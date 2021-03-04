package ru.sms_activate.response.api_activation.extra;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class SMSActivatePriceInfo {
  /**
   * Service cost (ruble).
   */
  private BigDecimal cost;

  /**
   * count phone numbers.
   */
  @SerializedName(value = "count", alternate = {"quant"})
  private int count;

  private SMSActivatePriceInfo() {
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
    return "SMSActivatePriceInfo{" +
      "cost=" + cost +
      ", count=" + count +
      '}';
  }
}
