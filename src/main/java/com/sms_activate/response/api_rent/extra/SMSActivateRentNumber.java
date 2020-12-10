package com.sms_activate.response.api_rent.extra;

public class SMSActivateRentNumber {
  /**
   * Id rent.
   */
  private int id;

  /**
   * Phone number.
   */
  private long phone;

  private SMSActivateRentNumber() {
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
   * Phone number.
   *
   * @return phone number.
   */
  public long getNumber() {
    return phone;
  }

  @Override
  public String toString() {
    return "SMSActivateRentNumber{" +
      "id=" + id +
      ", phone=" + phone +
      '}';
  }
}
