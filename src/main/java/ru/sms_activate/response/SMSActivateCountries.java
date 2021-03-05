package ru.sms_activate.response;

import org.jetbrains.annotations.NotNull;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.extra.SMSActivateCountry;

import java.util.List;

public class SMSActivateCountries {
  /**
   * List of countries.
   */
  private final List<SMSActivateCountry> countries;

  /**
   * Constructor with list of countries.
   *
   * @param countries list of countries.
   */
  public SMSActivateCountries(@NotNull List<SMSActivateCountry> countries) {
    this.countries = countries;
  }

  /**
   * Returns the list of countries.
   *
   * @return list of countries.
   */
  @NotNull
  public List<SMSActivateCountry> getCountries() {
    return countries;
  }

  /**
   * Returns the country by id.
   *
   * @param id country id.
   * @return country.
   * @throws SMSActivateWrongParameterException if country id is incorrect.
   */
  @NotNull
  public SMSActivateCountry getCountryById(int id) throws SMSActivateWrongParameterException {
    for (SMSActivateCountry country : countries) {
      if (country.getId() == id) {
        return country;
      }
    }

    throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
  }

  @Override
  public String toString() {
    return "SMSActivateCountries{" +
      "countries=" + countries +
      '}';
  }
}
