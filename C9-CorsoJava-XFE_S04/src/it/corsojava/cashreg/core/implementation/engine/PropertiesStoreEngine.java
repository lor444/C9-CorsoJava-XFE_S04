package it.corsojava.cashreg.core.implementation.engine;

import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.StatoScontrino;
import it.corsojava.cashreg.core.implementation.IdGenerator;
import it.corsojava.cashreg.core.implementation.RigaImpl;
import it.corsojava.cashreg.core.implementation.ScontrinoImpl;
import it.corsojava.cashreg.core.implementation.StoreEngine;
import it.corsojava.cashreg.core.implementation.exceptions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PropertiesStoreEngine implements StoreEngine {

    public static final String storeDirectory="./data";
    private IdGenerator idGenerator;

    public PropertiesStoreEngine(IdGenerator generator) throws StoreEngineInitException{
        if(generator==null)
            throw  new StoreEngineInitException("The IdGenerator cannot be null");
        this.idGenerator=generator;
        Path storePath=Path.of(storeDirectory);
        if(!Files.exists(storePath)) {
            try {
                Files.createDirectories(storePath);
            }catch(IOException ioe){
                throw new StoreEngineInitException("Unable to create store directory ",ioe);
            }
        }
    }

    @Override
    public Scontrino saveScontrino(Scontrino s) throws StoreEngineSaveScontrinoException {
        if(s==null)
            throw new StoreEngineSaveScontrinoException("Null argument");
        if(s.getId()!=null)
            throw new StoreEngineSaveScontrinoException("Already saved");
        if(!(s instanceof ScontrinoImpl)){
            throw new StoreEngineSaveScontrinoException("StoreEngine can run only with ScontrinoImpl implementation");
        }
        Properties sProp=((ScontrinoImpl)s).toProperties();
        String id=idGenerator.generateId();
        sProp.setProperty("id",id);
        sProp.setProperty("stato", StatoScontrino.REGISTRATO+"");
        String sFilePath=storeDirectory + "/" + id + "_s.txt";
        try {
            OutputStream out = Files.newOutputStream(Path.of(sFilePath));
            sProp.store(out,"Scontrino "+id);
            Set<Riga> righe=s.getRighe();
            int index = 0;
            for(Riga r : righe) {
                saveRiga(id,index,r);
                index++;
            }
            return loadScontrino(sFilePath);
        }catch(IOException ioe){
            throw new StoreEngineSaveScontrinoException("Unable to save scontrino file",ioe);
        }catch(StoreEngineLoadScontrinoException sle){
            throw new StoreEngineSaveScontrinoException("Unable to recover  scontrino file",sle);
        }
    }

    private void saveRiga(String id,int position, Riga r) throws StoreEngineSaveRigaException{
        if(r==null)
            throw new StoreEngineSaveRigaException("Null argument");
        if(!(r instanceof RigaImpl))
            throw new StoreEngineSaveRigaException("StoreEngine can run only with ScontrinoImpl and RigaImpl implementation");
        Properties rProp=((RigaImpl)r).toProperties();
        String fileName=id+"_r_"+position+".txt";
        try {
            OutputStream out= Files.newOutputStream(Path.of(storeDirectory + "/" + fileName));
            rProp.store(out,id+"-"+position);
        }catch (IOException ioe){
            throw new StoreEngineSaveRigaException("Unable to save file "+fileName,ioe);
        }
    }

    @Override
    public List<Scontrino> loadAll() throws StoreEngineLoadException {
        Path p=Path.of(storeDirectory);
        List<Scontrino> list=new ArrayList<Scontrino>();
        List<Exception> loadErrors=new ArrayList<Exception>();
        try {
            Files.list(p).filter(f -> f.toString().endsWith("_s.txt")).forEach(s -> {
                try{
                    list.add(loadScontrino(s.toString()));
                }catch (StoreEngineLoadScontrinoException lse){
                    loadErrors.add(lse);
                }
            });
        }catch(IOException ioe){
            throw new StoreEngineLoadException("Unable to load the archive",ioe);
        }
        if(loadErrors.size()>0) {
            throw new StoreEngineLoadException("Unable to load the archive",loadErrors.get(0));
        }
        return list;
    }

    private Scontrino loadScontrino(String sFilePath) throws StoreEngineLoadScontrinoException {
        Path sPath = Path.of(sFilePath);
        if(!Files.exists(sPath))
            throw new StoreEngineLoadScontrinoException("Unable to find file "+sFilePath);
        try{
            InputStream in = Files.newInputStream(sPath);
            Properties sProp=new Properties();
            sProp.load(in);
            Scontrino s = ScontrinoImpl.fromProperties(sProp);
            loadScontrinoRighe(s);
            return s;
        }catch(IOException ioe){
            throw new StoreEngineLoadScontrinoException("Unable to load file "+sFilePath,ioe);
        }
    }

    private void loadScontrinoRighe(Scontrino s) throws StoreEngineLoadRigaException{
        if(s==null)
            throw new StoreEngineLoadRigaException("Invalid argument");
        if(s.getId()==null || s.getId().length()<1)
            throw new StoreEngineLoadRigaException("Scontrino has no id");
        if(!(s instanceof ScontrinoImpl))
            throw new StoreEngineLoadRigaException("Only available with ScontrinoImpl implementation");
        String searchPattern=s.getId()+"_r";
        Path sPath=Path.of(storeDirectory);
        Set<Riga> righe=new HashSet<Riga>();
        try{
            List<Path> rFilesPath = Files.list(sPath)
                    .filter(f -> f.toFile().getName().startsWith(searchPattern))
                    .sorted((f0,f1) -> f0.toFile().getName().compareTo(f1.toFile().getName()))
                    .collect(Collectors.toList());
            for(Path p : rFilesPath){
                InputStream in=Files.newInputStream(p);
                Properties rProp=new Properties();
                rProp.load(in);
                righe.add(RigaImpl.fromProperties(rProp));
            }
            s.setRighe(righe);
        }catch(IOException ioe){
            throw new StoreEngineLoadRigaException("Unable to load rows for scontrino id "+s.getId(),ioe);
        }
    }


}
