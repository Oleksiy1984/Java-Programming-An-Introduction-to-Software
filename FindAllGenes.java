// Find all genes in a DNA string.
import edu.duke.*;

public class FindAllGenes 
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
* This method print all the genes it finds in DNA.
* This method should repeatedly look for a gene, and if it finds one,
* print it and then look for another gene. 
*/
public static void printAll(String dna){
    String dnaIsLow=dna.toLowerCase();
    int start=0;
    while(true){
        int startATG =dnaIsLow.indexOf("atg", start);
        int endTAG=findStopIndex(dnaIsLow,startATG+3);
        if(startATG==-1){
            break;
        }
        if (endTAG!=dna.length()){
            System.out.println(dna.substring(startATG,endTAG+3).toUpperCase());
            start=endTAG+3;
        }
        else{start = start + 3;}
    }
}

    public static void main( String[] args )
    {printAll("CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA");}
}
