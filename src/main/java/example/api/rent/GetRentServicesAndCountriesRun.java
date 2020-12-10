package example.api.rent;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_rent.SMSActivateGetRentServicesAndCountriesResponse;

/**
 * To get all countries with rent and data about services by country use the getRentServicesAndCountries method.
 */
public class GetRentServicesAndCountriesRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");
      // request services and countries where rent is supported
      SMSActivateGetRentServicesAndCountriesResponse smsActivateGetRentServicesAndCountriesResponse =
        smsActivateApi.getRentServicesAndCountries(0, null, 4);

      // print info about service
      System.out.println("Services: ");

      // getAllRentServices - returns the map with all services where key is service shortname.
      smsActivateGetRentServicesAndCountriesResponse.getAllRentServices().forEach((serviceShortName, smsActivateRentService) -> {
        System.out.println(">> Service shortname: " + serviceShortName);
        System.out.println(">> count phone numbers: " + smsActivateRentService.getCountPhoneNumbers());
        System.out.println(">> Cost: " + smsActivateRentService.getCost());
        System.out.println("====================================");
      });

      System.out.println();
      System.out.println();

      // print all name operators.
      System.out.println("Operators: ");
      smsActivateGetRentServicesAndCountriesResponse.getOperatorNameSet()
        .forEach(operatorName -> System.out.println(">> name: " + operatorName));

      System.out.println();
      System.out.println();

      // print all country who supported rent.
      System.out.println("Countries supported rent: ");
      smsActivateGetRentServicesAndCountriesResponse.getCountryIdSet()
        .forEach(countryId -> System.out.println(">> id: " + countryId));
    } catch (SMSActivateWrongParameterException e) {
      if (e.getWrongParameter() == SMSActivateWrongParameter.BAD_ACTION) {
        System.out.println("Contact support.");
      }
      if (e.getWrongParameter() == SMSActivateWrongParameter.BAD_KEY) {
        System.out.println("Your api-key is incorrect.");
      } else {
        // todo check other wrong parameter
        System.out.println(e.getWrongParameter() + "  " + e.getMessage());
      }
    } catch (SMSActivateBaseException e) {
      // todo check type error
      System.out.println(e.getTypeError() + "  " + e.getMessage());
    }
  }
}
