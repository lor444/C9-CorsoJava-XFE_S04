package it.corsojava.cashreg.core;

import it.corsojava.cashreg.core.datatypes.specifici.Iva;

/**
 * <p>Rappresenta un articolo memorizzato nel registratore utile per la composizione rapida della riga
 * mediante inserimento del codice prodotto (vedi <i>RegistratoreScontrini.addRigaToCurrentScontrino(Articolo a)</i> )
 * oppure mediante la lettura del barcode del prodotto medesimo (vedi
 * <i>RegistratoreScontrini.addRigaToCurrentScontrinoByBarcode(String barcode)</i>)</p>
 * <p>Trattandosi di un oggetto usato solo per consultazione da un archivio pre-registrato non mette a
 * disposizione metodi setter pubblici</p>
 */
public interface Articolo {

    /**
     * Restituisce il codice articolo che lo identifica
     * @return
     */
    public String getCodArticolo();

    /**
     * Restituisce il barcode dell'articolo. Nota: prevedendo che nei test non si disponga di un vero lettore
     * barcode, i codici utilizzati nell'archivio articoli della libreria CorsoJavaLib.jar sono numeri semplici.
     * @return
     */
    public String getBarcode();

    /**
     * Restituisce la denominazione dell'articolo
     * @return
     */
    public String getDenominazione();

    /**
     * Restituisce la descrizione estesa dell'articolo
     * @return
     */
    public String getDescrizione();

    /**
     * Restituice il prezzo unitario dell'articolo
     * @return
     */
    public double getPrezzoUnitario();

    /**
     * Restituisce l'aliquota IVA dell'articolo
     * @return
     */
    public Iva getAliquotaIva();

}
