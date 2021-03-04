package ru.sms_activate.response.api_rent.extra;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class SMSActivateGetRentService {
  /**
   * Cost number by service (ruble).
   */
  private BigDecimal cost;

  /**
   * Count phone numbers in service.
   */
  private int quant;

  /**
   * Name of service.
   */
  private String name;

  private SMSActivateGetRentService() {
  }

  /**
   * Returns the cost number by service (ruble).
   *
   * @return cost number by service (ruble).
   */
  @NotNull
  public BigDecimal getCost() {
    return cost;
  }

  /**
   * Returns the name of service.
   *
   * @return name of service.
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Returns the count phone numbers in service.
   *
   * @return count phone numbers in service.
   */
  public int getQuant() {
    return quant;
  }

  @Override
  public String toString() {
    return "SMSActivateGetRentService{" +
      "cost=" + cost +
      ", name='" + name + '\'' +
      ", quant=" + quant +
      '}';
  }
}
