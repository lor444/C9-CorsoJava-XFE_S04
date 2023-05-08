package it.corsojava.cashreg;

import it.corsojava.cashreg.core.*;
import it.corsojava.cashreg.core.datatypes.specifici.Sconto;
import it.corsojava.cashreg.core.implementation.IdGenerator;
import it.corsojava.cashreg.core.implementation.RegistratoreScontriniImpl;
import it.corsojava.cashreg.core.implementation.ScontrinoImpl;
import it.corsojava.cashreg.core.implementation.StoreEngine;
import it.corsojava.cashreg.core.implementation.engine.PropertiesStoreEngine;
import it.corsojava.cashreg.core.implementation.engine.SystemTimeIdGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DbGenerator {


    public static void main(String[] args) {
        try {
            IdGenerator gen=new SystemTimeIdGenerator();
            StoreEngine engine=new PropertiesStoreEngine(gen);
            RegistratoreScontrini registratore = new RegistratoreScontriniImpl(engine);


            LocalDate from = LocalDate.now().minusDays(3);
            LocalDate to = LocalDate.now();
            LocalTime openingTime = LocalTime.of(8, 0);
            LocalTime closingTime = LocalTime.of(20, 0);

            LocalDate currentDate = from;
            /*
            for (int i = 0; i < 100; i++) {

                System.out.println(getRandomSconto());

            }*/


            while (currentDate.compareTo(to) <= 0) {
                System.out.println("Running "+currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                // da 10 a 30 scontrini al giorno
                int nrScontriniDay = getRandom(10, 30);
                Scontrino savedScontrino=null;
                // generazione di scontrini
                if(currentDate.compareTo(LocalDate.now())==0) closingTime=LocalTime.now();
                for (int i = 0; i < nrScontriniDay; i++) {
                    LocalTime orario = getRandomTime(openingTime, closingTime);
                    Scontrino s = registratore.creaScontrinoVendita();
                    s.setTipo(TipiScontrino.VENDITA);
                    s.setData(currentDate);
                    s.setOra(orario);
                    s.setStato(StatoScontrino.NUOVO);
                    int nrRighe = getRandom(1, 7);
                    for(int x=0;x<nrRighe;x++){
                        Articolo art = getRandomArticolo(registratore);
                        Riga r = s.creaNuovaRiga();
                        r.setQuantita(1);
                        r.setPrezzoUnitario(art.getPrezzoUnitario());
                        r.setIva(art.getAliquotaIva());
                        r.setSconto(new Sconto(getRandomSconto()));
                    }
                    savedScontrino=registratore.chiudiCurrentScontrino(); //engine.saveScontrino(s);

                }
                // generazione di qualche storno
                boolean generaStorno=bet(0.85);
                if(generaStorno){
                    registratore.creaStorno();
                    Scontrino storno = registratore.popolaStornoDaScontrino(savedScontrino);
                    registratore.getCurrentScontrino().setData(savedScontrino.getData());
                    registratore.getCurrentScontrino().setOra(savedScontrino.getOra().plusMinutes(2));
                    savedScontrino=registratore.chiudiCurrentScontrino();
                    System.out.println("Creato Storno "+savedScontrino.getId());
                }
                // generazione di almeno una chiusura intermedia tra le 12:00 e le 14:00
                /*
                LocalTime intermTime=LocalTime.of(getRandom(12,14),getRandom(0,59));
                System.out.println("Chiusura intermedia alle "+intermTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                registratore.venditeGiornaliereEffettuaChiusura()
                // e una a fine giornata dopo le 20:0
                */
                currentDate = currentDate.plusDays(1);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Se il numero random generato supera il minValue allora restituisce true
     * In pratica è una scomeessa in cui viene indicata la probabiltà di perdita
     * @param minValue
     * @return
     */
    public static boolean bet(double minValue){
        if(minValue>1.0) throw new IllegalArgumentException("Min valute must be between 0.0 and 1.0");
        return Math.random()>minValue;
    }


    public static double getRandomSconto(){
        List<Double> sconti=new ArrayList<Double>();
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(0.0);
        sconti.add(5.0);
        sconti.add(5.0);
        sconti.add(5.0);
        sconti.add(5.0);
        sconti.add(10.0);
        sconti.add(10.0);
        sconti.add(20.0);
        double r = Math.random()*(sconti.size()-1);
        r=Math.round(r);
        return sconti.get((int)r);
    }
    public static  Articolo getRandomArticolo(RegistratoreScontrini registratore){
        int index=getRandom(0,registratore.getArticoli().size()-1);
        return registratore.getArticoli().get(index);
    }

    public static int getRandom(int min,int max){
        double d = Math.random();
        double t = (max-min)*d;
        t = Math.round(t);
        int result=(int)t+min;
         // System.out.println(result+" <- "+d);
        return result;
    }

    public static LocalTime getRandomTime(LocalTime from, LocalTime to){
        Duration d= Duration.between(from,to);
        long seconds=d.getSeconds();
        double r = Math.random();
        double s = r * seconds;
        int elapsed=(int)Math.round(s);
        return from.plusSeconds(elapsed);
    }


}
