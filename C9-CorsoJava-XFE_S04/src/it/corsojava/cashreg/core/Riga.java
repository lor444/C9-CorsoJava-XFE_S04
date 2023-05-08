package it.corsojava.cashreg.core;

import it.corsojava.cashreg.core.datatypes.specifici.Iva;
import it.corsojava.cashreg.core.datatypes.specifici.Sconto;

/**
 * <p>Rappresenta la riga di uno scontrino.</p>
 */
public interface Riga {

    public void setPrezzoUnitario(double prezzoUnitario);
    public double getPrezzoUnitario();
    public void setSconto(Sconto sconto);
    public Sconto getSconto();
    public void setIva(Iva iva);
    public Iva getIva();
    public void setQuantita(double quantita);
    public double getQuantita();
    public double getPrezzoTotale();
    public String getDescrizione();
    public void setDescrizione(String descrizione);

}
