package it.corsojava.cashreg.core.implementation;

import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.StatoScontrino;
import it.corsojava.cashreg.core.TipiScontrino;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ScontrinoImpl implements Scontrino {

    private String id;
    private String intestazione;
    private String pieDiPagina;
    private LocalDate data;
    private LocalTime ora;
    private TipiScontrino tipo;
    private StatoScontrino stato;
    Set<Riga> righe;

    protected ScontrinoImpl(){
        id=null;
        tipo=TipiScontrino.VENDITA;
        intestazione="";
        pieDiPagina="";
        data=LocalDate.now();
        ora=LocalTime.now();
        righe = new HashSet<Riga>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public TipiScontrino getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipiScontrino tipo) {
        this.tipo=tipo;
    }

    @Override
    public StatoScontrino getStato() {
        return this.stato;
    }

    @Override
    public void setStato(StatoScontrino stato) {
        this.stato=stato;
    }

    @Override
    public String getIntestazione() {
        return intestazione;
    }

    @Override
    public void setIntestazione(String intestazione) {
        this.intestazione = intestazione;
    }

    @Override
    public String getPieDiPagina() {
        return pieDiPagina;
    }

    @Override
    public void setPieDiPagina(String pieDiPagina) {
        this.pieDiPagina = pieDiPagina;
    }

    @Override
    public LocalDate getData() {
        return this.data;
    }

    @Override
    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public LocalTime getOra() {
        return this.ora;
    }

    @Override
    public void setOra(LocalTime ora) {
        this.ora=ora;
    }

    @Override
    public Set<Riga> getRighe() {
        return righe;
    }

    @Override
    public void setRighe(Set<Riga> righe) {
        this.righe = righe;
    }

    @Override
    public double getTotaleComplessivo() {
        double temp=0;
        for(Riga r : righe){
            temp += r.getPrezzoTotale();
        }
        return temp;
    }

    @Override
    public Riga creaNuovaRiga() {
        Riga r = new RigaImpl();
        this.righe.add(r);
        return r;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb      .append(id)
                .append(" - ")
                .append(getData())
                .append(" - ")
                .append(getOra())
                .append(" - ")
                .append(tipo)
                .append(" ")
                .append(getTotaleComplessivo());
        return sb.toString();
    }

    public Properties toProperties(){
        Properties sProp=new Properties();
        sProp.setProperty("id",id!=null?id:"");
        sProp.setProperty("data", data!=null ?data.format(DateTimeFormatter.ISO_LOCAL_DATE):"");
        sProp.setProperty("ora", ora!=null ? ora.format(DateTimeFormatter.ISO_LOCAL_TIME):"");
        sProp.setProperty("intestazione",intestazione!=null?intestazione:"");
        sProp.setProperty("pieDiPagaina",pieDiPagina!=null ? pieDiPagina : "");
        sProp.setProperty("stato",""+getStato());
        sProp.setProperty("tipo",""+getTipo());
        return sProp;
    }

    public static Scontrino fromProperties(Properties p) {
        if(p!=null){
            try {
                ScontrinoImpl s = new ScontrinoImpl();
                s.id = p.getProperty("id");
                s.setData(LocalDate.parse(p.getProperty("data"), DateTimeFormatter.ISO_LOCAL_DATE));
                s.setOra(LocalTime.parse(p.getProperty("ora"), DateTimeFormatter.ISO_LOCAL_TIME));
                s.setIntestazione(p.getProperty("intestazione"));
                s.setPieDiPagina(p.getProperty("pieDiPagaina"));
                s.setStato(StatoScontrino.valueOf(p.getProperty("stato")));
                s.setTipo(TipiScontrino.valueOf(p.getProperty("tipo")));
                return s;
            }catch (Exception ex){
                throw new UnsupportedOperationException("Unable to convert properties to instance",ex);
            }
        }else throw new IllegalArgumentException();
    }


}
