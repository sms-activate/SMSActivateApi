package ru.sms_activate.response.api_activation;

import org.jetbrains.annotations.NotNull;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_activation.extra.SMSActivateGetPriceInfo;

import java.util.Map;

public class SMSActivateGetNumbersStatusAndMediumSmsTime {
  /**
   * Map current service price info where key is service shortname with suffix forward(_0, _1).
   */
  private final Map<String, SMSActivateGetPriceInfo> smsActivateGetPriceInfoMap;

  /**
   * Constructor response getNumbersStatusAndMediumSmsTime with data.
   *
   * @param smsActivateGetPriceInfoMap information about services.
   */
  public SMSActivateGetNumbersStatusAndMediumSmsTime(@NotNull Map<String, SMSActivateGetPriceInfo> smsActivateGetPriceInfoMap) {
    this.smsActivateGetPriceInfoMap = smsActivateGetPriceInfoMap;
  }

  /**
   * Returns the information about services.
   *
   * @return information about services.
   */
  @NotNull
  public Map<String, SMSActivateGetPriceInfo> getSmsActivateGetPriceInfoMap() {
    return smsActivateGetPriceInfoMap;
  }

  /**
   * Returns the price info by service short name with forward.
   *
   * @param serviceShortName service short name with forward.
   * @return price info by service short name with forward.
   * @throws SMSActivateWrongParameterException if service short name is incorrect.
   */
  @NotNull
  public SMSActivateGetPriceInfo getPriceInfoByServiceShortNameWithoutForward(@NotNull String serviceShortName)
      throws SMSActivateWrongParameterException {
    return getPriceInfoByServiceShortName(serviceShortName + "_0");
  }

  /**
   * Returns the price info by service short name without forward.
   *
   * @param serviceShortName service short name without forward.
   * @return price info by service short name without forward.
   * @throws SMSActivateWrongParameterException if service short name is incorrect.
   */
  @NotNull
  public SMSActivateGetPriceInfo getPriceInfoByServiceShortNameWithForward(@NotNull String serviceShortName)
      throws SMSActivateWrongParameterException {
    return getPriceInfoByServiceShortName(serviceShortName + "_1");
  }

  /**
   * Returns the price info by service short name with suffix forward.
   *
   * @param serviceShortName service short name with suffix forward(_0 _1).
   * @return price info by service short name with suffix forward.
   * @throws SMSActivateWrongParameterException if service short name is incorrect.
   */
  @NotNull
  private SMSActivateGetPriceInfo getPriceInfoByServiceShortName(@NotNull String serviceShortName)
      throws SMSActivateWrongParameterException {
    SMSActivateGetPriceInfo smsActivateGetPriceInfo = smsActivateGetPriceInfoMap.get(serviceShortName);

    if (smsActivateGetPriceInfo == null) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.BAD_SERVICE);
    }

    return smsActivateGetPriceInfo;
  }
}
