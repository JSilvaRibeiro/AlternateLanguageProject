import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataAnalysis {

    // Method to find the company with the highest average body weight
    public static String findCompanyWithHighestAvgBodyWeight(List<Cell> cells) {
        Map<String, Double> avgBodyWeightByCompany = cells.stream()
                .filter(cell -> cell.getBodyWeight() != null)
                .collect(Collectors.groupingBy(Cell::getOem,
                        Collectors.averagingDouble(Cell::getBodyWeight)));

        return avgBodyWeightByCompany.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // Method to find phones announced in one year and released in another
    public static List<Cell> findPhonesWithDifferentAnnouncementAndReleaseYears(List<Cell> cells) {
        return cells.stream()
                .filter(cell -> cell.getLaunchAnnounced() != null && cell.getLaunchStatus() != null)
                .filter(cell -> !cell.getLaunchAnnounced().equals(cell.getLaunchStatus()))
                .collect(Collectors.toList());
    }

    // Method to count phones with only one feature sensor
    public static long countPhonesWithOneFeatureSensor(List<Cell> cells) {
        return cells.stream()
                .filter(cell -> cell.getFeaturesSensors() != null)
                .filter(cell -> cell.getFeaturesSensors().split(",").length == 1)
                .count();
    }

    // Method to find the year with the most phones launched after 1999
    public static int findYearWithMostPhonesLaunched(List<Cell> cells) {
        Map<Integer, Long> phonesLaunchedByYear = cells.stream()
                .filter(cell -> cell.getLaunchAnnounced() != null && cell.getLaunchAnnounced() > 1999)
                .collect(Collectors.groupingBy(Cell::getLaunchAnnounced, Collectors.counting()));

        return phonesLaunchedByYear.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);
    }
  
    // Method to calculate the average body weight of phones for a given list of cells
    public static float calculateAverageBodyWeight(List<Cell> cellList) {
        if (cellList.isEmpty()) {
            return 0;
        }

        float totalWeight = 0;
        int count = 0;

        for (Cell cell : cellList) {
            Float weight = cell.getBodyWeight();
            if (weight != null) {
                totalWeight += weight;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return totalWeight / count;
    }
}
