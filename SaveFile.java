import java.io.*;
import java.util.ArrayList;

public class SaveFile {
    
    /**
     * Writes the higher score to the record file
     * Pre: higher score and winning player's name
     * Post: none
     */
    public static void Save(int score, String name) throws FileNotFoundException, IOException {
        String person = (name + ": " + score);
        ArrayList<String> list = new ArrayList<String>();
        
        
        File textFile = new File("H:\\Records.txt");
        FileReader in;
        BufferedReader readFile;
        String lineOfText;
        
        try {
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);
            while ((lineOfText = readFile.readLine()) != null) {
                System.out.println(lineOfText);
                list.add(lineOfText);
            }
            readFile.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist or could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem reading file."); 
            System.err.println("IOException: " + e.getMessage());
        }
           
        System.out.println(person);
        list.add(person);
        
         try {    
             FileWriter records = new FileWriter("H:\\Records.txt");
             
               for (int i = 0; i < list.size(); i++) {
                   records.write("\n" + list.get(i));
               }
            records.close();    
         } catch(Exception e) {
             System.out.println(e);
         }
    }
}
