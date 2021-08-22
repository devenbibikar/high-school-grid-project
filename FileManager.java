import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

//CREDIT: w3schools.com/java/java_files_read.asp for CreateFile, WriteToFile, & ReadFile functions

public class FileManager {
	
	Path currentRelativePath = Paths.get("");
	String rawParentFolderName = currentRelativePath.toAbsolutePath().toString();
	String parentFolderName = rawParentFolderName.replace("\\", "\\\\") + "\\\\Presets\\\\";
	
      
public void CreateFile(String fileName)
{
	try {
    File myObj = new File(parentFolderName + fileName);
	    if (myObj.createNewFile()) 
	    {
	      System.out.println("File created: " + myObj.getName());
	    } 
	    else 
	    {
	      System.out.println("File already exists.");
	    }
  } catch (IOException e) 
	{
    System.out.println("An error occurred.");
    e.printStackTrace();
  }

}



public void WriteToFile(String fileName, String text) {
	    try {
	      FileWriter myWriter = new FileWriter(parentFolderName + fileName);
	      myWriter.write(text);
	      myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	    } 
	    catch (IOException e) 
	    {
	      System.out.println("Error occurred.");
	      e.printStackTrace();
	    }
	  }


//read format: Coordinates: #,# ;; Direction: [n] ;; {arraylist} 
public String ReadFile(String fileName)
{
	String information = "ERROR";
	try {
		File fileInfo = new File(parentFolderName + fileName);
		Scanner myReader = new Scanner(fileInfo);
		while (myReader.hasNextLine())
		{
			information = myReader.nextLine();
		}
		
		myReader.close();
	}
	catch (FileNotFoundException e){
		{
			System.out.println("Error occured");
			e.printStackTrace();
		}
	}
	
	return information; //returns ERROR or file info
}
 
public void listFiles()
{
	String[] allFileNames;
	
	File fileLocation = new File(parentFolderName);
	allFileNames = fileLocation.list();
	
	System.out.println("The following files are availible:");
	for (int i = 0; i < allFileNames.length; i++)
	{
		System.out.println(allFileNames[i]);
	}
}
}//last one