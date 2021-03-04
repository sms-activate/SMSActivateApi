package ru.sms_activate.response.api_rent;

import org.jetbrains.annotations.NotNull;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_rent.extra.SMSActivateRentService;

import java.util.*;

public class SMSActivateGetRentServicesAndCountriesResponse {
  /**
   * Map where key is name operators.
   */
  private Map<Integer, String> operators;

  /**
   * Map where key is id countries.
   */
  private Map<Integer, Integer> countries;

  /**
   * Map rent services where key is short name service.
   */
  private Map<String, SMSActivateRentService> services;

  private SMSActivateGetRentServicesAndCountriesResponse() {
  }

  /**
   * Returns the service rent by name.
   *
   * @param serviceShortName short service name.
   * @return service rent.
   * @throws SMSActivateWrongParameterException if service name is incorrect.
   */
  @NotNull
  public SMSActivateRentService getRentServiceByShortName(@NotNull String serviceShortName) throws SMSActivateWrongParameterException {
    SMSActivateRentService rentService = services.get(serviceShortName);

    if (rentService == null) {
      throw new SMSActivateWrongParameterException("Service name is incorrect", "Неккоректное имя сервиса.");
    }

    return rentService;
  }

  /**
   * Returns the all rent services info.
   *
   * @return all rent services info.
   */
  @NotNull
  public Map<String, SMSActivateRentService> getAllRentServices() {
    return services;
  }

  /**
   * Returns the list rent services.
   *
   * @return list rent services.
   */
  @NotNull
  public Set<String> getRentServiceNameSet() {
    return services.keySet();
  }

  /**
   * Returns the set countries supported rent.
   *
   * @return set countries supported rent.
   */
  @NotNull
  public SortedSet<Integer> getCountryIdSet() {
    return new TreeSet<>(countries.values());
  }

  /**
   * Returns the set operators supported rent.
   *
   * @return set operators supported rent.
   */
  @NotNull
  public Set<String> getOperatorNameSet() {
    return new HashSet<>(operators.values());
  }
}
