package it.corsojava.cashreg.core.implementation.engine;

import it.corsojava.cashreg.core.implementation.IdGenerator;

public class SystemTimeIdGenerator implements IdGenerator {


    @Override
    public String generateId() {
        return System.currentTimeMillis()+"";
    }
}
