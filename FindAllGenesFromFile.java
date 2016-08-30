// Find all genes from File.
import edu.duke.*;
public class FindAllGenesFromFile 
{
/**
* Find a valid stop codon in dna that occurs after index.
* If no valid stop codon found, return dna.length().
* @param dna is String being searched.
* @param index is index where search starts.
* @return index of beginning of a valid stop codon,
* or dna.length() if no valid codon.
*/
    public static int findStopIndex (String dna,int index) {

        int stop1=dna.indexOf("tga",index);
        if (stop1==-1||(stop1-index)%3!=0){
            stop1=dna.length();

        }
        int stop2=dna.indexOf("taa",index);
        if (stop2==-1||(stop2-index)%3!=0){
            stop2=dna.length();
        }
        int stop3=dna.indexOf("tag",index);
        if (stop3==-1||(stop3-index)%3!=0){
            stop3=dna.length();
        }
        return Math.min(stop1,Math.min(stop2,stop3));
    }
    
/**
* This method create and return a StorageResource containing the genes found.
*/
public static StorageResource storeAll(String dna){
    StorageResource genes = new StorageResource();
    int start=0;
    String dnaIsLow=dna.toLowerCase();
        while(true){
        int startATG =dnaIsLow.indexOf("atg", start);
        int endTAG=findStopIndex(dnaIsLow,startATG+3);
        if(startATG==-1){
            break;
        }
        if (endTAG!=dna.length()){
            
            genes.add( dna.substring(startATG, endTAG+3) );
            start=endTAG+3;
        }
        else{start = start + 3;}

    }
    return genes;
}

/**
 * This method reads the file, which is a large string
 * of DNA and calls storeAll to find and store all the genes in this large
 * strand of DNA. It then prints the number of genes found.
 * /
    public static void testStorageFinder() {
        FileResource dnaFile = new FileResource();
        String source = dnaFile.asString();
        StorageResource genesFound = storeAll(source);
        System.out.println( "Number of genes found: "+genesFound.size() );
        printGenes( genesFound );
    }
    
    /**
     * This method returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA/
     * /
    public static float cgRatio( String dna ) {
        String dnaLow = dna.toLowerCase();
        int cgCount = 0;
        int start = 0;

        while (true) {
            int pos = dnaLow.indexOf("c", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }

        while (true) {
            int pos = dnaLow.indexOf("g", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }

        return ( (float) cgCount ) / dna.length();

    }
/**
 * prints all the Strings that are longer than 60 characters
 * prints the number of Strings that are longer than 60 characters
 * prints the Strings whose C-G-ratio is higher than 0.35
 * prints the number of strings whose C-G-ratio is higher than 0.35
 */
    public static void printGenes( StorageResource sr ) {

        int sixtyCharQty = 0;
        int highCgRatioQty = 0;
        float cgRatioConst = (float) 0.35;

        for ( String s : sr.data() ) {

            if ( s.length() > 60 ) {
                System.out.println( "String longer than 60 characters: "+s );
                sixtyCharQty++;
            }


            if ( cgRatio(s) > cgRatioConst ) {
                System.out.println( "String with C-G-ratio higher than 0.35: "+s );
                highCgRatioQty++;
            }

        }

        System.out.println( "the number of Strings that are longer than 60 characters: "+sixtyCharQty );
        System.out.println( "Strings with C-G-ratio higher than 0.35: "+highCgRatioQty );

    }

  public static void main( String[] args )
    {testStorageFinder();}
}
