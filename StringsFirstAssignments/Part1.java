
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        
        int indexStart = dna.indexOf("ATG");
        int indexStop = dna.indexOf("TAA",indexStart+3);
        if (indexStart == -1 || indexStop == -1){
            return "";
        }
        String s = dna.substring(indexStart, indexStop+3);
        int length = s.length();
        if ((length-6) % 3 == 0){
            return s;
        }
        return "";
    }
    
    public void testSimpleGene(){
        String a = "ATTGATAA";
        String b = "ATGGATTA";
        String c = "GGATATAT";
        String d = "ATGGATTTA";
        String e = "ATGGATTTTA";
        System.out.println(a);
        findSimpleGene(a);
        System.out.println(b);
        findSimpleGene(b);
        System.out.println(c);
        findSimpleGene(c);
        System.out.println(d);
        findSimpleGene(d);
        System.out.println(e);
        findSimpleGene(e);
    }
}
