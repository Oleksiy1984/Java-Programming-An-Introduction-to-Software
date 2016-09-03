import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ParsingExportData {
    public static void main(String[] args) {

        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();

        
        System.out.print(countryInfo(parser, "Nauru"));
        
        //listExportersTwoProducts(parser, "gold", "diamonds");
        
        //numberOfExporters(parser, "sugar");
        
        //bigExporters(parser, "$999,999,999,999");
    }

    /**
     * Finds and returns a string of information about the country
     * @param parser
     * @param countryForSearch
     */
    public static String countryInfo(CSVParser parser, String countryForSearch) {


        String country;
        String exports = null;
        String value = null;
        String response;

        for (CSVRecord record : parser) {
            country = record.get("Country");
            if (country.equals(countryForSearch)) {
                exports = record.get("Exports");
                value = record.get("Value (dollars)");
                countryForSearch=country;
            }

        }
        if(exports!=null) {
            response = String.format("%s:  %s,  %s", countryForSearch, exports, value);
        }
        else
        response = "NOT FOUND";

        return response;
    }

    /**
     * Prints the names of all the countries that have exportItem1 and exportItem2
     *
     * @param parser
     * @param exportItem1
     * @param exportItem2
     */
    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {

        for (CSVRecord record : parser) {

            String country = record.get("Country");
            String exports = record.get("Exports");

            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(country);
            }
        }


    }

    /**
     * This method returns the number of countries that export exportItem
     *
     * @param parser
     * @param exportItem1
     */
    public static void numberOfExporters(CSVParser parser, String exportItem1) {

        int counter = 0;

        for (CSVRecord record : parser) {
            String exports = record.get("Exports");

            if (exports.contains(exportItem1)) {
                counter ++;
            }
        }

        System.out.println(counter);

    }

    public static void bigExporters(CSVParser parser, String amount) {

        for (CSVRecord record : parser) {

            String country = record.get("Country");
            String value = record.get("Value (dollars)");

            if (value.length() > amount.length()) {
                System.out.println(country + ":" + value);
            }
        }
    }
}
