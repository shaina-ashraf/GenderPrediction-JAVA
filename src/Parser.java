
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class Parser {

	public void readFiles(String path) throws ParserConfigurationException, SAXException, IOException{
		
		File folder  = new File(path);
		File[] files = folder.listFiles();
		
		for(File file : files){
			
			if(file.isFile() && file.getName().endsWith(".xml")){
				
				this.parseXML(file);
			}
		}
		
	}
	
	private void parseXML(File file) throws ParserConfigurationException, SAXException, IOException{
		
		System.out.println("************* PARSING FILE: "+file.getName()+"*************");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
	
//		doc.getDocumentElement().normalize();				
		
		NodeList  documentList= doc.getElementsByTagName("document");
		
		String finalData = "";
		
		for (int idx=0; idx<documentList.getLength();idx++){
			
			Element documentElement = (Element) documentList.item(idx);
			String data = this.getCharacterDataFromElement(documentElement);
			
			finalData += data +"\r\n";
		}

		String strippedData = finalData.replaceAll("\\<.*?>","");
		this.write(file.getName(), strippedData);
		
		System.out.println("************* PARSED *************");
		
	}	
	private String getCharacterDataFromElement(Element e) {

	    NodeList list = e.getChildNodes();
	    String data;

	    for(int index = 0; index < list.getLength(); index++){
	    	
	        if(list.item(index) instanceof CharacterData){
	        	
	            CharacterData child = (CharacterData) list.item(index);
	            data = child.getData();

	            if(data != null && data.trim().length() > 0)
	                return child.getData();
	        }
	    }
	    return "";
	}
	
	private void write (String filename, String data) throws IOException{
		
		String[] parts = filename.split("\\.");
		String newName = parts[0]+".txt";
		
		File file = new File(Constants.TEST_DATA_DIR+newName);
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter(file));
		outputWriter.write(data);
		outputWriter.flush();  
		outputWriter.close();  
	}
	
	
}
