package it.corsojava.cashreg.core;

import it.corsojava.cashreg.core.exceptions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Rappresenta l'interfaccia generale del "motore" dell'applicazione.</p>
 * <p>Espone all'interfaccia utente le funzionalita' intrinseche del programma</p>
 * <p>L'interfaccia utente attraverso il RegistratoreScontrini "pilota" il programma eseguendone
 * le funzionalita' elencate</p>
 * <p>Per utilizzarla e' necessario realizzarne una implementazione concreta che dovra' essere istanziata
 * all'avvio del programma e che dovra' preoccuparsi di caricare i dati dagli archivi di memorizzazione
 * per permettere l'elaborazione delle funzionalita'</p>
 */
public interface RegistratoreScontrini {

    /**
     * <p>Restituisce l'elenco degli articoli registrati e disponibili per il riconscimento a mezzo di
     * codice a barre</p>
     * @return Un oggetto List contenente tutte le istanze di oggetti Articolo presenti nel registro
     */
    public List<Articolo> getArticoli();

    /**
     * <p>Crea uno scontrino di vendita e ne restituisce un riferimento</p>
     * <p>La creazione avviene con successo solo se non e' impostato alcuno scontrino corrente
     * al momento della creazione</p>
     * @return Il riferimento allo scontrino creato
     */
    public Scontrino creaScontrinoVendita() throws ScontrinoCreateException;

    /**
     * <p>Crea uno scontrino di storno e ne restituisce un riferimento</p>
     * <p>La creazione avviene con successo solo se non e' impostato alcuno scontrino corrente
     * al momento della creazione</p>
     * @return Il riferimento allo scontrino creato
     */
    public Scontrino creaStorno() throws ScontrinoCreateException;

    /**
     * <p>Restituisce lo scontrino corrente su cui si sta operando (se presente)</p>
     * @return L'oggetto relativo allo scontrino corrente. Se non presente restituisce null
     */
    public Scontrino getCurrentScontrino();

    /**
     * <p>Aggiunge una nuova riga allo scontrino corrente (se presente) in base all'articolo
     * passato come argomento e ne restituisce il riferimento</p>
     * @param a <p>L'articolo selezionato per l'aggiunta</p>
     * @return
     */
    public Riga addRigaToCurrentScontrino(Articolo a) throws ScontrinoAddRigaException;

    /**
     * <p>Aggiunge una nuova riga allo scontrino corrente (se presente) in base al barcode dell'articolo
     * passato come argomento e ne restituisce il riferimento</p>
     * @param barcode <p>Il barcode di un articolo presente a catalogo</p>
     * @return Il riferimento all'oggetto Riga creato dall'operazione
     */
    public Riga addRigaToCurrentScontrinoByBarcode(String barcode) throws ScontrinoAddRigaException;

    /**
     * <p>Termina uno scontrino e lo registra in modo persistente</p>
     * <p>Dopo la persistenza, lo scontrino non e' piu' lo scontrino corrente (che diventa null)</p>
     * @return <>Lo scontrino appena reso persistente (dotato di ID assegnato all'atto della registrazione)</>
     * @throws ScontrinoCloseException
     */
    public Scontrino chiudiCurrentScontrino() throws ScontrinoCloseException ;

    /**
     * <p>Popola la righe di uno scontrino di tipo STORNO prendendo spunto da uno scontrino di vendita
     * che dovra' essere stornato.</p>
     * <p>Per ogni riga dello scontrino di vendita il metodo genera una riga all'interno dello scontrino di
     * storno con valore identico a quello della corrispondente riga dello scontrino di vendita ma con segno
     * negativo</p>
     *
     * @param s Lo scontrino di vendita.
     * @return Uno scontrino di storno basato sullo scontrino di vendita passato come argomento
     * @throws StornoImportException
     */
    public Scontrino popolaStornoDaScontrino(Scontrino s) throws StornoImportException;

    // funzioni per analisi vendite

    /**
     * <p>Ricerca uno scontrino nel registro generale in base all'id passato come argomento</p>
     * @param id
     * @return
     */
    public Optional<Scontrino> findScontrino(String id);

    /**
     * <p>Effettua l'operazione di "chiusura di cassa". In pratica registra un nuovo scontrino contenente
     * la somma degli scontrini di vendita e degli storni effettuati a partire dalla precendete operazioni
     * di chiusura</p>
     * <p>E' una sorta di "somma parziale" delle operazioni che viene registrata con l'emissione di uno
     * scontrino di tipo particolare, appunto uno scontrino di "chiusura"</p>
     * @throws ChiusuraDiCassaException
     */
    public Scontrino venditeGiornaliereEffettuaChiusura() throws ChiusuraDiCassaException;

    /**
     * <p>Restituisce l'elenco degli scontrini delle vendite giornaliere.</p>
     * <p>Comprende scontrini di vendita, di storno e delle chiusure</p>
     * @param giorno Data per la quale si vuole otttenere l'elenco degli scontrini
     * @return Un elenco di oggetti Scontrino di diverso tipo</>
     */
    public List<Scontrino> getVenditeGiornaliereElencoScontrini(LocalDate giorno);

    /**
     * <p>Restituisce l'elenco delle vendite effettuate fino al momento passato come argomento e solo a partire
     * dall'ultima chiusura o al massimo dall'inizio della giornata</p>
     *
     * @param momento Il momento in cui si vuole rilevare il parziale (solitamente LocalDateTime.now() )
     * @return La somma dei totali di tutte le vendite effettuate a partire dall'ultima chiusura precedente
     * il momento passato come argomento o, in caso non ve ne siano nella giornata, dall'inizio della giornata
     */
    public double getVenditeGiornaliereParziale(LocalDateTime momento);

    /**
     * <p>Restituisce il valore complessivo delle vendite giornaliere per il giorno indicato come argomento.</p>
     * <p>Se nella giornata vi sono alcune chiusure non le prende in considerazione e somma semlicemente il totale
     * degli scontrini di vendita a cui sottrae il totale degli storni nella giornata indicata</p>
     * @param giorno Il giorno di cui si richiede il calcolo del totale complessivo di vendita
     * @return Un valore double che rappresenta l'incasso totale di una giornata
     */
    public double getVenditeGiornaliereTotale(LocalDate giorno);

    /**
     * Restituisce il totale complessivo delle vendite registrate
     * @return Un valore double
     */
    public double getVenditeGeneraliTotaleComplessivo();

    /**
     * <p>Esporta una mappa con gli importi complessivi giornalieri suddivisi per data</p>
     * <p>La data costituisce la chiave della mappa di cui il totale giornaliero e' il valore</p>
     * @param dal Data iniziale del periodo considerato per l'esportazione (compresa nell'intervallo)
     * @param al Data finale del periodo considerato per l'esportazione (compresa nell'intervallo)
     * @return La mappa dei totali di vendita del periodo preso in considerazione
     * @throws ScontrinoSearchException
     */
    public Map<LocalDate,Double> getVenditeGeneraliTotaliGiornalieri(LocalDate dal, LocalDate al) throws ScontrinoSearchException;

}