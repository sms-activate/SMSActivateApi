package ru.sms_activate.response.api_activation;

import ru.sms_activate.response.api_activation.extra.SMSActivateGetPriceInfo;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SMSActivateGetPricesResponse {
  /**
   * Map prices where key is country id and short name service.
   */
  private final Map<Integer, Map<String, SMSActivateGetPriceInfo>> smsActivateGetPriceMap;

  /**
   * Constructor response getPrices with data from server.
   *
   * @param smsActivateGetPriceMap map where first key is countryId, second key is service short name.
   */
  public SMSActivateGetPricesResponse(@NotNull Map<Integer, Map<String, SMSActivateGetPriceInfo>> smsActivateGetPriceMap) {
    this.smsActivateGetPriceMap = smsActivateGetPriceMap;
  }

  /**
   * Returns the object with info about service.
   *
   * @param countryId   specified country id.
   * @param serviceName service short name.
   * @return object with info about service.
   * @throws SMSActivateWrongParameterException if country id or service name is incorrect.
   */
  @NotNull
  public SMSActivateGetPriceInfo getPriceInfo(int countryId, @NotNull String serviceName) throws SMSActivateBaseException {
    SMSActivateGetPriceInfo smsActivateGetPriceInfo = this.getSmsActivateGetPriceMap(countryId).get(serviceName);

    if (smsActivateGetPriceInfo == null) {
      throw new SMSActivateWrongParameterException("Wrong service name.", "Некорректное имя сервиса.");
    }

    return smsActivateGetPriceInfo;
  }

  /**
   * Returns the map with services by country id.
   *
   * @param countryId country id.
   * @return map with services by country id.
   * @throws SMSActivateWrongParameterException if country id is incorrect.
   */
  @NotNull
  public Map<String, SMSActivateGetPriceInfo> getSmsActivateGetPriceMap(@NotNull Integer countryId) throws SMSActivateWrongParameterException {
    Map<String, SMSActivateGetPriceInfo> smsActivateGetPriceInfoMap = smsActivateGetPriceMap.get(countryId);

    if (smsActivateGetPriceInfoMap == null) {
      throw new SMSActivateWrongParameterException("Wrong country id", "Некорректный id страны");
    }

    return smsActivateGetPriceInfoMap;
  }

  /**
   * Returns the sorted set with country id.
   *
   * @return sorted set with country id.
   */
  @NotNull
  public SortedSet<Integer> getCountryIdSet() {
    return new TreeSet<>(this.smsActivateGetPriceMap.keySet());
  }

  /**
   * Returns the set with short name service.
   *
   * @param countryId country id.
   * @return set with short name service.
   * @throws SMSActivateWrongParameterException if country id is incorrect.
   */
  @NotNull
  public Set<String> getServicesByCountryId(@NotNull Integer countryId) throws SMSActivateWrongParameterException {
    return new HashSet<>(this.getSmsActivateGetPriceMap(countryId).keySet());
  }
}
