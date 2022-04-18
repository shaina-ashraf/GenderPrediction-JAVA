
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LADTree;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

public class ProcessArff {

	void genderateAgeModel() throws Exception{
		
		// create J48
		 Classifier cls = new J48();
		 
		 // train
		 Instances inst = new Instances(
		                    new BufferedReader(
		                      new FileReader(Constants.AGE_ARFF_FILE)));
		 inst.setClassIndex(inst.numAttributes() - 1);
		 cls.buildClassifier(inst);
		 
		 // serialize model
		 ObjectOutputStream oos = new ObjectOutputStream(
		                            new FileOutputStream(Constants.AGE_Model_FILE));
		 oos.writeObject(cls);
		 oos.flush();
		 oos.close();
		
	}
	void genderateGenderModel() throws Exception{
		
		// create J48
		 Classifier cls = new J48();
		 
		 // train
		 Instances inst = new Instances(
		                    new BufferedReader(
		                      new FileReader(Constants.GENDER_ARFF_FILE)));
		 inst.setClassIndex(inst.numAttributes() - 1);
		 cls.buildClassifier(inst);
		 
		 // serialize model
		 ObjectOutputStream oos = new ObjectOutputStream(
		                            new FileOutputStream(Constants.GENDER_Model_FILE));
		 oos.writeObject(cls);
		 oos.flush();
		 oos.close();
		
	}
	
	void generateGenderLadTreeModel() throws Exception{
		
		// create J48
				 Classifier cls = new LADTree();
				 
				 // train
				 Instances inst = new Instances(
				                    new BufferedReader(
				                      new FileReader(Constants.GENDER_ARFF_FILE)));
				 inst.setClassIndex(inst.numAttributes() - 1);
				 cls.buildClassifier(inst);
				 
				 // serialize model
				 ObjectOutputStream oos = new ObjectOutputStream(
				                            new FileOutputStream(Constants.GENDER_LadTreeModel_FILE));
				 oos.writeObject(cls);
				 oos.flush();
				 oos.close();
	}
void generateAgeRandomForestModel() throws Exception{
		
		// create J48
				 Classifier cls = new LADTree();
				 
				 // train
				 Instances inst = new Instances(
				                    new BufferedReader(
				                      new FileReader(Constants.AGE_ARFF_FILE)));
				 inst.setClassIndex(inst.numAttributes() - 1);
				 cls.buildClassifier(inst);
				 
				 // serialize model
				 ObjectOutputStream oos = new ObjectOutputStream(
				                            new FileOutputStream(Constants.AGE_RANDOMForestModel_FILE));
				 oos.writeObject(cls);
				 oos.flush();
				 oos.close();
	}
	
}
