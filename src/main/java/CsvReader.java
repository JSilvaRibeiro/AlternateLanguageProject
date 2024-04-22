import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
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
