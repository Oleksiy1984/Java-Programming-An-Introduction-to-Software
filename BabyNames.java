
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

/*
 * providing data on baby names from the United States and 
 * you will answer questions about this data. 
 * The data files give information on the first names of people born 
 * in a particular year. We have data from 1880 through 2014 on both boys and girls names.
 */
 
public class BabyNames {
    public static void main(String[] args){

        BabyNames miniP = new BabyNames();

// Print out number of girls/boys and total
       // System.out.println("Printout number of girls and boys" );
       // miniP.totalBirths();

      
        // getRank() that has three parameters:
        //an integer named year, a string named name, and a string named gender
        //(F for female and M for male).
        //This method returns the rank of the name in the file for the given gender
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = miniP.getRank(year, name, gender);
        System.out.println("The name " + name + " ranks " + rank + " at year " + year);

        /*
        int year = 1980;
        int rank = 350;
        String gender = "F";
        System.out.println("Q#5: Select year " + year +". ");
        String rankName = miniP.getName(year, rank, gender);
        System.out.println("The " + gender + " name ranked " + rank + " is: " + rankName +". " );

        
        int year = 1982;
        int rank = 450;
        String gender = "M";
        System.out.println("Q#5: Select year " + year +". ");
        String rankName = miniP.getName(year, rank, gender);
        System.out.println("The " + gender + " name ranked " + rank + " is: " + rankName +". " );
        
        int year1 = 1974;
        int year2 = 2014;
        String gender = "M";
        String name = "Owen";
        System.out.println("Select year " + year1 +"; And year " + year2 +". ");
        miniP.whatIsNameInYear(name, year1, year2, gender);
       
        String name = "Mich";
        String gender = "M";
        System.out.println("Select all years:");
        int highestRank = miniP.yearOfHighestRank(name, gender);
        System.out.println("The highest rank for the name " + name + " gender " + gender + " is at year " + highestRank );

    
        String name = "Robert";
        String gender = "M";
        System.out.println("Q#11: select all years:");
        double aveRank = miniP.getAverageRank(name, gender);
        System.out.println("The average rank of " + name + " gender " + gender + " is " + aveRank);

     
       int year = 1990;
        String name = "Emily";
        String gender = "F";
        System.out.println("Q13: select year " + year);

        int totalBirthHigher = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("The total " + gender + " births at year " + year + " ranks higher than " + name + " is: " + totalBirthHigher);


        System.out.println("Select year 1990: ");
        year = 1990;
        name = "Drew";
        gender = "M";

        totalBirthHigher = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("The total " + gender + " births at year " + year + " ranks higher than " + name + " is: " + totalBirthHigher);

*/

    }//end of main();

        //method getTotalBirthsRankedHigher that has three parameters: 
		//an integer named year, a string named name, and a string named gender (F for female and M for male). 
		//This method returns an integer, the total number of births of those names with the same gender and same year 
		//who are ranked higher than name
    private static int getTotalBirthsRankedHigher(int year, String name, String gender) {
        // TODO Auto-generated method stub
        FileResource fr = new FileResource();

        int sum = 0;
        for(CSVRecord record : fr.getCSVParser(false)){

            if(record.get(1).equals(gender)){

                if(record.get(0).equals(name)) return sum;

                sum += Integer.parseInt(record.get(2));

            }//end if record euqals gender condition;

        }//end for CSV record record;

        return sum;
    }//end getTotalBirthsRankedHigher() method;
    
    /* getAverageRank()  
	 * @param name
	 * @param gender
	 * @return
	 */
    private double getAverageRank(String name, String gender) {
        // TODO Auto-generated method stub

        //get director
        DirectoryResource dr = new DirectoryResource();
        int fileNum = 0;
        int totalRank = 0;

        for(File fi : dr.selectedFiles()){
            fileNum++;

            //get the file resource
            FileResource fr = new FileResource(fi);

            int pivot = 0;
            int currRank = 0;
            for(CSVRecord record : fr.getCSVParser(false) ){

                if(record.get(1).equals(gender)) {pivot++;

                    if(record.get(0).equals(name)) {

                        currRank = pivot;
                        break;

                    } //end if record.equals name condition;
                }

            }//end for Record loop;

            totalRank += currRank;

        }//end for file loop;

        if(totalRank == 0) return -1;
        else return (double)(totalRank)/fileNum;

    }//end getAverageRank() method;

