
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryinfo(parser,"Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser,"cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");

    }
    
    public String countryinfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            String c = record.get("Country");
            String export = record.get("Exports");
            String values = record.get("Value (dollars)");
            if (c.contains(country)){
                if (export == ""|| values == ""){   
                       return "NOT FOUND";}
                else{
                     return c+":"+export+":"+values;}
            }
    }
        return "NOT FOUND";
}
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
           for (CSVRecord record: parser){
                  String c = record.get("Country");
                  String export = record.get("Exports");
                  if (export.contains(exportItem1) && export.contains(exportItem2)){
                      System.out.println(c);}
                    }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
         for (CSVRecord record: parser){
            String c = record.get("Country");
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                count++;
            }
            }
           return count;
          
    }
    
    public void bigExporters(CSVParser parser, String amount){
         for (CSVRecord record: parser){
            String c = record.get("Country");
            String values = record.get("Value (dollars)");
            if (values.length() >amount.length()){
                System.out.println(c+" "+values);
    }
}
}
}
