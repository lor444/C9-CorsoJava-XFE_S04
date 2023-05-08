package it.corsojava.cashreg.core.implementation;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.implementation.exceptions.StoreEngineLoadException;
import it.corsojava.cashreg.core.implementation.exceptions.StoreEngineSaveScontrinoException;

import java.util.List;

public interface StoreEngine {

    public Scontrino saveScontrino(Scontrino s) throws StoreEngineSaveScontrinoException;

    public List<Scontrino> loadAll() throws StoreEngineLoadException;

}
