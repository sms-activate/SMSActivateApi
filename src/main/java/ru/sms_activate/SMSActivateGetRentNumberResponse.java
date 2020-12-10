package ru.sms_activate;

import ru.sms_activate.response.api_rent.extra.SMSActivateRentActivation;
import org.jetbrains.annotations.NotNull;

class SMSActivateGetRentNumberResponse {
  /**
   * Rent phone.
   */
  private SMSActivateRentActivation phone;

  /**
   * Returns the rent phone.
   *
   * @return rent phone.
   */
  @NotNull
  public SMSActivateRentActivation getSMSmsActivateGetRentNumber() {
    return phone;
  }
}
