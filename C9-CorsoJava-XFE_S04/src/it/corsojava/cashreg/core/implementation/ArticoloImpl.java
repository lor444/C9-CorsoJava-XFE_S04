package it.corsojava.cashreg.core.implementation;

import it.corsojava.cashreg.core.Articolo;
import it.corsojava.cashreg.core.datatypes.specifici.Iva;

public class ArticoloImpl implements Articolo {

    private String codArticolo;
    private String barcode;
    private String denominazione;
    private String descrizione;
    private double prezzoUnitario;
    private Iva aliquotaIva;

    @Override
    public String getCodArticolo() {
        return codArticolo;
    }

    @Override
    public String getBarcode() {
        return barcode;
    }

    @Override
    public String getDenominazione() {
        return denominazione;
    }

    @Override
    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    @Override
    public Iva getAliquotaIva() {
        return aliquotaIva;
    }

    public static Articolo fromTextLine(String line){
        //codArticolo;barcode;denominazione;descrizione;prezzoUnitario;aliquotaIva
        String[] fields=line.split(";");
        ArticoloImpl a =new ArticoloImpl();
        a.codArticolo=fields[0];
        a.barcode=fields[1];
        a.denominazione=fields[2];
        a.descrizione=fields[3];
        a.prezzoUnitario= Double.parseDouble(fields[4]);
        switch (fields[5]){
            case "22":
                a.aliquotaIva=Iva.IVA_22;
                break;
            case "10":
                a.aliquotaIva=Iva.IVA_10;
                break;
            case "4":
                a.aliquotaIva=Iva.IVA_4;
                break;
            default:
                a.aliquotaIva=Iva.IVA_0;
                break;
        }
        return a;
    }
}
