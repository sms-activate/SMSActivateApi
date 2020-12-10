package com.sms_activate.response.api_activation;

import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SMSActivateGetMultiServiceNumberResponse {
  /**
   * List multi-service activations.
   */
  private final List<SMSActivateActivation> smsActivateActivationList;

  /**
   * Constructor the getMultiServiceNumber response with activations.
   *
   * @param smsActivateActivationList list activations.
   */
  public SMSActivateGetMultiServiceNumberResponse(@NotNull List<SMSActivateActivation> smsActivateActivationList) {
    this.smsActivateActivationList = smsActivateActivationList;
  }

  /**
   * Returns the activations by specified Id.
   *
   * @param activationId id activation.
   * @return activation.
   * @throws SMSActivateWrongParameter if id is incorrect.
   */
  @NotNull
  public SMSActivateActivation get(int activationId) throws SMSActivateWrongParameterException {
    for (SMSActivateActivation activateActivation : this.smsActivateActivationList) {
      if (activateActivation.getId() == activationId) {
        return activateActivation;
      }
    }

    throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.INVALID_PHONE);
  }

  /**
   * Returns the list multi-service activations.
   *
   * @return list multi-service activations.
   */
  @NotNull
  public List<SMSActivateActivation> getSMSActivateActivationList() {
    return smsActivateActivationList;
  }
}
