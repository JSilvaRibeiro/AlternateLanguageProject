import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Cell {
  private String oem;
  private String model;
  private Integer launchAnnounced;
  private String launchStatus;
  private String bodyDimensions;
  private Float bodyWeight;
  private String bodySim;
  private String displayType;
  private Float displaySize;
  private String displayResolution;
  private String featuresSensors;
  private String platformOs;

    // Constructor
  public Cell(String oem, String model, String launchAnnounced, String launchStatus, String bodyDimensions,
              String bodyWeight, String bodySim, String displayType, String displaySize, String displayResolution,
              String featuresSensors, String platformOs) {
      this.oem = oem;
      this.model = model;
      this.launchAnnounced = transformLaunchYear(launchAnnounced);
      this.launchStatus = launchStatus;
      this.bodyDimensions = bodyDimensions;
      this.bodyWeight = transformBodyWeight(bodyWeight);
      this.bodySim = transformBodySim(bodySim);
      this.displayType = displayType;
      this.displaySize = transformDisplaySize(displaySize);
      this.displayResolution = displayResolution;
      this.featuresSensors = featuresSensors;
      this.platformOs = transformPlatformOs(platformOs);
  }

  // Getter and setter methods...

  public String getOem() {
      return oem;
  }

  public String getModel() {
      return model;
  }

  public Integer getLaunchAnnounced() {
      return launchAnnounced;
  }

  public String getLaunchStatus() {
      return launchStatus;
  }

  public String getBodyDimensions() {
      return bodyDimensions;
  }

  public Float getBodyWeight() {
      return bodyWeight;
  }

  public String getBodySim() {
      return bodySim;
  }

  public String getDisplayType() {
      return displayType;
  }

  public Float getDisplaySize() {
      return displaySize;
  }

  public String getDisplayResolution() {
      return displayResolution;
  }

  public String getFeaturesSensors() {
      return featuresSensors;
  }

  public String getPlatformOs() {
      return platformOs;
  }


// Data transformation methods
  private Integer transformLaunchYear(String value) {
      try {
          return Integer.parseInt(value.replaceAll("\\D+", ""));
      } catch (NumberFormatException e) {
          // Handle invalid or missing values
          return null;
      }
  }

  private Float transformBodyWeight(String value) {
      try {
          Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)\\s*g");
          Matcher matcher = pattern.matcher(value);
          if (matcher.find()) {
              return Float.parseFloat(matcher.group(1));
          } else {
              return null;
          }
      } catch (NumberFormatException | IllegalStateException e) {
          return null;
      }
  }

  private String transformBodySim(String value) {
      if (value != null && (value.equalsIgnoreCase("No") || value.equalsIgnoreCase("Yes"))) {
          return null;
      }
      return value;
  }

  private Float transformDisplaySize(String value) {
      try {
          Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)\\s*inch");
          Matcher matcher = pattern.matcher(value);
          if (matcher.find()) {
              return Float.parseFloat(matcher.group(1));
          } else {
              return null;
          }
      } catch (NumberFormatException | IllegalStateException e) {
          return null;
      }
  }

  private String transformPlatformOs(String value) {
      if (value != null) {
          // Extract platform OS before the colon
          int colonIndex = value.indexOf(":");
          if (colonIndex != -1) {
              return value.substring(0, colonIndex).trim();
          } else {
              return value;
          }
      }
      return null;
  }


  // Method to convert object details to a string for printing
  @Override
  public String toString() {
      return "OEM: " + oem + ", Model: " + model + ", Launch Announced: " + launchAnnounced + ", Launch Status: " + launchStatus + ", Body Dimensions: " + bodyDimensions + ", Body Weight: " + bodyWeight + ", Body SIM: " + bodySim + ", Display Type: " + displayType + ", Display Size: " + displaySize + ", Display Resolution: " + displayResolution + ", Features Sensors: " + featuresSensors + ", Platform OS: " + platformOs;
  }

  // Method to count unique OEMs
  public static int countUniqueOEMs(ArrayList<Cell> cells) {
      HashMap<String, Integer> uniqueOEMs = new HashMap<String, Integer>();
      for (Cell cell : cells) {
          uniqueOEMs.put(cell.getOem(), 1);
      }
      return uniqueOEMs.size();
  }

  // Method to calculate average body weight
  public static Float calculateAverageBodyWeight(ArrayList<Cell> cells) {
      float totalWeight = 0;
      int count = 0;
      for (Cell cell : cells) {
          if (cell.getBodyWeight() != null) {
              totalWeight += cell.getBodyWeight();
              count++;
          }
      }
      return count > 0 ? totalWeight / count : 0;
  }

  // Method to add a new cell object
  public static void addCell(ArrayList<Cell> cells, Cell newCell) {
      cells.add(newCell);
  }

  // Method to delete a cell object
  public static void deleteCell(ArrayList<Cell> cells, int index) {
      if (index >= 0 && index < cells.size()) {
          cells.remove(index);
      }
  }

  // Method to get the launch status of a cell based on its index
  public static String getLaunchStatus(ArrayList<Cell> cells, int index) {
      if (index >= 0 && index < cells.size()) {
          return cells.get(index).getLaunchStatus();
      } else {
          return "Invalid index";
      }
  }

  // Method to list unique values for a specified column
public static ArrayList<String> listUniqueValuesForColumn(ArrayList<Cell> cells, String columnName) {
  ArrayList<String> uniqueValues = new ArrayList<String>();
  if ("oem".equals(columnName)) {
      for (Cell cell : cells) {
          if (!uniqueValues.contains(cell.getOem())) {
              uniqueValues.add(cell.getOem());
          }
      }
  } else if ("model".equals(columnName)) {
      for (Cell cell : cells) {
          if (!uniqueValues.contains(cell.getModel())) {
              uniqueValues.add(cell.getModel());
          }
      }
  } else {
      System.out.println("Invalid column name!");
  }
  return uniqueValues;
}
}

