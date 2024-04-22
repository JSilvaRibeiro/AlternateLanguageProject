import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Read the CSV file and create Cell objects
        List<Cell> cellList = readCsvFile("src/main/java/cells.csv");

        // Print details of each cell
        for (Cell cell : cellList) {
            System.out.println(cell.toString());
        }

        // Additional testing or operations as required...
      // Question 1: Company with highest average body weight
        String companyWithHighestAvgWeight = DataAnalysis.findCompanyWithHighestAvgBodyWeight(cellList);
        System.out.println("Company with highest average body weight: " + companyWithHighestAvgWeight);

        // Question 2: Phones announced in one year and released in another
        List<Cell> phonesWithDifferentYears = DataAnalysis.findPhonesWithDifferentAnnouncementAndReleaseYears(cellList);
        System.out.println("Phones announced in one year and released in another:");
        for (Cell cell : phonesWithDifferentYears) {
            System.out.println("OEM: " + cell.getOem() + ", Model: " + cell.getModel());
        }
        System.out.println("Total: " + phonesWithDifferentYears.size());

        // Question 3: Count of phones with only one feature sensor
        long countPhonesWithOneSensor = DataAnalysis.countPhonesWithOneFeatureSensor(cellList);
        System.out.println("Number of phones with only one feature sensor: " + countPhonesWithOneSensor);

        // Question 4: Year with most phones launched after 1999
        int yearWithMostPhonesLaunched = DataAnalysis.findYearWithMostPhonesLaunched(cellList);
        System.out.println("Year with most phones launched after 1999: " + yearWithMostPhonesLaunched);
    }

    public static List<Cell> readCsvFile(String filename) {
        List<Cell> cellList = new ArrayList<Cell>();
        String line;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 12) {
                    Cell cell = new Cell(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7],
                            data[8], data[9], data[10], data[11]);
                    cellList.add(cell);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cellList;
    }
}