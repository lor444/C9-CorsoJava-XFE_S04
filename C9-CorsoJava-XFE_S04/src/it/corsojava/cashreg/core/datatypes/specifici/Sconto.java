package it.corsojava.cashreg.core.datatypes.specifici;

import it.corsojava.cashreg.core.datatypes.base.PercOutOfRangeException;
import it.corsojava.cashreg.core.datatypes.base.Percentuale;

public class Sconto extends Percentuale {

    public static  final double MIN_VALUE=0;

    public Sconto(double percentualeSconto) throws PercOutOfRangeException {
        super(percentualeSconto);
    }

    @Override
    public void setValue(double value) throws PercOutOfRangeException {
        super.setValue(value);
        if(this.getValue()<MIN_VALUE){
            throw new ScontoOutOfRangeException();
        }
    }
}

