
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

public class WekaClassifier {
  
    
   
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
 
    
   
    public void testFile(String testFile) throws Exception {
        // I've commented the code as best I can, at the moment.
        // Comments are denoted by "//" at the beginning of the line.
    	String gender=wekaGenderClassifier();
    	System.out.println("Predicted Gender=> "+gender);
    	
    	
        
    }
}