     /**********
	 * yearOfHighestRank()
	 * @param name
	 * @param gender
	 * @return
	 */
    private int yearOfHighestRank(String name, String gender) {
        // TODO Auto-generated method stub

        //initial year and rank;
        int rank = 1000000;
        int yearHigh = 0;

        //get the directory:
        DirectoryResource dr = new DirectoryResource();

        //get the files
        for(File fi : dr.selectedFiles()){

            //get the name of the file, which contains the year
            String fileName = fi.getName();

            //get the year integer from the name of the file
            int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));

            //get the FileResource
            FileResource fr = new FileResource(fi);
            int currRank = -1;
            int pivot = 0;
            for(CSVRecord record : fr.getCSVParser(false)){

                if(record.get(1).equals(gender)) {

                    pivot++;

                    if(record.get(0).equals(name)) {
                        currRank = pivot;
                        break;
                    }

                }

            }//end for loop;

            //int currRank = getRank(year, name, gender);
            //	System.out.println("  At year " + year + " name " + name + " gender " + gender + " ranks " + currRank + ". ");

            if(currRank != -1 && currRank < rank){
                rank = currRank;
                yearHigh = year;
            }//end if condition;

        }//end for File fi loop;

        return yearHigh;
    }

     /******************
	 * whatIsNameInYear()
	 * get the name of the same popularity in another year, give the same gender;
	 * @param name
	 * @param year
	 * @param year2 
	 * @param gender
	 */
    private void whatIsNameInYear(String name, int year1, int year2, String gender) {
        // TODO Auto-generated method stub


        int rank = getRank(year1, name, gender);
        //System.out.println(name + " ranks " + rank + " at year " + year1);
        String equalName = getName(year2, rank, gender);

        //Isabella born in 2012 would be Sophia if she was born in 2014.
        System.out.println( name + " born in " + year1 + " would be " + equalName + " if she was born in " + year2);

    }//end whatIsNameInYear() method;
    /***********
	 * getName();
	 * given the rank and gender, return a name in a certain file;
	 * @param year
	 * @param rank
	 * @param gender
	 * @return
	 */
    private String getName(int year, int rank, String gender) {
        // TODO Auto-generated method stub

        //get the file
        FileResource fr = new FileResource();

        int pivot = 0;
        //get the CSV file
        for(CSVRecord record : fr.getCSVParser(false)){

            if(record.get(1).equals(gender)){

                pivot ++;
                if(pivot == rank) return record.get(0);
            }//

        }//end for CSV record loop;

        System.out.println("the rank: " + rank + "... The last one rank " + pivot + ".");
        return "NO NMAE";

    }//end getName() method;
    
    
     /**********
     * getRank();
	 * get the rank of a certain name in a file, give the gender;
	 * @param year
	 * @param name
	 * @param gender
	 * @return
	 */
    private int getRank(int year, String name, String gender) {
        // TODO Auto-generated method stub

        //get the file
        FileResource fr = new FileResource();

        int pivot = 0;

        for(CSVRecord record : fr.getCSVParser(false)){

            //for female records
            if(record.get(1).equals(gender)) {

                //the pivot of equal gender ++
                pivot++;
                if(record.get(0).equals(name)) return pivot;

            }

        }//end for CSV record loop;

        return -1;

    }//end getRank() method;

    //method totalBirths, print out number of girls/boys
    private void totalBirths() {
       
        //get the file,
        FileResource fr = new FileResource();

        //get the CSV, parser the CSV
        int numGirl = 0;
        int numBoy = 0;
        int sum = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            String gender = record.get(1);
            int num = Integer.parseInt(record.get(2));

            if (gender.equals("F")) numGirl++;
            else numBoy++;

            sum++;

        }//end for csvrecord loop;

System.out.println(String.format("Girls-%03d, Boys - %03d",numGirl,numBoy));
    }
}//end
