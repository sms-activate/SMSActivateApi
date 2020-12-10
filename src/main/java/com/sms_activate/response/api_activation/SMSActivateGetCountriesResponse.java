package com.sms_activate.response.api_activation;

import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.response.api_activation.extra.SMSActivateCountryInfo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SMSActivateGetCountriesResponse {
  /**
   * The map countries info where key is ID country.
   */
  private final Map<Integer, SMSActivateCountryInfo> smsActivateCountryInfoMap;

  /**
   * Constructor getCountries response.
   *
   * @param smsActivateCountryInfoMap map countries info where key is ID country.
   */
  public SMSActivateGetCountriesResponse(@NotNull Map<Integer, SMSActivateCountryInfo> smsActivateCountryInfoMap) {
    this.smsActivateCountryInfoMap = smsActivateCountryInfoMap;
  }

  /**
   * Returns the country info by id.
   *
   * @param countryId country id.
   * @return country info.
   * @throws SMSActivateWrongParameterException if id is incorrect.
   */
  @NotNull
  public SMSActivateCountryInfo get(int countryId) throws SMSActivateWrongParameterException {
    SMSActivateCountryInfo smsActivateCountryInfo = smsActivateCountryInfoMap.get(countryId);

    if (smsActivateCountryInfo == null) {
      throw new SMSActivateWrongParameterException("Wrong country id.", "Некорректный id страны.");
    }
    return smsActivateCountryInfo;
  }

  /**
   * Returns the countries list.
   *
   * @return countries list.
   */
  @NotNull
  public List<SMSActivateCountryInfo> getSMSActivateGetCountryInfoList() {
    return new ArrayList<>(this.smsActivateCountryInfoMap.values());
  }
}
