package ru.sms_activate;

import org.jetbrains.annotations.NotNull;

class SMSActivateErrorResponse {
  /**
   * Status response.
   */
  private String status;

  /**
   * Message about error.
   */
  private String message;

  /**
   * Returns the error status response.
   *
   * @return the error status response.
   */
  @NotNull
  public String getStatus() {
    return status;
  }

  /**
   * Returns the message about error.
   *
   * @return message about error.
   */
  @NotNull
  public String getMessage() {
    return message;
  }
}
