package it.corsojava.cashreg.core.datatypes.specifici;

import it.corsojava.cashreg.core.datatypes.base.ValoreCalcolabile;

public enum Iva implements ValoreCalcolabile {
    IVA_0(0.0),
    IVA_4(4.0),
    IVA_10(10.0),
    IVA_22(22.0);
    private double aliquota;
    private Iva(double aliquota){
        this.aliquota=aliquota;
    }
    public double calcolaValore(double imponibile){
        return imponibile / 100 * this.aliquota;
    }
}
