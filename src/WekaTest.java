
import java.awt.*;        
import java.awt.event.*; 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class WekaTest {
    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;
        
        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + filename);
        }
        
        return inputReader;
    }
    
    public static Evaluation simpleClassify(Classifier model, Instances trainingSet, Instances testingSet) throws Exception {
        Evaluation validation = new Evaluation(trainingSet);
        
        model.buildClassifier(trainingSet);
        validation.evaluateModel(model, testingSet);
        
        return validation;
    }
    
    public static double calculateAccuracy(FastVector predictions) {
        double correct = 0;
        
        for (int i = 0; i < predictions.size(); i++) {
            NominalPrediction np = (NominalPrediction) predictions.elementAt(i);
            if (np.predicted() == np.actual()) {
                correct++;
            }
        }
        
        return 100 * correct / predictions.size();
    }
    
    public static Instances[][] crossValidationSplit(Instances data, int numberOfFolds) {
        Instances[][] split = new Instances[2][numberOfFolds];
        
        for (int i = 0; i < numberOfFolds; i++) {
            split[0][i] = data.trainCV(numberOfFolds, i);
            split[1][i] = data.testCV(numberOfFolds, i);
        }
        
        return split;
    }
    public static String wekaGenderClassifier() throws Exception{
    	
    	String prediction="";
    	 
		 BufferedReader reader = new BufferedReader(new FileReader(Constants.Ouput_DATA_DIR+Constants.ARFF_TEST_Gender));
		 ArffReader arff = new ArffReader(reader, 1000);
		 Instances data = arff.getStructure();
		 data.setClassIndex(data.numAttributes() - 1);
		 Instance inst;
		 int instanceNo=0;
		 while ((inst = arff.readInstance(data)) != null) {
			   data.add(inst);
			   Classifier clstest = (Classifier) weka.core.SerializationHelper.read(Constants.GENDER_LadTreeModel_FILE);
			   Double valu=clstest.classifyInstance(data.instance(instanceNo));
			  
			   prediction=data.classAttribute().value((int) clstest.classifyInstance(data.instance(instanceNo)));
			   System.out.println("The predicted Gender-value of instance "+Integer.toString(instanceNo)+": "+prediction);
		 }
		 
		 reader.close();
		 
		 return prediction;
    }
 public static String wekaGendertmpClassifier() throws Exception{
    	
    	String prediction="";
    	 
		 BufferedReader reader = new BufferedReader(new FileReader(Constants.Ouput_DATA_DIR+Constants.ARFF_TEST_Gender));
		 ArffReader arff = new ArffReader(reader, 1000);
		 Instances data = arff.getStructure();
		 data.setClassIndex(data.numAttributes() - 1);
		 Instance inst;
		 int instanceNo=0;
		 while ((inst = arff.readInstance(data)) != null) {
			   data.add(inst);
			   Classifier clstest = (Classifier) weka.core.SerializationHelper.read(Constants.GENDER_LadTreeModel_FILE);
			   Double valu=clstest.classifyInstance(data.instance(instanceNo));
			  
			   prediction=data.classAttribute().value((int) clstest.classifyInstance(data.instance(instanceNo)));
			   System.out.println("The predicted Gender-value of instance "+Integer.toString(instanceNo)+": "+prediction);
		 }
		 
		 reader.close();
		 
		 return prediction;
    }
 public static String wekaAgeClassifier() throws Exception{
    	
    	String prediction="";
    	 
		 BufferedReader reader = new BufferedReader(new FileReader(Constants.Ouput_DATA_DIR+Constants.ARFF_TEST_Age));
		 ArffReader arff = new ArffReader(reader, 1000);
		 Instances data = arff.getStructure();
		 data.setClassIndex(data.numAttributes() - 1);
		 Instance inst;
		 int instanceNo=0;
		 while ((inst = arff.readInstance(data)) != null) {
			   data.add(inst);
			   Classifier clstest = (Classifier) weka.core.SerializationHelper.read(Constants.AGE_RANDOMForestModel_FILE);
			   Double valu=clstest.classifyInstance(data.instance(instanceNo));
			  
			   prediction=data.classAttribute().value((int) clstest.classifyInstance(data.instance(instanceNo)));
			   System.out.println("The predicted Age-value of instance "+Integer.toString(instanceNo)+": "+prediction);
		 }
		 
		 reader.close();
		 
		 return prediction;
    }
    
    
    public static String wekaClassifier( ) throws Exception{
		 
		 String prediction="";
        String testFile=Constants.ARFF_TEST_Gender;
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(Constants.GENDER_Model_FILE));
        Classifier fc = (Classifier) ois.readObject();
        
		 BufferedReader reader = new BufferedReader(new FileReader(testFile));
		 ArffReader arff = new ArffReader(reader, 1000);
		 Instances test = arff.getStructure();
		 test.setClassIndex(test.numAttributes() - 1);
		 for (int i = 0; i < test.numInstances(); i++) {
			   double pred = fc.classifyInstance(test.instance(i));
			   System.out.print("ID: " + test.instance(i).value(0));
			   System.out.print(", actual: " + test.classAttribute().value((int) test.instance(i).classValue()));
			   System.out.println(", predicted: " + test.classAttribute().value((int) pred));
			 }
		 
		 reader.close();
		 
		 return prediction;
		// System.exit(0);

}
    public void testFile(String testFile) throws Exception {
        // I've commented the code as best I can, at the moment.
        // Comments are denoted by "//" at the beginning of the line.
    
    		String gender=wekaGenderClassifier();
    	//String gender=wekaGendertmpClassifier();
    	
    	
    	System.out.println("Predicted Gender=> "+gender);
    	
    	String age=wekaAgeClassifier();
    	System.out.println("Predicted Age=>"+age);
    	
    	
    	ArrayList<String> listData = new ArrayList( );
    	String author_id=testFile.substring(0, testFile.lastIndexOf('.'));
    	listData.add(author_id);
    	listData.add(gender);
    	listData.add(age);
    	CreateXML xmlFile=new CreateXML();
    	xmlFile.createXML(Constants.Ouput_DATA_DIR+author_id+".xml",listData);
    	
     //   BufferedReader datafile = readDataFile(Constants.ARFF_TEST_FILE);
        
      //  Instances originalTrain = new Instances(datafile);
       // Classifier cls = (Classifier) weka.core.SerializationHelper.read(Constants.GENDER_Model_FILE);

        //predict instance class values
      
        //which instance to predict class value
        int s1=1;  


        
    }
}