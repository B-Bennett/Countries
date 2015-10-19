import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) {

        HashMap<String, ArrayList<Country>> countries = new HashMap();
        String countryContent = readFile("countries.txt");//fileContent could have named
        String[] countryLines = countryContent.split("\n");

        for (String countryLine : countryLines) {  //to loop over array
            String[] columns = countryLine.split("\\|"); //parse ing the array. using the pipe to split line
            String countryAbbreviation = columns[0]; //abbrev in front of pipe
            String countryName = columns[1]; //name back of pipe
            Country country = new Country(); //create a country object (abbrev, name)
            country.abbreviation = countryAbbreviation;
            country.name = countryName;

            String firstLetter = String.valueOf(countryName.charAt(0)); //get the first letter from the name
            ArrayList<Country> list = countries.get(firstLetter); //create a variable."asking get me all the letters that start with."

            if (list == null) {
                list = new ArrayList();
                list.add(country);
                countries.put(firstLetter, list);
            } else {
                list.add(country);
            }
        }
        Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the first letter of a Country.");
            String userInputLetter = scanner.nextLine();

        if(countries.containsKey(userInputLetter)) {
            String newLine = "";
            for(Country n :countries.get(userInputLetter)) {
                newLine = newLine + String.format("%s %s\n", n.abbreviation, n.name);

                System.out.println(newLine);//print abbreviation & name of country of user input letter.
            }
            writeFile(userInputLetter, newLine);//newLine holds the countries name chosen by the user input letter.
        }
    }

    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        }catch (Exception e) {
            return null;
        }
    }

    static void writeFile(String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        }catch (Exception e) {

        }
    }
}
