package ru.sms_activate.response.api_activation.extra;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class SMSActivateGetPriceInfo {
  /**
   * Service cost (ruble).
   */
  private BigDecimal cost;
  /**
   * count phone numbers.
   */
  private int count;

  private SMSActivateGetPriceInfo() {
  }

  /**
   * Returns the service cost (ruble).
   *
   * @return service cost (ruble).
   */
  @NotNull
  public BigDecimal getCost() {
    return cost;
  }

  /**
   * Returns the count phone numbers.
   *
   * @return count phone numbers.
   */
  public int getCountPhoneNumbers() {
    return count;
  }

  @Override
  public String toString() {
    return "SMSActivateGetPriceInfo{" +
      "cost=" + cost +
      ", count=" + count +
      '}';
  }
}
