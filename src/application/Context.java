package application;

import java.io.File;

import weka.classifiers.Evaluation;
import weka.core.Instances;

public class Context {

    private final static Context instance = new Context();

    private static File loadedFile;

    private static Instances loadedInstance = null;

    private static Evaluation evaluation;

    private static int KNN;

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

    public static void setEvaluation(Evaluation _evaluation)
    {
    	evaluation = _evaluation;
    }

    public static Evaluation getEvaluation()
    {
    	return evaluation;
    }

    public static void setKNN(int N)
    {
    	KNN = N;
    }

    public static int getKNN()
    {
    	return KNN;
    }
}