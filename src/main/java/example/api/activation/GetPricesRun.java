package example.api.activation;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_activation.SMSActivateGetPricesResponse;
import ru.sms_activate.response.api_activation.extra.SMSActivateGetPriceInfo;

import java.util.Set;

/**
 * To get information about current prices use the getPrices method.
 */
public class GetPricesRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // 1. Request to get current prices by specified country and serviceName.
      // 0 - Russia
      SMSActivateGetPricesResponse smsActivateGetPricesResponse = smsActivateApi.getPricesByCountryIdAndServiceShortName(
        0, "vk"
      );

      // getAllPrices - returns full price list.
      // if the country is not important to you and you just need to get a complete list of prices for a specific service,
      // then use getPricesAllCountryByServiceShortName (shortServiceName)

      // if you just need to find out the prices by country and the service is not
      // important then use getPricesAllServicesByCountryId (countryId)

      // if you are interested in a service for a specific country, then use the getPrices (countryId, shortServiceName)

      SMSActivateGetPriceInfo vk = smsActivateGetPricesResponse.getPriceInfo(0, "vk");

      //print info about vk
      System.out.println(">>> Cost: " + vk.getCost());
      System.out.println(">>> count phone numbers: " + vk.getCountPhoneNumbers());

      // output all services
      for (Integer countryId : smsActivateGetPricesResponse.getCountryIdSet()) {
        Set<String> servicesByCountryId = smsActivateGetPricesResponse.getServicesByCountryId(countryId);

        for (String shortName : servicesByCountryId) {
          SMSActivateGetPriceInfo priceInfo = smsActivateGetPricesResponse.getPriceInfo(countryId, shortName);

          System.out.println(">>> Service shortname: " + shortName);
          System.out.println(">>> Cost: " + priceInfo.getCost());
          System.out.println(">>> count phone numbers: " + priceInfo.getCountPhoneNumbers());
          System.out.println("==========================================================================");
        }
      }
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
      // todo check
      System.out.println(e.getTypeError() + "  " + e.getMessage());
    }
  }
}
