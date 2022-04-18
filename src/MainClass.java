
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.File;
import java.io.FileInputStream;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.JFileChooser;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;



@SuppressWarnings("unused")
public class MainClass extends Thread {

		
	 public void run() {
		 
		 try {
			extractFeatures();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	 public static Evaluation classify(Classifier model,
				Instances trainingSet, Instances testingSet) throws Exception {
			Evaluation evaluation = new Evaluation(trainingSet);
	 
			model.buildClassifier(trainingSet);
			evaluation.evaluateModel(model, testingSet);
	 
			return evaluation;
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
	 
	 static void extractFeatureOfTestData(String fileName) throws Exception{
		 
		 POSTagger pt =new POSTagger();
			String tagged="";
			 
			PreprocessClass pc= new PreprocessClass();
			StylometricTechniques lexicalTechniques=new StylometricTechniques();
			
			ReadData rd=new ReadData();
			SyntacticFeatures p=new SyntacticFeatures();
			String data="";
			double features[]=new double[55];
			double tags[]=new double[16];
			double lexicalFeatures[] =new double[28];
			 Instances  testDataSet = null;
			TestDataArff createGenderArff=new TestDataArff();
			TestDataArff createAgeArff=new TestDataArff();
			createGenderArff.initializeARFF(true);
			createAgeArff.initializeARFF(false);
			ListFilesUtil listFilesUtil = new ListFilesUtil();
	        String[] authorInfo= new String[2];
	        
	        data=listFilesUtil.readFileFromTestDirectory(fileName);
	    //    for (File file : flist){
	        
	        
	         //   if (file.isFile() && file.getName().endsWith(".txt")){
	            	
	          // System.out.println("FIlen name is: "+file.getName());
					  //  data=listFilesUtil.readFile(file.getName());
//	    if(x==1)
        //  		break;
					//    x++;
					    data= rd.removeUrl(data);
					    if(data.length()>2){
					    	
					    	
					    	String data1=pc.removeElements(data);
					    	lexicalFeatures=lexicalTechniques.getLexicalFeatures(data);
							tagged=pt.applyPOSTagging(data1);
							tags=pt.countTaggings(tagged);
							int j=0;
					    	features[0]=p.countApostrophe(data);
					    	features[1]=p.countBrackets(data);
					    	features[2]=p.countColon(data);
					    	features[3]=p.countComma(data);
					    	features[4]=p.countDash(data);
					    	features[5]=p.countEllipsis(data);
					    	features[6]=p.countExclamation(data);
					    	features[7]=p.countFullStop(data);
					    	features[8]=p.countQMark(data);
					    	features[9]=p.countSemicolon(data);
					    	features[10]=p.countSlash(data);
					    	j=11;
						    for (int i=0;i<16;i++){
						    	
						    	features[j]=tags[i];
						    	j++;
						    }
						    for (int i=0;i<28;i++){
						    	
						    	features[j]=lexicalFeatures[i];
						    	j++;
						    }
					    	try {
							
					    	/*	for(int i=0;i<j;i++){
					    			
					    			System.out.println("Features are :"+features[i]+" At Index: "+i);
					    			
					    		}*/
					    		createGenderArff.generateArffFile(features,authorInfo);
					    		createAgeArff.generateArffFile(features, authorInfo);
							
					    	} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					    	}
					    }
	                
	                
	      //      } //end if
	            
	       // } //End for
	        
	        createGenderArff.createARFF(Constants.Ouput_DATA_DIR+Constants.ARFF_TEST_Gender);
	        createAgeArff.createARFF(Constants.Ouput_DATA_DIR+Constants.ARFF_TEST_Age);
			
			System.out.println("----------------------------------");
		//return testDataSet;
		 
	 }
	static void extractFeatures()  throws Exception {
		
		POSTagger pt =new POSTagger();
		String tagged="";
		 
		PreprocessClass pc= new PreprocessClass();
		StylometricTechniques lexicalTechniques=new StylometricTechniques();
		
		ReadData rd=new ReadData();
		SyntacticFeatures p=new SyntacticFeatures();
		String data="";
		double features[]=new double[55];
		double tags[]=new double[16];
		double lexicalFeatures[] =new double[28];
		
		CreateARFF createArff=new CreateARFF();
		createArff.initializeARFF();
		ListFilesUtil listFilesUtil = new ListFilesUtil();
        final String folder =Constants.CORPUS_PATH;
        String gender="";
        String[] authorInfo= new String[2];
        
        File[] flist=listFilesUtil.listFiles(folder);
        int x=0;
        for (File file : flist){
            if (file.isFile() && file.getName().endsWith(".txt")){
            	
            	System.out.println("Counttttt is "+x);
                System.out.println("File name is: "+file.getName()+ "AND CUrrent file: "+x);
                try {
                	 // System.out.println("FIlen name is: "+file.getName());
                	    authorInfo=getGenderAndAgeOfAuthor(file.getName());
					    data=listFilesUtil.readFile(file.getName());
				//	    if(x==1)
		          //  		break;
					    x++;
					    data= rd.removeUrl(data);
					    if(data.length()>2){
					    	
					    	
					    	String data1=pc.removeElements(data);
					    	lexicalFeatures=lexicalTechniques.getLexicalFeatures(data);
							tagged=pt.applyPOSTagging(data1);
							tags=pt.countTaggings(tagged);
							int j=0;
					    	features[0]=p.countApostrophe(data);
					    	features[1]=p.countBrackets(data);
					    	features[2]=p.countColon(data);
					    	features[3]=p.countComma(data);
					    	features[4]=p.countDash(data);
					    	features[5]=p.countEllipsis(data);
					    	features[6]=p.countExclamation(data);
					    	features[7]=p.countFullStop(data);
					    	features[8]=p.countQMark(data);
					    	features[9]=p.countSemicolon(data);
					    	features[10]=p.countSlash(data);
					    	j=11;
						    for (int i=0;i<16;i++){
						    	
						    	features[j]=tags[i];
						    	j++;
						    }
						    for (int i=0;i<28;i++){
						    	
						    	features[j]=lexicalFeatures[i];
						    	j++;
						    }
					    	try {
							
					    	/*	for(int i=0;i<j;i++){
					    			
					    			System.out.println("Features are :"+features[i]+" At Index: "+i);
					    			
					    		}*/
					    		createArff.generateArffFile(features,authorInfo);
							
					    	} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					    	}
					    }
					
				//	count++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                
            }
            
        }
        
        createArff.createARFF();
        
		
		System.out.println("----------------------------------");
		
		
	}
	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	static String[] getGenderAndAgeOfAuthor(String author)throws IOException {
		
		
		author=author.substring(0, author.lastIndexOf('.'));
		String[] str= new String[2]; 
    	
		String gender="";
		String age="";
		BufferedReader reader = new BufferedReader(new FileReader(
				Constants.TRUTH_CSV));
 
        // read file line by line
        String line = null;
     
        while ((line = reader.readLine()) != null) {
        	
        	if( line.contains(author)){
        		
        		String [] items = line.split(",");
        		gender=items[1];
        		age= items[2];
        		str=items;
        		return items;
        	}
          
        }
          reader.close();
          return str;
	}
	static void parseData(){
		
		Parser parser = new Parser();
		ReadFile reader = new ReadFile();
		
		try{
			parser.readFiles(Constants.TEST_DATA_DIR);

			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	public void abc(){
		
		URL resource = getClass().getResource(Constants.TEST_DATA_DIR);

	}
	static void testData() throws Exception{
		
		MainClass c = new MainClass();
		c.abc();
		
		parseData();
		ListFilesUtil listFilesUtil = new ListFilesUtil();
        final String folder =Constants.TEST_DATA_DIR;
        String gender="";
        String[] authorInfo= new String[2];
        
        File[] flist=listFilesUtil.listFiles(folder);
        int x=0;
        for (File file : flist){
            if (file.isFile() && file.getName().endsWith(".txt")){
            	
            	System.out.println("Counttttt is "+x);
                System.out.println("File name is: "+file.getName()+ "AND CUrrent file: "+x);
                x++;
                extractFeatureOfTestData(file.getName());
        		WekaTest wk =new WekaTest();
        		wk.testFile(file.getName());
            }
        }
        
       
	}
	public static void main(String[] b) throws Exception  { 

		//TODO Extracting Features
	
		MainClass mClass =new MainClass();
	//	System.out.println("Argument one = "+b[0]);
      //  System.out.println("Argument two = "+b[1]);
		
	//	(new MainClass()).start();
		
		//TODO Genderate Models
	//	testData();
		
		ProcessArff processArff =new ProcessArff();
		//processArff.genderateAgeModel();
		processArff.genderateGenderModel();
	//	processArff.generateGenderLadTreeModel();
		String testFile="4687f84cb54bda94350da7f67e51a823.txt";
		extractFeatureOfTestData(testFile);
		WekaTest wk =new WekaTest();
		wk.testFile(testFile);
		//CSV2Arff cv =new CSV2Arff();
		//cv.convert();
		
	}
	
		
		
	}




