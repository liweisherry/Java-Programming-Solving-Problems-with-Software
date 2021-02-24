
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

import javax.rmi.PortableRemoteObject;

public class Part1 {

        public int findStopCodon(String dna, int startIndex,String stopCodon){
            int index = dna.indexOf(stopCodon,startIndex+3);
            while (index != -1){
                if ((index-startIndex) %3 ==0){
                    return index;
                }
                else{
                    index = dna.indexOf(stopCodon,index+1);
                }
            }

            return -1;
        }

        public String findGene(String dna,int where){
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

        public StorageResource getAllGenes(String dna){
            StorageResource geneList = new StorageResource();
            int startIndex = 0;
            while (true){
                String currentGene = findGene(dna,startIndex);
                if (currentGene.isEmpty()){
                    break;
                }
                geneList.add(currentGene);
                startIndex = dna.indexOf(currentGene,startIndex+currentGene.length());
            }
            return geneList;
        }

    public float cgRatio(String dna){
        int countCg = 0;
        int currentindex = 0;
        
        while(true){
            if (dna.substring(currentindex,currentindex+1) =="C" || dna.substring(currentindex,currentindex+1) =="G"){
                countCg ++;
            }
            currentindex ++;
            if ( currentindex>=dna.length()){
                break;
            }
        }
        float fraction = ((float) countCg)/dna.length();
        return fraction;
    }

    public int countCTG(String dna){
        int count = 0;
        int currentindex = 0;
        int mindex =0;
        while (true){
            mindex = dna.indexOf("CTG",currentindex);
            if (mindex == -1){
                break;
            }
            count++;
            currentindex = mindex+3;
        }
        return count;
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

    public void testProcessGenes(){
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource geneList = getAllGenes(dna);
        System.out.println(geneList.data());
        processGenes(geneList);
    }
    
    public void test(){
    String dna1 = "aATgTCACtagCaTAaAtgCCCTGAactG"; //genes longer than 9 char
    String dna2 = "atgcacTAGATGAAbTAaATGCaBTGA"; // no genes longer than 9 char
    String dna3 = "atgcacTAGATGAAbTAaATGCaBTGAcccACg"; // some genes C-G ratio higher than .35
    String dna4 = "atgcacTAGATGAAbTAaATGCaBTGAggaggaggG"; // some genes C-G ratio lower than .35
    String dna5 = "atgcacTAGATGAAbTAaATGCaBTGACATgatGCCACTatGaagcacacTGAATGCCATCTGggaatTGATATAATATAAggTTCCC"; // whatever you want
    countCTG(dna1);
    cgRatio(dna1);
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
}
