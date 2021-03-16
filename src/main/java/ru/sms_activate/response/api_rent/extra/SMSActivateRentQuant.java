package ru.sms_activate.response.api_rent.extra;

import com.google.gson.annotations.SerializedName;

public class SMSActivateRentQuant {
  /**
   * Real current phone numbers.
   */
  @SerializedName("current")
  private int currentCountPhoneNumbers;

  /**
   * All count phone numbers.
   */
  @SerializedName("total")
  private int totalCountPhoneNumbers;

  private SMSActivateRentQuant() {
  }

  /**
   * Returns the current count phone numbers.
   *
   * @return current count phone numbers.
   */
  public int getCurrentCountPhoneNumbers() {
    return currentCountPhoneNumbers;
  }

  /**
   * Returns the total count phone numbers.
   *
   * @return total count phone numbers.
   */
  public int getTotalCountPhoneNumbers() {
    return totalCountPhoneNumbers;
  }

  @Override
  public String toString() {
    return "SMSActivateRentQuant{" +
      "currentCountPhoneNumbers=" + currentCountPhoneNumbers +
      ", totalCountPhoneNumbers=" + totalCountPhoneNumbers +
      '}';
  }
}
