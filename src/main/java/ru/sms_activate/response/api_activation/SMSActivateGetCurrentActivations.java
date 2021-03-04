package ru.sms_activate.response.api_activation;

import org.jetbrains.annotations.NotNull;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_activation.extra.SMSActivateCurrentActivation;

import java.util.ArrayList;
import java.util.List;

public class SMSActivateGetCurrentActivations {
  /**
   * List of current activations.
   */
  private List<SMSActivateCurrentActivation> data;

  /**
   * Indicator to send a repeat request.
   */
  private boolean existNext;

  /**
   * Total count of activations.
   */
  private int quant;

  private SMSActivateGetCurrentActivations() {
  }

  /**
   * Returns the list of current activations.
   *
   * @return list of current activations
   */
  @NotNull
  public List<SMSActivateCurrentActivation> getCurrentActivationList() {
    return data;
  }

  /**
   * Returns the activation by phone number.
   *
   * @param phoneNumber activation phone number.
   * @return activation by phone number.
   * @throws SMSActivateWrongParameterException if phone number is incorrect.
   */
  @NotNull
  public SMSActivateCurrentActivation getActivationByPhoneNumber(long phoneNumber) throws SMSActivateWrongParameterException {
    for (SMSActivateCurrentActivation smsActivateCurrentActivation : data) {
      if (smsActivateCurrentActivation.getPhoneNumber() == phoneNumber) {
        return smsActivateCurrentActivation;
      }
    }

    throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.INVALID_PHONE);
  }

  /**
   * Returns the activation by id.
   *
   * @param id activation id.
   * @return activation by id.
   * @throws SMSActivateWrongParameterException if activation id is incorrect.
   */
  @NotNull
  public SMSActivateCurrentActivation getActivationById(int id) throws SMSActivateWrongParameterException {
    for (SMSActivateCurrentActivation smsActivateCurrentActivation : data) {
      if (smsActivateCurrentActivation.getId() == id) {
        return smsActivateCurrentActivation;
      }
    }

    throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_ACTIVATION_ID);
  }

  /**
   * Returns the list current activations by service short name.
   *
   * @param serviceShortName service short name.
   * @return list current activations by service short name.
   */
  @NotNull
  public List<SMSActivateCurrentActivation> getActivationsByServiceName(@NotNull String serviceShortName) {
    List<SMSActivateCurrentActivation> smsActivateCurrentActivationList = new ArrayList<>();

    for (SMSActivateCurrentActivation smsActivateCurrentActivation : data) {
      if (smsActivateCurrentActivation.getService().equals(serviceShortName)) {
        smsActivateCurrentActivationList.add(smsActivateCurrentActivation);
      }
    }

    return smsActivateCurrentActivationList;
  }

  /**
   * Returns the list of current activations by country id.
   *
   * @param countryId country id.
   * @return list of current activations by country id.
   */
  @NotNull
  public List<SMSActivateCurrentActivation> getCurrentActivationsByCountryId(int countryId) {
    List<SMSActivateCurrentActivation> smsActivateCurrentActivationList = new ArrayList<>();

    for (SMSActivateCurrentActivation smsActivateCurrentActivation : data) {
      if (smsActivateCurrentActivation.getCountry() == countryId) {
        smsActivateCurrentActivationList.add(smsActivateCurrentActivation);
      }
    }

    return smsActivateCurrentActivationList;
  }

  /**
   * Returns the list current activation with forward.
   *
   * @return list current activation with forward.
   * @throws SMSActivateBaseException if not current activations.
   */
  @NotNull
  public List<SMSActivateCurrentActivation> getCurrentActivationWithForward() throws SMSActivateBaseException {
    return getCurrentActivationByForward(true);
  }

  /**
   * Returns the list current activation without forward.
   *
   * @return list current activation without forward.
   */
  @NotNull
  public List<SMSActivateCurrentActivation> getCurrentActivationWithoutForward() throws SMSActivateBaseException {
    return getCurrentActivationByForward(false);
  }

  /**
   * Returns the list current activation by forward.
   *
   * @param forward - true if activation with forward, else false.
   * @return list current activation by forward
   * @throws SMSActivateBaseException if not current activations.
   */
  @NotNull
  private List<SMSActivateCurrentActivation> getCurrentActivationByForward(boolean forward) throws SMSActivateBaseException {
    if (data == null) {
      throw new SMSActivateBaseException("No activations!", "Нет активаций!");
    }

    List<SMSActivateCurrentActivation> smsActivateCurrentActivationList = new ArrayList<>();

    for (SMSActivateCurrentActivation smsActivateCurrentActivation : data) {
      if (smsActivateCurrentActivation.isForward() == forward) {
        smsActivateCurrentActivationList.add(smsActivateCurrentActivation);
      }
    }

    return smsActivateCurrentActivationList;
  }

  /**
   * Returns the indicator to send a repeat request.
   *
   * @return indicator to send a repeat request.
   */
  public boolean isExistNext() {
    return existNext;
  }

  /**
   * Returns the total count of activations.
   *
   * @return total count of activations.
   */
  public int getQuant() {
    return quant;
  }

  @Override
  public String toString() {
    return "SMSActivateGetCurrentActivations{" +
      "data=" + data +
      ", existNext=" + existNext +
      ", quant=" + quant +
      '}';
  }
}
