package rentalsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
  private String toolCode;
  private String toolType;
  private String brand;
  private int rentalDays;
  private LocalDate checkoutDate;
  private LocalDate dueDate;
  private double dailyCharge;
  private int chargeDays;
  private double preDiscountCharge;
  private int discountPercent;
  private double discountAmount;
  private double finalCharge;

  // Getters and setters
  public String getToolCode() {
    return toolCode;
  }

  public void setToolCode(String toolCode) {
    this.toolCode = toolCode;
  }

  public String getToolType() {
    return toolType;
  }

  public void setToolType(String toolType) {
    this.toolType = toolType;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getRentalDays() {
    return rentalDays;
  }

  public void setRentalDays(int rentalDays) {
    this.rentalDays = rentalDays;
  }

  public LocalDate getCheckoutDate() {
    return checkoutDate;
  }

  public void setCheckoutDate(LocalDate checkoutDate) {
    this.checkoutDate = checkoutDate;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public double getDailyCharge() {
    return dailyCharge;
  }

  public void setDailyCharge(double dailyCharge) {
    this.dailyCharge = dailyCharge;
  }

  public int getChargeDays() {
    return chargeDays;
  }

  public void setChargeDays(int chargeDays) {
    this.chargeDays = chargeDays;
  }

  public double getPreDiscountCharge() {
    return preDiscountCharge;
  }

  public void setPreDiscountCharge(double preDiscountCharge) {
    this.preDiscountCharge = preDiscountCharge;
  }

  public int getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(int discountPercent) {
    this.discountPercent = discountPercent;
  }

  public double getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(double discountAmount) {
    this.discountAmount = discountAmount;
  }

  public double getFinalCharge() {
    return finalCharge;
  }

  public void setFinalCharge(double finalCharge) {
    this.finalCharge = finalCharge;
  }

  // Method to print the rental agreement
  public void printAgreement() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    System.out.println("Tool code: " + toolCode);
    System.out.println("Tool type: " + toolType);
    System.out.println("Tool brand: " + brand);
    System.out.println("Rental days: " + rentalDays);
    System.out.println("Check out date: " + checkoutDate.format(formatter));
    System.out.println("Due date: " + dueDate.format(formatter));
    System.out.println("Daily rental charge: $" + String.format("%.2f", dailyCharge));
    System.out.println("Charge days: " + chargeDays);
    System.out.println("Pre-discount charge: $" + String.format("%.2f", preDiscountCharge));
    System.out.println("Discount percent: " + discountPercent + "%");
    System.out.println("Discount amount: $" + String.format("%.2f", discountAmount));
    System.out.println("Final charge: $" + String.format("%.2f", finalCharge));
  }
}
