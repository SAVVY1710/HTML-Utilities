import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *	File Utilites for reading and writing
 *
 *	@author	Sai Avula
 *	@since August 24, 2024
 */

 public class FileUtils
 {
	 
	 /**
	  * Opens a file to read using the Scanner class.
	  * @param fileName	name of the file to open
	  * @return 		The Scanner object to the file
	  */
	  public static java.util.Scanner openToRead(String fileName)
	  {
		java.util.Scanner input = null;
		try
		{
			input = new java.util.Scanner(new java.io.File(fileName));
		}
		catch(java.io.FileNotFoundException e)
		{
			System.err.println("ERROR: Cannot open"+fileName+
			" for reading.");
			System.exit(-1);
		}
		return input;
		  
	  }
	  
	  /**
	  * Opens a file to write using the PrintWriter class.
	  * @param fileName	name of the file to open
	  * @return 		The PrintWriter object to the file
	  */
	  public static PrintWriter openToWrite(String fileName)
	  {
		  PrintWriter output = null;
		  try
		{
			output = new PrintWriter(new File(fileName));		
		}
		catch(FileNotFoundException e)
		{
			System.err.println("ERROR: Cannot open"+fileName+
			" for writing.");
			System.exit(-1);
		}
		return output;
	  }
	  
	  
 }
 
