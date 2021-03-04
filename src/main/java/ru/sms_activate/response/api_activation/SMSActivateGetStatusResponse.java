package ru.sms_activate.response.api_activation;

import ru.sms_activate.response.api_activation.enums.SMSActivateGetStatusActivation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SMSActivateGetStatusResponse {
  /**
   * Status activation.
   */
  private final SMSActivateGetStatusActivation smsActivateGetStatusActivation;

  /**
   * Code from sms.
   */
  private final String codeFromSMS;

  /**
   * Constructor response getStatus with data from server.
   *
   * @param smsActivateGetStatusActivation status activation.
   * @param codeFromSMS                    code sms.
   */
  public SMSActivateGetStatusResponse(@NotNull SMSActivateGetStatusActivation smsActivateGetStatusActivation, @Nullable String codeFromSMS) {
    this.smsActivateGetStatusActivation = smsActivateGetStatusActivation;
    this.codeFromSMS = codeFromSMS;
  }

  /**
   * Returns the description about status.
   *
   * @return description about status.
   */
  @NotNull
  public String getMessage() {
    return smsActivateGetStatusActivation.getMessage();
  }

  /**
   * Returns the code from sms.
   *
   * @return code from sms.
   */
  @Nullable
  public String getCodeFromSMS() {
    return codeFromSMS;
  }

  /**
   * Returns the status activation.
   *
   * @return status activation.
   */
  @NotNull
  public SMSActivateGetStatusActivation getSMSActivateGetStatus() {
    return smsActivateGetStatusActivation;
  }

  @Override
  public String toString() {
    return "SMSActivateGetStatusResponse{" +
      "smsActivateGetStatusActivation=" + smsActivateGetStatusActivation +
      ", codeFromSMS='" + codeFromSMS + '\'' +
      '}';
  }
}
