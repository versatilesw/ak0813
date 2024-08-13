package rentalsystem;

public class Tool {
  private String toolCode;
  private String toolType;
  private String brand;
  private double dailyCharge;
  private boolean isWeekdayCharge;
  private boolean isWeekendCharge;
  private boolean isHolidayCharge;

  public Tool(String toolCode, String toolType, String brand, double dailyCharge, boolean isWeekdayCharge,
      boolean isWeekendCharge, boolean isHolidayCharge) {
    this.toolCode = toolCode;
    this.toolType = toolType;
    this.brand = brand;
    this.dailyCharge = dailyCharge;
    this.isWeekdayCharge = isWeekdayCharge;
    this.isWeekendCharge = isWeekendCharge;
    this.isHolidayCharge = isHolidayCharge;
  }

  // Getters
  public String getToolCode() {
    return toolCode;
  }

  public String getToolType() {
    return toolType;
  }

  public String getBrand() {
    return brand;
  }

  public double getDailyCharge() {
    return dailyCharge;
  }

  public boolean isWeekdayCharge() {
    return isWeekdayCharge;
  }

  public boolean isWeekendCharge() {
    return isWeekendCharge;
  }

  public boolean isHolidayCharge() {
    return isHolidayCharge;
  }
}
