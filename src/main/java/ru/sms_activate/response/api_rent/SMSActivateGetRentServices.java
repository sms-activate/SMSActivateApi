package ru.sms_activate.response.api_rent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_rent.extra.SMSActivateGetRentService;

import java.util.*;

public class SMSActivateGetRentServices {
  /**
   * Map where key is name operators.
   */
  private Map<String, String> operators;

  /**
   * Map where key is id countries.
   */
  private Map<String, String> countries;

  /**
   * Map rent services where key is short name service.
   */
  private Map<String, SMSActivateGetRentService> services;

  /**
   * Time rent.
   */
  private int realHours;

  private SMSActivateGetRentServices() {
  }

  /**
   * Returns the rent services.
   *
   * @return rent services
   */
  @NotNull
  public Map<String, SMSActivateGetRentService> getServices() {
    return services;
  }

  /**
   * Returns the service rent by name.
   *
   * @param serviceShortName short service name.
   * @return service rent.
   * @throws SMSActivateWrongParameterException if service name is incorrect.
   */
  @NotNull
  public SMSActivateGetRentService getRentServiceByShortName(@NotNull String serviceShortName) throws SMSActivateWrongParameterException {
    SMSActivateGetRentService rentService = services.get(serviceShortName);

    if (rentService == null) {
      throw new SMSActivateWrongParameterException("Service name is incorrect", "Неккоректное имя сервиса.");
    }

    return rentService;
  }

  /**
   * Returns the map countries.
   *
   * @return map countries.
   */
  @NotNull
  public Map<String, String> getCountries() {
    return countries;
  }

  /**
   * Returns the map operators.
   *
   * @return map operators.
   */
  @NotNull
  public Map<String, String> getOperators() {
    return operators;
  }

  /**
   * Returns the full name operator by short name.
   *
   * @param shortName operator short name.
   * @return full name operator by short name.
   * @throws SMSActivateWrongParameterException if operator name is incorrect.
   */
  @NotNull
  public String getFullOperatorNameByShortName(@NotNull String shortName) throws SMSActivateWrongParameterException {
    String operatorName = operators.get(shortName);

    if (operatorName == null) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_OPERATOR);
    }

    return operatorName;
  }

  /**
   * Returns the all rent services info.
   *
   * @return all rent services info.
   */
  @Nullable
  public Map<String, SMSActivateGetRentService> getAllRentServices() {
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
    SortedSet<Integer> idCountryIntSet = new TreeSet<>();

    for (String countryId : countries.keySet()) {
      idCountryIntSet.add(Integer.parseInt(countryId));
    }

    return idCountryIntSet;
  }

  /**
   * Returns the set of names countries supported rent.
   *
   * @return set of names countries supported rent.
   */
  @NotNull
  public SortedSet<String> getCountryNameSet() {
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

  /**
   * Returns the set of short names operator.
   *
   * @return set of short names operator.
   */
  @NotNull
  public Set<String> getOperatorShortNameSet() {
    return new HashSet<>(operators.keySet());
  }

  @Override
  public String toString() {
    return "SMSActivateGetRentServices{" +
      "operators=" + operators +
      ", countries=" + countries +
      ", services=" + services +
      '}';
  }
}
