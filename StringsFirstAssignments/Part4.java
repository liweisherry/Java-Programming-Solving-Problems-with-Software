
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part4 {
    private String name = "youtube.com";
    public void readURL(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.lines()) {
            // print or process s
            String convert = s.toLowerCase();
            if (convert.indexOf(name) != -1){
                int indexstart = s.lastIndexOf("\"", convert.indexOf(name));
          
                int indexend = s.indexOf("\"",convert.indexOf(name));
                System.out.println(s.substring(indexstart,indexend));
            }

        }
    }
}
