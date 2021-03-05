package ru.sms_activate.response.extra;

import org.jetbrains.annotations.NotNull;

public class SMSActivateCountry {
  /**
   * Country id.
   */
  private int id;

  /**
   * Country name.
   */
  private String name;

  private SMSActivateCountry() {
  }

  /**
   * Returns the country name.
   *
   * @return country name.
   */
  @NotNull
  public String getName() {
    return name;
  }

  /**
   * Returns the country id.
   *
   * @return country id.
   */
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "SMSActivateCountry{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
