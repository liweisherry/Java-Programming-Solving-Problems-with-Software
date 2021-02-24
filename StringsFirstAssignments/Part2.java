
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, int startCodon, int stopCodon){

        int indexStart = startCodon;
        int indexStop = stopCodon;
        if (indexStart == -1 || indexStop == -1){
            return "";
        }
        if (dna != dna.toLowerCase()){
            dna.toUpperCase();
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
        findSimpleGene(a,0,7);
        System.out.println(b);
        findSimpleGene(b,0,7);
        System.out.println(c);
        findSimpleGene(c,0,7);
        System.out.println(d);
        findSimpleGene(d,0,8);
        System.out.println(e);
        findSimpleGene(e,0,9);
    }
}
