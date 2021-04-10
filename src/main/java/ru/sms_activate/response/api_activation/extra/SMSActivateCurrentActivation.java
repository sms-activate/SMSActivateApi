package ru.sms_activate.response.api_activation.extra;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class SMSActivateCurrentActivation {
  /**
   * Activation id.
   */
  private int id;

  /**
   * Activation is forward.
   */
  private int forward;

  /**
   * Activation phone number.
   */
  @SerializedName(value = "phone", alternate = {
    "phoneNumber", "number"
  })
  private long phoneNumber;

  /**
   * Service for activation.
   */
  private String service;

  /**
   * Country activation.
   */
  private int country;

  private SMSActivateCurrentActivation() {
  }

  /**
   * Returns the service activation.
   *
   * @return service activation
   */
  @NotNull
  public String getService() {
    return service;
  }

  /**
   * Returns the country activation.
   *
   * @return country activation.
   */
  public int getCountry() {
    return country;
  }

  /**
   * Returns the activation id.
   *
   * @return activation id
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the forward activation.
   *
   * @return forward activation
   */
  public boolean isForward() {
    return forward == 1;
  }

  /**
   * Returns the activation phone number.
   *
   * @return activation phone number
   */
  public long getPhoneNumber() {
    return phoneNumber;
  }

  @Override
  public String toString() {
    return "SMSActivateCurrentActivation{" +
      "id=" + id +
      ", forward=" + forward +
      ", phoneNumber=" + phoneNumber +
      ", service='" + service + '\'' +
      ", country=" + country +
      '}';
  }
}
