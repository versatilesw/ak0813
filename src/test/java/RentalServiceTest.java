package rentalsystem;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {

  @Test
  public void testCase1() {
    // Test 1: Tests the case where the discount percentage is above 100,
    // expecting an exception.
    Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false,
        false);
    RentalService service = new RentalService();
    LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
    assertThrows(IllegalArgumentException.class, () -> {
      service.checkout(tool, 5, 101, checkoutDate);
    });
  }

  @Test
  public void testCase2() {
    // Test 2: Validates a ladder rental, including weekend charging.
    Tool tool = new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false);
    RentalService service = new RentalService();
    LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
    RentalAgreement agreement = service.checkout(tool, 3, 10, checkoutDate);

    assertEquals("LADW", agreement.getToolCode());
    assertEquals("Ladder", agreement.getToolType());
    assertEquals("Werner", agreement.getBrand());
    assertEquals(3, agreement.getRentalDays());
    assertEquals(LocalDate.of(2020, 7, 4), agreement.getDueDate());
    assertEquals(1.99, agreement.getDailyCharge());
    assertEquals(2, agreement.getChargeDays());
    assertEquals(3.98, agreement.getPreDiscountCharge(), 0.01);
    assertEquals(10, agreement.getDiscountPercent());
    assertEquals(0.40, agreement.getDiscountAmount(), 0.01);
    assertEquals(3.58, agreement.getFinalCharge(), 0.01);
  }

  @Test
  public void testCase3() {
    Tool tool = new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true);
    RentalService service = new RentalService();
    LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
    RentalAgreement agreement = service.checkout(tool, 5, 25, checkoutDate);

    assertEquals("CHNS", agreement.getToolCode());
    assertEquals("Chainsaw", agreement.getToolType());
    assertEquals("Stihl", agreement.getBrand());
    assertEquals(5, agreement.getRentalDays());
    assertEquals(LocalDate.of(2015, 7, 6), agreement.getDueDate());
    assertEquals(3, agreement.getChargeDays()); // Includes July 3 (observed holiday)
    assertEquals(4.47, agreement.getPreDiscountCharge(), 0.01);
    assertEquals(25, agreement.getDiscountPercent());
    assertEquals(1.12, agreement.getDiscountAmount(), 0.01);
    assertEquals(3.35, agreement.getFinalCharge(), 0.01);
  }

  @Test
  public void testCase4() {
    Tool tool = new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false,
        false);
    RentalService service = new RentalService();
    LocalDate checkoutDate = LocalDate.of(2015, 9, 3);
    RentalAgreement agreement = service.checkout(tool, 6, 0, checkoutDate);

    assertEquals("JAKD", agreement.getToolCode());
    assertEquals("Jackhammer", agreement.getToolType());
    assertEquals("DeWalt", agreement.getBrand());
    assertEquals(6, agreement.getRentalDays());
    assertEquals(LocalDate.of(2015, 9, 8), agreement.getDueDate());
    assertEquals(3, agreement.getChargeDays()); // Excludes weekend and Labor Day
    assertEquals(8.97, agreement.getPreDiscountCharge(), 0.01);
    assertEquals(0, agreement.getDiscountPercent());
    assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
    assertEquals(8.97, agreement.getFinalCharge(), 0.01);
  }

  @Test
  public void testCase5() {
    Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false,
        false);
    RentalService service = new RentalService();
    LocalDate checkoutDate = LocalDate.of(2015, 7, 2);
    RentalAgreement agreement = service.checkout(tool, 9, 0, checkoutDate);

    assertEquals("JAKR", agreement.getToolCode());
    assertEquals("Jackhammer", agreement.getToolType());
    assertEquals("Ridgid", agreement.getBrand());
    assertEquals(9, agreement.getRentalDays());
    assertEquals(LocalDate.of(2015, 7, 10), agreement.getDueDate());
    assertEquals(6, agreement.getChargeDays()); // Excludes weekends and July 3 (observed holiday)
    assertEquals(17.94, agreement.getPreDiscountCharge(), 0.01);
    assertEquals(0, agreement.getDiscountPercent());
    assertEquals(0.00, agreement.getDiscountAmount(), 0.01);
    assertEquals(17.94, agreement.getFinalCharge(), 0.01);
  }

  @Test
  public void testCase6() {
    Tool tool = new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false,
        false);
    RentalService service = new RentalService();
    LocalDate checkoutDate = LocalDate.of(2020, 7, 2);
    RentalAgreement agreement = service.checkout(tool, 4, 50, checkoutDate);

    assertEquals("JAKR", agreement.getToolCode());
    assertEquals("Jackhammer", agreement.getToolType());
    assertEquals("Ridgid", agreement.getBrand());
    assertEquals(4, agreement.getRentalDays());
    assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate());
    assertEquals(1, agreement.getChargeDays()); // Excludes weekend and July 4th
    assertEquals(2.99, agreement.getPreDiscountCharge(), 0.01);
    assertEquals(50, agreement.getDiscountPercent());
    assertEquals(1.495, agreement.getDiscountAmount(), 0.01);
    assertEquals(1.495, agreement.getFinalCharge(), 0.01);
  }
}
