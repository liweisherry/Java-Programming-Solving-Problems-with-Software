
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int aindex = stringb.indexOf(stringa);
        String result = stringb.substring(aindex,stringb.length());
        int pindex = result.indexOf(stringa);
        if (pindex == -1){
            return false;
        }
        return  true;
    }


    public String lastPart(String stringa, String stringb) {
        int aindex = stringb.indexOf(stringa);
        if (aindex == -1){
            return stringb;
        }
        else {
            String result = stringb.substring(aindex+stringa.length(),stringb.length());
            return result;
        }
    }

    public void testing(){
        System.out.println(twoOccurrences("by","A story by Abby Long") );
        System.out.println(twoOccurrences("atg","ctgtatgta") );
        System.out.println(twoOccurrences("a","banana") );
        System.out.println(lastPart("an","banana") );
        System.out.println(lastPart("zoo","banana") );
    }


}
