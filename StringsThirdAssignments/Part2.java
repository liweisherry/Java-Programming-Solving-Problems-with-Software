
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part2 {
       public void testProcessGenes(){
    String dna1 = "aATgTCACtagCaTAaAtgCCCTGAactG"; //genes longer than 9 char
    String dna2 = "atgcacTAGATGAAbTAaATGCaBTGA"; // no genes longer than 9 char
    String dna3 = "atgcacTAGATGAAbTAaATGCaBTGAcccACg"; // some genes C-G ratio higher than .35
    String dna4 = "atgcacTAGATGAAbTAaATGCaBTGAggaggaggG"; // some genes C-G ratio lower than .35
    String dna5 = "atgcacTAGATGAAbTAaATGCaBTGACATgatGCCACTatGaagcacacTGAATGCCATCTGggaatTGATATAATATAAggTTCCC"; // whatever you want
    StorageResource genes = getAllGenes(dna1);
    processGenes(genes);
    genes = getAllGenes(dna2);
    processGenes(genes);
    genes = getAllGenes(dna3);
    processGenes(genes);
    genes = getAllGenes(dna4);
    processGenes(genes);
    genes = getAllGenes(dna5);
    processGenes(genes);
    //printAllGenes(genes);
    //System.out.println("C:G ratio is: " + cgRatio(dna1));
    //System.out.println("CTG codon count is: " + countCTG(dna1));
    }
    public void testRealDna(){
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String dna = fr.asString();
    dna = dna.toUpperCase();
    StorageResource genes = getAllGenes(dna);
    printAllGenes(genes);
    processGenes(genes);
     System.out.println("Number of CTG codones: " + countCTG(dna));
    }
    
    public void printAllGenes(StorageResource genes){
        for (String g : genes.data()){
        System.out.println(g);
        } 
    }
    
    public void processGenes(StorageResource genes){
        int countChar = 0;
        int countGenes = 0;
        System.out.println("Genes strands with length greater than 60:");
        for(String sr : genes.data()){
            if(sr.length() > 60){
            countChar += 1;
            }
            countGenes += 1;
        }
        System.out.println("Total # of Genes: " + countGenes);
        System.out.println("Total # with length > 60: " + countChar);
        
        int countCG = 0;
        //System.out.println("Genes with C-G ratio higher than 0.35");
        for(String sr : genes.data()){
            if(cgRatio(sr) > 0.35){
            //System.out.println(sr + ", with a C-G ratio of: " + cgRatio(sr));
            countCG += 1;
            }
        }
        System.out.println("Total # with CG ratio > 0.35: " + countCG);
        
        String prevGene = "";
        String longestGene = "";
        for(String sr : genes.data()){
            if(sr.length() > longestGene.length()){
                longestGene = sr;
            }
        } 
        System.out.println("Longest gene is: " + longestGene);
        System.out.println("Length of longest gene is: " + longestGene.length());
       
    }
    
     public float cgRatio(String dna){
        dna = dna.toUpperCase();
        float c = 0;
        int indexC = dna.indexOf("C");
        while (indexC != -1){
            c += 1;
            indexC = dna.indexOf("C", indexC + 1);
        }
        //System.out.println(c);
        float g = 0;
        int indexG = dna.indexOf("G");
        while (indexG != -1){
            g += 1;
            indexG = dna.indexOf("G", indexG + 1);
        }
        //System.out.println(g);
        float ratio = (c+g)/dna.length();
        return ratio;
        
    }
    
    public int countCTG(String dna){
    dna = dna.toUpperCase();
    int ctg = 0;
    int indexCTG = dna.indexOf("CTG");
    while (indexCTG > 0){
        ctg = ctg + 1;
        indexCTG = dna.indexOf("CTG", indexCTG + 3);
    }
    return ctg;
    }
    public int findStopCodon(String dnaStr,
                             int startIndex, 
                             String stopCodon){                                 
            int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
            while (currIndex != -1 ) {
               int diff = currIndex - startIndex;
               if (diff % 3  == 0) {
                   return currIndex;
               }
               else {
                   currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
               }
            }
            return -1;
        
    }
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex + 3);
    }
    public StorageResource getAllGenes(String dna) {
      //create an empty StorageResource, call it geneList
      StorageResource geneList = new StorageResource();
      //Set startIndex to 0
      int startIndex = 0;
      //Repeat the following steps
      while ( true ) {
          //Find the next gene after startIndex
          String currentGene = findGene(dna, startIndex);
          //If no gene was found, leave this loop 
          if (currentGene.isEmpty()) {
              break;
          }
          //Add that gene to geneList
          geneList.add(currentGene);
          //Set startIndex to just past the end of the gene
          startIndex = dna.indexOf(currentGene, startIndex) +
                       currentGene.length();
        }
      //Your answer is geneList
      return geneList;
    }
    public void testOn(String dna) {
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    public void test() {
        //      ATGv  TAAv  ATG   v  v  TGA   
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        //      ATGv  v  v  v  TAAv  v  v  ATGTAA
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
   
    }
}

