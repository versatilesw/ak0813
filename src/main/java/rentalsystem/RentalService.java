package rentalsystem;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;

public class RentalService {
  public RentalAgreement checkout(Tool tool, int rentalDays, int discountPercent, LocalDate checkoutDate) {
    if (rentalDays < 1) {
      throw new IllegalArgumentException("Rental day count must be 1 or greater.");
    }
    if (discountPercent < 0 || discountPercent > 100) {
      throw new IllegalArgumentException("Discount percent must be between 0 and 100.");
    }

    RentalAgreement agreement = new RentalAgreement();
    agreement.setToolCode(tool.getToolCode());
    agreement.setToolType(tool.getToolType());
    agreement.setBrand(tool.getBrand());
    agreement.setRentalDays(rentalDays);
    agreement.setCheckoutDate(checkoutDate);

    // Calculate due date
    LocalDate dueDate = checkoutDate.plusDays(rentalDays - 1);
    agreement.setDueDate(dueDate);

    // Calculate chargeable days
    int chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
    agreement.setChargeDays(chargeDays);

    // Set daily charge
    agreement.setDailyCharge(tool.getDailyCharge());

    // Calculate pre-discount charge
    double preDiscountCharge = chargeDays * tool.getDailyCharge();
    agreement.setPreDiscountCharge(preDiscountCharge);

    // Set discount percent
    agreement.setDiscountPercent(discountPercent);

    // Calculate discount amount
    double discountAmount = (preDiscountCharge * discountPercent) / 100;
    agreement.setDiscountAmount(discountAmount);

    // Calculate final charge
    double finalCharge = preDiscountCharge - discountAmount;
    agreement.setFinalCharge(finalCharge);

    return agreement;
  }

  private int calculateChargeDays(LocalDate startDate, LocalDate endDate, Tool tool) {
    int chargeDays = 0;
    for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
      boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
      boolean isHoliday = isHoliday(date);

      if (!isWeekend && !isHoliday && tool.isWeekdayCharge()) {
        chargeDays++; // Weekday charge
      } else if (isWeekend && tool.isWeekendCharge()) {
        chargeDays++; // Weekend charge
      } else if (isHoliday && tool.isHolidayCharge()) {
        chargeDays++; // Holiday charge
      }
    }
    return chargeDays;
  }

  private boolean isHoliday(LocalDate date) {
    // Independence Day observed logic
    if (date.getMonth() == Month.JULY) {
      if (date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) {
        return true; // Observed holiday on Friday if July 4th is Saturday
      } else if (date.getDayOfMonth() == 4 && date.getDayOfWeek() != DayOfWeek.SATURDAY) {
        return true; // July 4th if not a Saturday
      } else if (date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY) {
        return true; // Observed holiday on Monday if July 4th is Sunday
      }
    }

    // Labor Day logic (First Monday in September)
    if (date.getMonth() == Month.SEPTEMBER && date.getDayOfWeek() == DayOfWeek.MONDAY && date.getDayOfMonth() <= 7) {
      return true;
    }

    return false;
  }
}