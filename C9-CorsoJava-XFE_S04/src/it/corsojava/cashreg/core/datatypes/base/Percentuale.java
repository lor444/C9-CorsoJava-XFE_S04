package it.corsojava.cashreg.core.datatypes.base;

public class Percentuale implements ValoreCalcolabile{

    public static final double MAX_VALUE=100.0;
    public static final double MIN_VALUE=-100.0;

    private double value;
        
    public Percentuale(double value) throws PercOutOfRangeException{
        setValue(value);
    }
    
    public double getValue(){
        return this.value;
    }
     
    public void setValue(double value) throws PercOutOfRangeException{
        if(value>=Percentuale.MIN_VALUE && value<=Percentuale.MAX_VALUE){
            this.value=value;
        }else {
            throw new PercOutOfRangeException();
        }
    } 

    public double calcolaValore(double valorebase){
        return valorebase / 100.0 * this.value;
    }

}


