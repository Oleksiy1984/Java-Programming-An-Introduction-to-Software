
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

public class FindColdestAndLowHumidityDay {

    
    public static void main(String[] args) {
        //1
        //System.out.print(lowestHumidityInManyFiles());

        //2
        //FileResource fr = new FileResource("weather-2013-08-10.csv");
        //CSVParser parser = fr.getCSVParser();
        //System.out.print(averageTemperatureInFile(parser));

        //3
        //FileResource fr = new FileResource("weather-2013-09-02.csv");
        //CSVParser parser = fr.getCSVParser();
        //System.out.print(averageTemperatureWithHighHumidityInFile(parser,80));

        //4
        //System.out.print(fileWithColdestTemperature());

        //5
        FileResource fr = new FileResource("weather-2013-01-23.csv");
        CSVParser parser = fr.getCSVParser();
        System.out.print(coldestHourInFile(parser));
    }
    
    //lowestHumidityInFile()
     /**************************************
     * This method returns the CSVRecord that has the lowest humidity
     *
     * @param parser
     */
    public static CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord resultRecord = null;

        for (CSVRecord record : parser) {

            if (record.get("Humidity").equals("N/A")) {
                continue;
            }

            if (resultRecord == null) {
                resultRecord = record;
            } else {

                double humidity = Double.parseDouble(record.get("Humidity"));
                double lowestHumidity = Double.parseDouble(resultRecord.get("Humidity"));
                resultRecord = (humidity < lowestHumidity) ? record : resultRecord;

            }

        }//end for File loop;

        return resultRecord;
    }//end lowestHumidityInFile() method

    //lowestHumidityInManyFiles()
	/****************************
	 * This method returns a CSVRecord that has the lowest humidity over all the files. 
	 * @return 
	 */
    public static CSVRecord lowestHumidityInManyFiles(){

        CSVRecord lowestHumidity = null;
        //get the directory
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles()){
            //get the file
            FileResource fr = new FileResource(file);
            //get the CSV praser
            CSVParser parser = fr.getCSVParser();
            //get the lowest Humidity in that document 'file'
            CSVRecord currLowHumidity = lowestHumidityInFile(parser);
            //compare and assign the CSV record with lower humidity to lowestHumidity;
            if(lowestHumidity == null || 
            Double.parseDouble(currLowHumidity.get("Humidity")) < Double.parseDouble( lowestHumidity.get("Humidity")))
                lowestHumidity = currLowHumidity;

        }//end for File loop;


        return lowestHumidity;

    }//end lowestHumidityInManyFiles() method
    
    //coldestHourInFile()
    /********************
     * Returns the CSVRecord with the coldest temperature in the file and
     * consequently all the information about the coldest temperature
     *
     * @param parser
     */
    public static CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord resultRecord = null;

        for (CSVRecord record : parser) {

            if (resultRecord == null) {
                resultRecord = record;
            } else {
                double temperatureF = Double.parseDouble(record.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(resultRecord.get("TemperatureF"));
                resultRecord = (temperatureF < coldestTemp) ? record : resultRecord;
            }

        }//end for CSVRecord loop;

        return resultRecord;

    }// end coldestHourInFile() method

// fileWithColdestTemperature()
    /**************************
     * should return a string that is the name of the file
     * from selected files that has the coldest temperature
     */
    public static String fileWithColdestTemperature() {

        CSVRecord coldestRecord = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = "";

        for (File f : dr.selectedFiles()) {

            FileResource fr = new FileResource(f);

            CSVRecord record = coldestHourInFile(fr.getCSVParser());

            double recordTemp = Double.parseDouble(record.get("TemperatureF"));

            if (recordTemp == -9999) {
                continue;
            }

            if (coldestRecord == null) {
                coldestRecord = record;
                filename = f.getAbsolutePath();
            } else {
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));

                if (recordTemp < coldestTemp) {
                    coldestRecord = record;
                    filename = f.getAbsolutePath();
                }
            }

        }

        return filename;
    } // end fileWithColdestTemperature() method

   // averageTemperatureInFile()
    /**
     * @param parser
     * @return
     */
    public static double averageTemperatureInFile(CSVParser parser) {

        double averageTemp = 0.0;
        double sum = 0;
        int counter = 0;
        for (CSVRecord record : parser) {

            double recordTemp = Double.parseDouble(record.get("TemperatureF"));

            if (recordTemp == -9999) {
                continue;
            } else {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }

        }

        averageTemp = sum / counter;

        return averageTemp;
    } // end averageTemperatureInFile() method

     //averageTemperatureWithHighHumidityInFile()
    /*******************************************
     * This method returns a double that represents the average temperature of only
     * those temperatures when the humidity was greater than or equal to value
     *
     * @param parser
     * @param level
     */
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, double level) {

        double averageTemp;
        double sum = 0;
        int counter = 0;

        for (CSVRecord record : parser) {

            double humidity = Double.parseDouble(record.get("Humidity"));

            if (humidity >= level) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                counter++;
            }

        }

        averageTemp = sum / counter;

        return averageTemp;

    } // end averageTemperatureWithHighHumidityInFile() method

}//end
