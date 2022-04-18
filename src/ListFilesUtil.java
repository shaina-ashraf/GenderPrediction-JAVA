

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListFilesUtil {
    /**
     * List all the files and folders from a directory
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            System.out.println(file.getName());
           
        }
    }
    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public File[] listFiles(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int count=0;
        /*for (File file : fList){
            if (file.isFile()){
              //  System.out.println(file.getName());
                try {
					readFile(file.getName());
				//	count++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        */
        return fList;
      //  System.out.println("COunt is "+count);
    }
    /**
     * List all the folder under a directory
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isDirectory()){
                System.out.println(file.getName());
            }
        }
    }
    /**
     * List all files from a directory and its subdirectories
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }
    String readFileFromTestDirectory(String fileName) throws IOException {
		 
	    BufferedReader br = new BufferedReader(new FileReader(Constants.TEST_DATA_DIR+fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	     //   System.out.println("--------------------------------------------------");
	     //   System.out.println(sb.toString());
	       // System.out.println("--------------------------------------------------");
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
    String readFile(String fileName) throws IOException {
		 
	    BufferedReader br = new BufferedReader(new FileReader(Constants.CORPUS_PATH+'/'+fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	     //   System.out.println("--------------------------------------------------");
	     //   System.out.println(sb.toString());
	       // System.out.println("--------------------------------------------------");
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
    /*
    public static void main (String[] args){
        ListFilesUtil listFilesUtil = new ListFilesUtil();
        final String folder ="D:/CIIT-LHR/Research/data/corpus";
         
        listFilesUtil.listFiles(folder);
    }
    */
}