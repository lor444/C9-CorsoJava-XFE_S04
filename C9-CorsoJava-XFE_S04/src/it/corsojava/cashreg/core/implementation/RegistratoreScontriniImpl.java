package it.corsojava.cashreg.core.implementation;

import it.corsojava.cashreg.core.*;
import it.corsojava.cashreg.core.datatypes.specifici.Iva;
import it.corsojava.cashreg.core.exceptions.*;
import it.corsojava.cashreg.core.implementation.exceptions.StoreEngineException;
import it.corsojava.cashreg.core.implementation.exceptions.StoreEngineSaveScontrinoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegistratoreScontriniImpl implements RegistratoreScontrini {

    private List<Articolo> articoli;
    private Scontrino currentScontrino;
    private StoreEngine engine;
    private List<Scontrino> archivio;

    public RegistratoreScontriniImpl(StoreEngine engine) throws StoreEngineException, RegistratoreLoadException {
        this.engine= engine;
        archivio=this.engine.loadAll();
        loadArticoli();
    }

    private void loadArticoli(){
        // codArticolo;barcode;denominazione;descrizione;prezzoUnitario;aliquotaIva
        String source = it.corsojava.datasources.StringDataSources.getArticoli();
        String[] lines = source.split("\n");
        articoli=new ArrayList<Articolo>();
        for(int i=1; i<lines.length;i++){
            Articolo a = ArticoloImpl.fromTextLine(lines[i]);
            articoli.add(a);
        }
    }

    @Override
    public List<Articolo> getArticoli() {
        return articoli;
    }

    @Override
    public Scontrino creaScontrinoVendita() throws ScontrinoCreateException {
        if(this.currentScontrino!=null)
            throw new ScontrinoCreateException("C'e' gia' uno scontrino aperto");
        ScontrinoImpl s =new ScontrinoImpl();
        s.setTipo(TipiScontrino.VENDITA);
        this.currentScontrino=s;
        return s;
    }

    @Override
    public Scontrino creaStorno() throws ScontrinoCreateException {
        if(this.currentScontrino!=null)
            throw new ScontrinoCreateException("C'e' gia' uno scontrino aperto");
        ScontrinoImpl s =new ScontrinoImpl();
        s.setTipo(TipiScontrino.STORNO);
        this.currentScontrino=s;
        return s;
    }

    @Override
    public Scontrino getCurrentScontrino() {
        return this.currentScontrino;
    }

    @Override
    public Riga addRigaToCurrentScontrino(Articolo a) throws ScontrinoAddRigaException {
        if(this.currentScontrino==null)
            throw new ScontrinoAddRigaException("Nessuno scontrino aperto. Impossibile aggiungere una riga");
        if(articoli.contains(a)){
            Riga r = currentScontrino.creaNuovaRiga();
            r.setPrezzoUnitario(a.getPrezzoUnitario());
            r.setQuantita(1);
            r.setIva(a.getAliquotaIva());
            r.setDescrizione(a.getDenominazione());
            return r;
        }
        return null;
    }

    @Override
    public Riga addRigaToCurrentScontrinoByBarcode(String barcode) throws ScontrinoAddRigaException {
        if(this.currentScontrino==null)
            throw new ScontrinoAddRigaException("Nessuno scontrino aperto. Impossibile aggiungere una riga");
        Optional<Articolo> art=articoli.stream().filter(a -> a.getBarcode().equals(barcode)).findFirst();
        Riga riga=null;
        if(art.isPresent()) {
            riga= addRigaToCurrentScontrino(art.get());
        }
        return riga;
    }

    @Override
    public Scontrino chiudiCurrentScontrino() throws ScontrinoCloseException {
        if(this.currentScontrino==null)
            throw new ScontrinoCloseException("Nessuno scontrino aperto. Impossibile chiuderlo");
        try {
            Scontrino scontrinoSalvato = engine.saveScontrino(currentScontrino);
            this.archivio.add(scontrinoSalvato);
            this.currentScontrino=null;
            return scontrinoSalvato;
        } catch (StoreEngineSaveScontrinoException e) {
            throw new ScontrinoCloseException("Impossibile registrare lo scontrino",e);
        }
    }

    @Override
    public Scontrino popolaStornoDaScontrino(Scontrino s) throws StornoImportException {
        if(this.currentScontrino==null)
            throw new StornoImportException("Non c'e' alcuno storno aperto");
        if(this.currentScontrino.getTipo()!=TipiScontrino.STORNO)
            throw new StornoImportException("Lo scontrino aperto non e' uno storno");
        for(Riga r : s.getRighe()){
            Riga sr = currentScontrino.creaNuovaRiga();
            sr.setDescrizione(r.getDescrizione());
            sr.setIva(r.getIva());
            sr.setQuantita(r.getQuantita());
            sr.setPrezzoUnitario( - r.getPrezzoUnitario() ); // meno .. inversione valori
        }
        return currentScontrino;
    }

    @Override
    public Optional<Scontrino> findScontrino(String id) {
        return archivio.stream().filter(s -> s.getId().equals(id)).findFirst();

    }

    @Override
    public Scontrino venditeGiornaliereEffettuaChiusura() throws ChiusuraDiCassaException {
        return null;
    }

    @Override
    public List<Scontrino> getVenditeGiornaliereElencoScontrini(LocalDate giorno) {
        return archivio.stream()
                .filter(s -> s.getData().compareTo(giorno)==0)
                .filter(s -> s.getTipo()==TipiScontrino.VENDITA || s.getTipo()==TipiScontrino.STORNO)
                .collect(Collectors.toList());
    }

    @Override
    public double getVenditeGiornaliereParziale(LocalDateTime momento) {
        return 0;
    }

    @Override
    public double getVenditeGiornaliereTotale(LocalDate giorno) {
        return getVenditeGiornaliereElencoScontrini(giorno).stream()
                .mapToDouble(s -> s.getTotaleComplessivo())
                .sum();
    }

    @Override
    public double getVenditeGeneraliTotaleComplessivo() {
        return archivio.stream()
                .filter(s -> s.getTipo()==TipiScontrino.VENDITA || s.getTipo()==TipiScontrino.STORNO)
                .mapToDouble(s -> s.getTotaleComplessivo())
                .sum();
    }

    @Override
    public Map<LocalDate, Double> getVenditeGeneraliTotaliGiornalieri(LocalDate dal, LocalDate al) throws ScontrinoSearchException {
        /*
        Map<LocalDate,Double>  map = new HashMap<LocalDate,Double>();
        Map<LocalDate,List<Scontrino>> intermediateMap = archivio.stream().
                filter(s -> s.getTipo()==TipiScontrino.VENDITA || s.getTipo()==TipiScontrino.STORNO)
                .collect(Collectors.groupingBy(s -> s.getData()));
        intermediateMap.keySet().parallelStream().forEach(k ->
                map.put(k ,
                        intermediateMap.get(k).stream().mapToDouble(s -> s.getTotaleComplessivo()).sum()
                ));
        return map;*/
        return archivio.stream()
                .filter(s -> s.getTipo()==TipiScontrino.VENDITA || s.getTipo()==TipiScontrino.STORNO)
                .collect(Collectors.groupingBy(s -> s.getData(), Collectors.summingDouble(s -> s.getTotaleComplessivo())));

    }
}
