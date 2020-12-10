package com.sms_activate.response.api_rent.extra;

import org.jetbrains.annotations.NotNull;

public class SMSActivateRentActivation {
  /**
   * Id rent.
   */
  private int id;

  /**
   * Phone number.
   */
  private long number;

  /**
   * End date of rent.
   */
  private String endDate;

  private SMSActivateRentActivation() {
  }

  /**
   * Returns the id rent.
   *
   * @return id rent.
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the phone number.
   *
   * @return phone number
   */
  public long getNumber() {
    return number;
  }

  /**
   * Returns the end date of rent.
   *
   * @return end date of rent.
   */
  @NotNull
  public String getEndDate() {
    return endDate;
  }

  @Override
  public String toString() {
    return "SMSActivateGetRentNumber{" +
      "id=" + id +
      ", number=" + number +
      ", endDate='" + endDate + '\'' +
      '}';
  }
}
