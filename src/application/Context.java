package application;

import java.io.File;

import weka.core.Instances;

public class Context {

    private final static Context instance = new Context();

    private static File loadedFile;
    
    private static Instances loadedInstance;
    
    
    public static Context getInstance() {
        return instance;
    }

    public static void setLoadedFile(File file)
    {
    	loadedFile = file;
    }
    
    public static File getLoadedFile()
    {
    	return loadedFile;
    }

    public static void setLoadedInstance(Instances instances)
    {
    	loadedInstance = instances;
    }
    
    public static Instances getLoadedInstance()
    {
    	return loadedInstance;
    }
    
}