import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Write a description of MiniProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class MiniProject {
        public void totalBirths(FileResource fr){
            
            int totalBirths = 0;
            int numGirl = 0;
            int numBoy = 0;
            int totalnum = 0;
            for (CSVRecord rec : fr.getCSVParser(false)){
                
                int numBorn = Integer.parseInt(rec.get(2));
                 totalBirths += numBorn;
                 if (rec.get(1).equals("F")){
                     numGirl++;
                 }else {
                     numBoy++;
                 }
                 totalnum++;
            }
            System.out.println("total births = "+totalBirths);
            System.out.println("the number of girls  = "+numGirl);
            System.out.println(" the number of boys = "+numBoy);
            System.out.println("total names  = "+totalnum);
        }

        public void testTotalBirths () {
            //FileResource fr = new FileResource();
            FileResource fr = new FileResource();
            totalBirths(fr);
        }

        public int getRank(int year, String name,String gender){
            FileResource fr=  new FileResource();
            int rank = 0;
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)){
                    rank ++;
                    if (rec.get(0).equals(name)){
                        return  rank;
                    }

              }
            }
            return -1;
        }

        public void testgetRank() {
            int rank = getRank(1960,"Emily", "F");
            System.out.println(rank);
        }

        public String getName(int year, int rank,String gender){
            int count = 0;
            FileResource fr=  new FileResource();
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    count ++;
                    if (count == rank) {
                        return rec.get(0);
                    }
                }
            }

            return "No name";
        }
        public void testgetName() {
            String name = getName(1980, 350, "F");
            System.out.println(name);

        }

        public void whatIsNameInYear(int year, int newYear,String gender,String name){
            int rank = getRank(year,name,gender);
            if (rank != -1) {
                System.out.println(getName(newYear, rank, gender) + " if she was born in " + newYear);
            }
            else{
                System.out.println("No name");
            }
        }

        public void testwhatIsNameInYear() {
            whatIsNameInYear(1974, 2014, "M","Owen");
        }
        public int getRank2(int year, String name, String gender, FileResource fr) {
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
        public int yearOfHighestRank(String name, String gender){
            DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        String yob = null;
        int year = 0;
        int highestYear = 0;
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            yob = fName.substring(3,7);
            year = Integer.parseInt(yob);
            
            int currentRank = getRank2(year,name, gender,fr);
            
            
            if (currentRank != -1) {
                if (highestRank == 0) {
                highestRank = currentRank;
            }
                if (currentRank < highestRank) {
                    highestRank = currentRank;
                    highestYear = year;
                }
            }  
            System.out.println(highestYear);
        
        }
        return highestYear;
            }

            public void testyearOfHighestRank() {
                int year = yearOfHighestRank("Mich", "M");
                System.out.println(year);
            }

            public double getAverageRank(String name, String gender){
                DirectoryResource dr = new DirectoryResource();
                double sumRank = 0.0;
                int count= 0;
                for (File f : dr.selectedFiles()) {
                    FileResource fr = new FileResource(f);
                    String filename = f.getName();
                    int year =Integer.parseInt(filename.substring(3,7));
                    if (getRank2(year,name,gender,fr) !=-1){
                        sumRank += getRank2(year,name,gender,fr);
                        count++;
                    }

                }
                return  sumRank/count;
            }

            public void testgetAverageRank() {
                double averageRank = getAverageRank("Robert", "M");
                System.out.println(averageRank);

            }

            public  int getTotalBirthsRankedHigher(int year, String name, String gender){
                int total = 0;
                FileResource fr = new FileResource();
                int rank = getRank2(year,name,gender,fr) ;
                for (CSVRecord rec : fr.getCSVParser(false)) {
                    int numBorn = Integer.parseInt(rec.get(2));
                   
                    if (rec.get(1).equals(gender)){
                        rank--;
        		if (rank > 0) {
        		    total += numBorn;
                        }
                    }
                }
                return total;
            }
            public void testgetTotalBirthsRankedHigher() {
                int totalBirth = getTotalBirthsRankedHigher(1990, "Emily", "F");
                System.out.println(totalBirth);
                totalBirth = getTotalBirthsRankedHigher(1990, "Drew", "M");
                System.out.println(totalBirth);
            }
            
               public  int getTotalNumbRankedHigher(int year, String name, String gender){
                int total = 0;
                FileResource fr = new FileResource();
                for (CSVRecord rec : fr.getCSVParser()) {
                    int numBorn = Integer.parseInt(rec.get(2));
                    int rank = getRank2(year, rec.get(0),rec.get(1),fr);
                    if (rank < getRank2(year,name,gender,fr) && rank !=-1 
                        && rec.get(1).equals(gender)){
                        total += 1;
                    }
                }
                return total;
            }
            
             public void testgetTotalNumbRankedHigher() {
                int totalBirth = getTotalNumbRankedHigher(1990, "Emily", "F");
                System.out.println(totalBirth);
                totalBirth = getTotalNumbRankedHigher(1990, "Drew", "M");
                System.out.println(totalBirth);
            }
            

}


