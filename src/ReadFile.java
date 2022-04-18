
import java.io.*;

public class ReadFile {

		
	public void readFiles(String path) throws IOException{
		
		File folder  = new File(path);
		File[] files = folder.listFiles();
		
		for(File file : files){
			
			if(file.isFile() && file.getName().endsWith(".arff")){
			
				this.readContents(file);
			}
		}
		
		
	}
	
	private void readContents(File file) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	line = line.replaceAll(",,$", "");
		    	line=line.replaceAll("^,", "");
			    System.out.println(line);

		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    this.write(file.getName(), everything);
		} finally {
		    br.close();
		}
	}
	
	
private void write (String filename, String data) throws IOException{
		
//		String[] parts = filename.split("\\.");
//		String newName = parts[0]+".txt";
		
		File file = new File("arffs_out/"+filename);
		
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter(file));
		outputWriter.write(data);
		outputWriter.flush();  
		outputWriter.close();  
	}
}
