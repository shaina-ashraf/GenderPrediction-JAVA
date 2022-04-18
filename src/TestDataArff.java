

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;

/**
/**
 * Generates a little ARFF file with different attribute types.
 *
 * 
 */
public class TestDataArff {

    FastVector      attributes;
    FastVector      attsRel;
    FastVector      attVals;
    FastVector      ageVals;
    FastVector      attValsRel;
    Instances       dataSet;
    String          author;
    Instances       dataRel;
    double[]        values;
    double[]        valsRel;
    int             i;
    
    void readTestFile(){
    	
    	
    }
    
    void initializeARFF(Boolean gender){
    	
    	// 1. set up attributes
    	// Syntactic features
    	
        attributes = new FastVector();
        attributes.addElement(new Attribute(Constants.APOSTROPHE)); //0
        attributes.addElement(new Attribute(Constants.BRACKETS)); //1
        attributes.addElement(new Attribute(Constants.COLON));  //2
        attributes.addElement(new Attribute(Constants.COMMA));   //3
        attributes.addElement(new Attribute(Constants.DASH));  //4
        attributes.addElement(new Attribute(Constants.ELLIPSIS)); //5
        attributes.addElement(new Attribute(Constants.EXCLAIMATION)); //6
        attributes.addElement(new Attribute(Constants.FULLSTOP));    //7
        attributes.addElement(new Attribute(Constants.QUESTION_MARK)); //8
        attributes.addElement(new Attribute(Constants.SEMICOLON));    //9
        attributes.addElement(new Attribute(Constants.SLASH));    //10
        
        attributes.addElement(new Attribute(Constants.NOUNS));   //11
        attributes.addElement(new Attribute(Constants.ADJECTIVE)); //12
        attributes.addElement(new Attribute(Constants.ADVERBS));   //13
        attributes.addElement(new Attribute(Constants.VERBS));      //14
        attributes.addElement(new Attribute(Constants.CARDINALS));   //15
        attributes.addElement(new Attribute(Constants.PREPOSITIONS));  //16
        attributes.addElement(new Attribute(Constants.PARTICLES));    //17
        attributes.addElement(new Attribute(Constants.SYMBOLS));  //18
        attributes.addElement(new Attribute(Constants.CONJUCTION));  //19
        attributes.addElement(new Attribute(Constants.DETERMINER));  //20
        attributes.addElement(new Attribute(Constants.INTERROGATIVES)); //21
        attributes.addElement(new Attribute(Constants.FOREIGNWORDS));  //22
        attributes.addElement(new Attribute(Constants.PRONOUNS));    //23
        
        attributes.addElement(new Attribute(Constants.UNIGRAM_DENSITY)); //24
        attributes.addElement(new Attribute(Constants.BIGRAM_DENSITY));  //25
        attributes.addElement(new Attribute(Constants.TRIGRAM_DENSITY)); //26
        
        // Lexical Features
        attributes.addElement(new Attribute(Constants.characterCount)); // 27
        attributes.addElement(new Attribute(Constants.characterCountWithoutSpaces)); //28
        attributes.addElement(new Attribute(Constants.ratioOfDigits));  //29
        attributes.addElement(new Attribute(Constants.ratioOfLetters)); //30
        attributes.addElement(new Attribute(Constants.ratioOfUpperCaseLetters)); //31
        attributes.addElement(new Attribute(Constants.ratioOfWhiteSpacesToN)); //32
        attributes.addElement(new Attribute(Constants.ratioOfTabsToN));  //33
        attributes.addElement(new Attribute(Constants.ratioOfSpecialCharacterToN)); //34
        attributes.addElement(new Attribute(Constants.numberOfUpperCaseCharacters)); //35
        attributes.addElement(new Attribute(Constants.digitCount)); //36
        attributes.addElement(new Attribute(Constants.numberOfWhiteSpaces)); //37
        attributes.addElement(new Attribute(Constants.numberOfTabs)); //38
        attributes.addElement(new Attribute(Constants.percentageOfQuestionSentences)); //39
        attributes.addElement(new Attribute(Constants.percentageOfPunctuationCharacters)); //40
        attributes.addElement(new Attribute(Constants.percentageOfSemiColons)); //41
        attributes.addElement(new Attribute(Constants.percentageOfCommas)); //42
        attributes.addElement(new Attribute(Constants.averageWordLength)); //43
        attributes.addElement(new Attribute(Constants.yuleKMeasure)); //44
        attributes.addElement(new Attribute(Constants.hapaxLegomena)); //45
        attributes.addElement(new Attribute(Constants.totalNumberofWords)); //46
        attributes.addElement(new Attribute(Constants.averageSentenceLengthinCharacters)); //47
        attributes.addElement(new Attribute(Constants.ratioOfWordsWithLength3)); //48
        attributes.addElement(new Attribute(Constants.averageSentenceLengthinWords)); //49
        attributes.addElement(new Attribute(Constants.totalUniqueWords)); //50
        attributes.addElement(new Attribute(Constants.simpsonDMeasure)); //51
        attributes.addElement(new Attribute(Constants.sichelSMeasure)); //52
        attributes.addElement(new Attribute(Constants.brunetWMeasure));  //53
        attributes.addElement(new Attribute(Constants.honoreRMeasure)); //54
        
        if(gender){
        
        attVals = new FastVector(2);
        for (i = 0; i < 2; i++){
        	
        	if(i==0)
        		attVals.addElement("MALE");
        	else
        		attVals.addElement("FEMALE");
        	
        	
        }
        Attribute classAttribute = new Attribute("Gender", attVals); 
        attributes.addElement(classAttribute);   //55
        dataSet = new Instances("GENDER", attributes, 0);
        
        }else{
        
        ageVals = new FastVector(5);
        ageVals.addElement("18-24");
        ageVals.addElement("25-34");
        ageVals.addElement("35-49");
        ageVals.addElement("50-64");
        ageVals.addElement("65-xx");
        
        Attribute ageAttribute = new Attribute("Age", ageVals); 
        attributes.addElement(ageAttribute);   //56
        dataSet = new Instances("Age", attributes, 0);
        
        }
        // 2. create Instances object
        
    
    }
    Instances generateArffFile(double[] features, String[] authorInfo)  throws Exception {
    	
    	
        values = new double[dataSet.numAttributes()]; 
       // Syntactic features
        for (int i=0;i<55;i++){
        	
        	double value=features[i];
        	DecimalFormat format = new DecimalFormat("0.00"); 
            String txt=format.format(features[i]);
        	value = Double.parseDouble(txt);
        	
        	
        	values[i] = value;
        }
        
        //Lexical Features
        
        // Set gender Classifier (male/female)
   
      //  values[55] = attVals.indexOf(authorInfo[1]);
        // set Age Group Classifier 
     //   values[56]= ageVals.indexOf(authorInfo[2]);
        
        dataSet.add(new Instance(1.0, values));
            
        values = new double[dataSet.numAttributes()]; 
        return dataSet;
      
    }
    void createARFF(String file)  throws Exception {
    	
    	 BufferedWriter writer = new BufferedWriter(new FileWriter(file));
         writer.write(dataSet.toString());
         writer.flush();
         writer.close();
         // 4. output data
         System.out.println(dataSet);
    }
    
    
}
