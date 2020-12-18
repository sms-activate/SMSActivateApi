package ru.sms_activate.response.api_activation.extra;

import org.jetbrains.annotations.NotNull;

public class SMSActivateServiceInfo {
  /**
   * Service support forward.
   */
  private final boolean forward;

  /**
   * Count of service numbers.
   */
  private final int countPhoneNumber;

  /**
   * Service short name.
   */
  private final String serviceShortName;

  /**
   * Constructor response getNumbersStatus with data from server.
   *
   * @param forward          service support forward.
   * @param countPhoneNumber      count of service numbers.
   * @param serviceShortName service short name.
   */
  public SMSActivateServiceInfo(
    @NotNull String serviceShortName,
    int countPhoneNumber,
    boolean forward
  ) {
    this.serviceShortName = serviceShortName;
    this.countPhoneNumber = countPhoneNumber;
    this.forward = forward;
  }

  /**
   * Returns the support service forward.
   *
   * @return support service forward.
   */
  public boolean isForward() {
    return forward;
  }

  /**
   * Returns the count of service numbers.
   *
   * @return count of service numbers.
   */
  public int getCountPhoneNumber() {
    return countPhoneNumber;
  }

  /**
   * Returns the short name of service.
   *
   * @return short name of service.
   */
  @NotNull
  public String getShortName() {
    return serviceShortName;
  }
}
