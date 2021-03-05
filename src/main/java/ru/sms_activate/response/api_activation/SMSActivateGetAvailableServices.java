package ru.sms_activate.response.api_activation;

import org.jetbrains.annotations.NotNull;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_activation.extra.SMSActivateAvailableService;

import java.util.List;

public class SMSActivateGetAvailableServices {
  /**
   * List of available services.
   */
  private final List<SMSActivateAvailableService> smsActivateAvailableServiceList;

  /**
   * Constructor response "getAvailableServicesByCountry" with list of available services.
   *
   * @param smsActivateAvailableServiceList list of available services
   */
  public SMSActivateGetAvailableServices(@NotNull List<SMSActivateAvailableService> smsActivateAvailableServiceList) {
    this.smsActivateAvailableServiceList = smsActivateAvailableServiceList;
  }

  /**
   * Returns the list of available of services.
   *
   * @return the list of available of services.
   */
  public List<SMSActivateAvailableService> getSmsActivateAvailableServiceList() {
    return smsActivateAvailableServiceList;
  }

  /**
   * Returns the available service by short name with out forward.
   *
   * @param shortName service short name.
   * @return available service by short name with out forward.
   * @throws SMSActivateWrongParameterException if shortName is not correct.
   */
  public SMSActivateAvailableService getSMSActivateAvailableServiceByShortNameWithoutForward(@NotNull String shortName) throws SMSActivateWrongParameterException {
    for (SMSActivateAvailableService smsActivateAvailableService : smsActivateAvailableServiceList) {
      if (smsActivateAvailableService.getShortName().equalsIgnoreCase(shortName) && !smsActivateAvailableService.isForward()) {
        return smsActivateAvailableService;
      }
    }

    throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.BAD_SERVICE);
  }

  /**
   * Returns the available service by short name with forward.
   *
   * @param shortName service short name.
   * @return available service by short name with forward.
   * @throws SMSActivateWrongParameterException if shortName is not correct.
   */
  public SMSActivateAvailableService getSMSActivateAvailableServiceByShortNameWithForward(@NotNull String shortName) throws SMSActivateWrongParameterException {
    for (SMSActivateAvailableService smsActivateAvailableService : smsActivateAvailableServiceList) {
      if (smsActivateAvailableService.getShortName().equals(shortName) && smsActivateAvailableService.isForward()) {
        return smsActivateAvailableService;
      }
    }

    throw new SMSActivateWrongParameterException(shortName + " service does not support call forwarding", "Сервис " + shortName + " не поддерживает переадресацию");
  }

  @Override
  public String toString() {
    return "SMSActivateGetAvailableServices{" +
      "smsActivateAvailableServiceList=" + smsActivateAvailableServiceList +
      '}';
  }
}
