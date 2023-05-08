package it.corsojava.cashreg.core.implementation;

import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.datatypes.specifici.Iva;
import it.corsojava.cashreg.core.datatypes.specifici.Sconto;

import java.util.Properties;

public class RigaImpl implements Riga {

    private double prezzoUnitario;
    private Sconto sconto;
    private Iva iva;
    private double quantita;
    private String descrizione;

    protected RigaImpl(){

    }

    @Override
    public void setPrezzoUnitario(double prezzoUnitario){
        this.prezzoUnitario=prezzoUnitario;
    }
    @Override
    public double getPrezzoUnitario(){
        return this.prezzoUnitario;
    }
    @Override
    public void setSconto(Sconto sconto){
        this.sconto=sconto;
    }
    @Override
    public Sconto getSconto(){
        return this.sconto;
    }
    @Override
    public void setIva(Iva iva){
        this.iva=iva;
    }
    @Override
    public Iva getIva(){
        return this.iva;
    }
    @Override
    public void setQuantita(double quantita){
        this.quantita=quantita;
    }
    @Override
    public double getQuantita(){
        return this.quantita;
    }
    @Override
    public String getDescrizione() {
        return this.descrizione;
    }
    @Override
    public void setDescrizione(String descrizione) {
        this.descrizione=descrizione;
    }

    @Override
    public double getPrezzoTotale() {
        double temp=0.0;
        temp=prezzoUnitario * quantita;
        if(this.sconto !=null){
            temp -= this.sconto.calcolaValore(temp);
        }
        if(this.iva!=null){
            temp += this.iva.calcolaValore(temp);
        }
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(quantita);
        sb.append(" X ");
        sb.append(prezzoUnitario);
        sb.append(" - ");
        sb.append(sconto != null ? getSconto().getValue() : 0);
        sb.append("% ");
        if(getIva()!=Iva.IVA_0){
            sb.append(" + ");
            sb.append(getIva());
            sb.append("% ");
        }
        sb.append(" = ");
        sb.append(getPrezzoTotale());
        return sb.toString();
    }

    public Properties toProperties(){
        Properties rProp=new Properties();
        rProp.setProperty("descrizione",descrizione!=null ? descrizione : "");
        rProp.setProperty("prezzoUnitario",prezzoUnitario+"");
        rProp.setProperty("quantita", quantita+"");
        rProp.setProperty("sconto", sconto != null ? sconto.getValue()+"" : "0.0");
        rProp.setProperty("iva",iva+"");
        return rProp;
    }

    public static Riga fromProperties(Properties p) {
        if(p!=null){
            try {
                RigaImpl r = new RigaImpl();
                r.setPrezzoUnitario(Double.parseDouble(p.getProperty("prezzoUnitario")));
                r.setQuantita(Double.parseDouble(p.getProperty("quantita")));
                r.setSconto(new Sconto(Double.parseDouble(p.getProperty("sconto"))));
                r.setIva(Iva.valueOf(p.getProperty("iva")));
                r.setDescrizione(p.getProperty("descrizione"));
                return r;
            }catch (Exception ex){
                throw new UnsupportedOperationException("Unable to convert properties to instance",ex);
            }
        }else throw new IllegalArgumentException();
    }



}
