import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) {

        HashMap<String, ArrayList<Country>> countries = new HashMap();
        String countryContent = readFile("countries.txt");
        String[] countryLines = countryContent.split("\n");

        for (String countryLine : countryLines) {
            String[] columns = countryLine.split("\\|");
            String countryAbbreviation = columns[0];
            String countryName = columns[1];
            Country country = new Country();
            country.abbreviation = countryAbbreviation;
            country.name = countryName;
            String firstLetter = String.valueOf(countryName.charAt(0));
            ArrayList<Country> list = countries.get(firstLetter);

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
            ArrayList<Country> countryList = countries.get(userInputLetter);

        if(countries.containsKey(userInputLetter)) {
            String newLine = "";
            for(Country n :countries.get(userInputLetter)) {
                newLine = newLine + String.format("%s %s\n", n.abbreviation, n.name);

                System.out.println(newLine);
            }
            writeFile(userInputLetter, newLine);
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
