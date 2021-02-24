
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;



public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord lowest = null;
        for (CSVRecord currentrow: parser){
            if (lowest == null){
                lowest = currentrow;
            }
            else{
                double currentTemp = Double.parseDouble(currentrow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowest.get("TemperatureF"));
                if (currentTemp < lowestTemp && currentTemp != -9999){
                    lowest = currentrow;
                }
            }
        }
        return lowest;
    }

    public  void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println(lowest.get("DateUTC") + ": " + lowest.get("TemperatureF"));
        ;
    }

    public String fileWithColdestTemperature(){
        CSVRecord lowest = null;
        DirectoryResource dr = new  DirectoryResource();
        String filename ="";
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            if (lowest == null){
                lowest = currentRow;
                filename = f.getAbsolutePath();
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowest.get("TemperatureF"));
                if (currentTemp < lowestTemp && currentTemp != -9999){
                    lowest = currentRow;
                    filename = f.getAbsolutePath();
                }
            }
        }
        return filename;
    }

    public  void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(filename);
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temperature on that day was " + lowest.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were:");

        for (CSVRecord currentRow : fr.getCSVParser()) {
            // use method to compare two records
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
            ;
        }

    }

    public CSVRecord LowestHumidityInFile(CSVParser parser){
        CSVRecord lowest = null;
        for (CSVRecord currentrow: parser){
            if (lowest == null){
                lowest = currentrow;
            }
            else{
                if (!currentrow.get("Humidity").equals("N/A")){
                    double currentTemp = Double.parseDouble(currentrow.get("Humidity"));
                    double lowestTemp = Double.parseDouble(lowest.get("Humidity"));
                    if (currentTemp < lowestTemp){
                        lowest = currentrow;
                    }
                }

            }
        }
        return lowest;
    }

    public  void  testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = LowestHumidityInFile(parser);
        System.out.println(csv.get("DateUTC") + ": " + csv.get("Humidity"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = LowestHumidityInFile(parser);
            if (lowest == null){
                lowest = currentRow;

            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double lowestTemp = Double.parseDouble(lowest.get("Humidity"));
                if (currentTemp < lowestTemp){
                    lowest = currentRow;

                }
            }
        }
        return  lowest;
    }
    public  void testlowestHumidityInManyFiles() {

        
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("lowest Humidity on that day was " + lowest.get("DateUTC") +lowest.get("Humidity"));
        System.out.println("All the Humidity on the Humidity day were:");

        

    }
    public double averageTemperatureInFile(CSVParser parser){
        double sum =0.0;
        int count = 0;
        for (CSVRecord currentrow: parser){
            double currentTemp = Double.parseDouble(currentrow.get("TemperatureF"));
            sum += currentTemp;
            count++;
            }
        return sum/count;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
    }

    public  double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum =0.0;
        int count = 0;
        for (CSVRecord currentrow: parser){
            double currentTemp = Double.parseDouble(currentrow.get("TemperatureF"));
            double currentHum = Double.parseDouble(currentrow.get("Humidity"));
            if (currentHum>= value){
                sum += currentTemp;
                count++;
            }


        }
        return sum/count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureWithHighHumidityInFile(parser,80));
    }
}
