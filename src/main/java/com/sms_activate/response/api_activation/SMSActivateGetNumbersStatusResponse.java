package com.sms_activate.response.api_activation;

import com.sms_activate.response.api_activation.extra.SMSActivateServiceInfo;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SMSActivateGetNumbersStatusResponse {
  /**
   * Map services where key is short name service.
   */
  private final Map<String, SMSActivateServiceInfo> smsActivateServiceInfoMap;

  /**
   * Constructor response getNumbersStatus with services.
   *
   * @param smsActivateServiceInfoMap map services where key is short name service.
   */
  public SMSActivateGetNumbersStatusResponse(@NotNull Map<String, SMSActivateServiceInfo> smsActivateServiceInfoMap) {
    this.smsActivateServiceInfoMap = smsActivateServiceInfoMap;
  }

  /**
   * Returns the service by shortName.
   *
   * @param serviceName service short name.
   * @return SMSActivateServiceInfo object.
   * @throws SMSActivateWrongParameterException if serviceName is incorrect.
   */
  @NotNull
  public SMSActivateServiceInfo getSMSActivateServiceInfoByShortName(@NotNull String serviceName) throws SMSActivateWrongParameterException {
    return getSMSActivateServiceInfoByShortNameAndForward(serviceName, false);
  }

  /**
   * Returns the service by shortName.
   *
   * @param serviceName service short name.
   * @return SMSActivateServiceInfo object.
   * @throws SMSActivateWrongParameterException if serviceName is incorrect.
   */
  @NotNull
  public SMSActivateServiceInfo getSMSActivateServiceForwardInfoByShortName(@NotNull String serviceName) throws SMSActivateWrongParameterException {
    return getSMSActivateServiceInfoByShortNameAndForward(serviceName, true);
  }

  /**
   * Returns the service by shortName and forward.
   *
   * @param serviceName service short name.
   * @param forward service support forward.
   * @return SMSActivateServiceInfo object.
   * @throws SMSActivateWrongParameterException if serviceName is incorrect.
   */
  @NotNull
  private SMSActivateServiceInfo getSMSActivateServiceInfoByShortNameAndForward(@NotNull String serviceName, boolean forward) throws SMSActivateWrongParameterException {
    SMSActivateServiceInfo smsActivateServiceInfo = smsActivateServiceInfoMap.get(serviceName + "_" + (forward ? "1" : "0"));

    if (smsActivateServiceInfo == null) {
      throw new SMSActivateWrongParameterException("Wrong service short name.", "Неккоректное короткое имя сервиса.");
    }

    return smsActivateServiceInfo;
  }

  /**
   * Returns the all services.
   *
   * @return all services.
   */
  @NotNull
  public List<SMSActivateServiceInfo> getAllServiceInfoList() {
    return new ArrayList<>(smsActivateServiceInfoMap.values());
  }
}
