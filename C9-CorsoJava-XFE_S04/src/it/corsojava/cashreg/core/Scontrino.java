package it.corsojava.cashreg.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * <p>Rappresenta uno scontrino di vendita, contenitore di oggetti Riga in cui sono dettagliati gli importi</p>
 * <p>Può appartenere a tre tipologie: VENDITA (scontrino effettivo di vendita) STORNO (storno di una vendita
 * o reso del prodotto) CHIUSURA (rappresenta un subtotale effettuato durante la giornata per fissare il valore
 * degli incassi)</p>
 */
public interface Scontrino {

    public String getId();

    /**
     * Indica la tipologie tra TipiScontrino.VENDITA, TipiScontrino.STORNO, TipiScontrino.CHIUSURA
     * @return
     */
    public TipiScontrino getTipo();

    /**
     * Imposta la tipologia dello scontrino tra TipiScontrino.VENDITA, TipiScontrino.STORNO, TipiScontrino.CHIUSURA
     * @return
     */
    public void setTipo(TipiScontrino tipo);

    /**
     * Restituisce lo stato dello scontrino che può essere StatoScontrino.NUOVO (in fase di editazione) o
     * StatoScontrino.REGISTRATO (già reso persistente)
     * @return
     */
    public StatoScontrino getStato();
    public void setStato(StatoScontrino stato);

    /**
     * Restitusice il totale complessivo dello scontrino
     * @return
     */
    public double getTotaleComplessivo();

    public String getIntestazione();
    public void setIntestazione(String intestazione);

    public String getPieDiPagina();
    public void setPieDiPagina(String pieDiPagina);

    public LocalDate getData();
    public void setData(LocalDate data);

    public LocalTime getOra();
    public void setOra(LocalTime ora);

    public Set<Riga> getRighe();
    public void setRighe(Set<Riga> righe);

    // ---------------------------------------

    /**
     * <p>Crea una nuova Riga che viene automaticamente registrata nell'elenco delle righe appartenenti allo scontrino
     * senza necessità di aggiungerla successivamente</p>
     * @return
     */
    public Riga creaNuovaRiga();

}

