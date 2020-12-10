package example.api.activation;

import com.sms_activate.SMSActivateApi;
import com.sms_activate.error.base.SMSActivateBaseException;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.response.api_activation.SMSActivateGetCountriesResponse;
import com.sms_activate.response.api_activation.extra.SMSActivateCountryInfo;
import org.jetbrains.annotations.NotNull;

/**
 * <p>This class shows how you can get information about supported countries.</p>
 * Also, in addition to the names of the countries, the following is contained:
 * <ul>
 *   <li>id - id country, it is often used to take activations for a specific country;</li>
 *   <li>visible - whether the country is shown on the site;</li>
 *   <li>multiService - whether the multiService is available;</li>
 *   <li>retry - whether repeated SMS is available;</li>
 *   <li>rent - whether the rent is available.</li>
 * </ul>
 */
public class GetCountriesRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // set listener on requests
      smsActivateApi.setSmsActivateWebClientListener((cid, request, statusCode, response) -> {
        System.out.println("Response: " + response);
      });

      SMSActivateGetCountriesResponse smsActivateGetCountriesResponse = smsActivateApi.getCountries();

      // 0 (id) - Russia
      // id is needed for request when you need to specify a specific country
      printInfo(smsActivateGetCountriesResponse.get(0));

      // print info about all countries
      smsActivateGetCountriesResponse.getSMSActivateGetCountryInfoList().forEach(GetCountriesRun::printInfo);
    } catch (SMSActivateWrongParameterException e) {
      System.out.println(e.getWrongParameter());
    } catch (SMSActivateBaseException e) {
      System.out.println(e.getTypeError());
      System.out.println(e.getMessage());
    }
  }

  private static void printInfo(@NotNull SMSActivateCountryInfo smsActivateCountryInfo) {
    System.out.println("Country id: " + smsActivateCountryInfo.getId());

    System.out.println("Russian name: " + smsActivateCountryInfo.getRussianName());
    System.out.println("English name: " + smsActivateCountryInfo.getEnglishName());
    System.out.println("Chinese name: " + smsActivateCountryInfo.getChineseName());

    System.out.println("Support multi-service: " + (smsActivateCountryInfo.isSupportMultiService() ? "yes" : "no"));
    System.out.println("Support retry: " + (smsActivateCountryInfo.isSupportRetry() ? "yes" : "no"));
    System.out.println("Visible in site: " + (smsActivateCountryInfo.isVisible() ? "yes" : "no"));
    System.out.println("Support rent: " + (smsActivateCountryInfo.isSupportRent() ? "yes" : "no"));
    System.out.println("====================================================");
  }
}
