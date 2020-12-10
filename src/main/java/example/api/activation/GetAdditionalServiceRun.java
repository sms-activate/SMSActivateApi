package example.api.activation;

import com.sms_activate.SMSActivateApi;
import com.sms_activate.client_enums.SMSActivateClientStatus;
import com.sms_activate.error.SMSActivateBannedException;
import com.sms_activate.error.base.SMSActivateBaseException;
import com.sms_activate.error.base.SMSActivateBaseTypeError;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.response.api_activation.SMSActivateActivation;
import org.jetbrains.annotations.NotNull;

/**
 * <p>This class shows how you can get an additional service for your activation.</p>
 * The rule for getting additional service is as follows:
 * <ol>
 *   <li>You need to request a number using the getNumber method with redirection;</li>
 *   <li>Then the MESSAGE_WAS_SENT status is set;</li>
 *   <li>Then you need to send an SMS with a code to the number you took;</li>
 *   <li>Then you can request additional service (only after the SMS arrives);</li>
 * </ol>
 * <small>
 *   Services with redirection for which this service is available:
 *   <ul>
 *     <li>other (ot)</li>
 *     <li>avito (av)</li>
 *     <li>yandex (ya)</li>
 *     <li>youla (ym)</li>
 *   </ul>
 * <p>For the ot + redirect service, you can send the FAKE_SMS status.</p>
 * </small>
 */
public class GetAdditionalServiceRun {
  public static void main(String[] args) {
    try {
      // create SMSActivateApi object for requests
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // Since very often errors can occur during requests, it is better to hang listeners to track and log errors.
      // All listeners must be set before requests!!!!

      // listener to each error (SMSActivateExceptionListener).
      smsActivateApi.setSmsActivateExceptionListener((@NotNull String errorFromServer) -> {
        // write to log files or print to console.
        System.out.println("Error response: " + errorFromServer);
      });

      // listener to each request (SMSActivateWebClientListener)
      smsActivateApi.setSmsActivateWebClientListener((int cid, @NotNull String request, int statusCode, @NotNull String response) -> {
        // write to log files or print to console.
        System.out.printf(
          "NUMBER_OF_REQUEST: %d || REQUEST: %s || STATUS_CODE: %d || RESPONSE: %s\n",
          cid, request, statusCode, response
        );
      });

      // 1. Set referral identifier if it was registered by sms-activate.
//      smsActivateApi.setRef("YOUR_REFERRAL_IDENTIFIER");

      // 2. Request to get number.
      SMSActivateActivation activation = smsActivateApi.getNumber(0, "ot", true);
      // print info
      System.out.println(activation);

      // 3. send MESSAGE_WAS_SENT status after you sent a sms to the number
      // if you are using 'ot' service with redirection and you don't need sms, send FAKE_SMS status
//      smsActivateApi.setStatus(activation, SMSActivateClientStatus.MESSAGE_WAS_SENT);
      smsActivateApi.setStatus(activation, SMSActivateClientStatus.FAKE_SMS);
      // check: https://sms-activate.ru/ru/getNumber

      // 4. Request new activation for additional service
      SMSActivateActivation childActivation = smsActivateApi.getAdditionalService(activation, "av");

      // print info about additional activation
      System.out.println(childActivation);

      // after using numbers send finish status or cancel
      // 5. finish parent activation and cancel child activation.
      smsActivateApi.setStatus(activation, SMSActivateClientStatus.FINISH);
      smsActivateApi.setStatus(childActivation, SMSActivateClientStatus.CANCEL);
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
    } catch (SMSActivateBannedException e) {
      System.out.println("Your account has been banned wait " + e.getEndDate());
    } catch (SMSActivateBaseException e) {
      if (e.getTypeError() == SMSActivateBaseTypeError.NO_BALANCE) {
        System.out.println("Top up balance.");
      }
      if (e.getTypeError() == SMSActivateBaseTypeError.NO_NUMBERS) {
        System.out.println("Send request later....");
      } else {
        // todo check other type error
        System.out.println(e.getTypeError() + "  " + e.getMessage());
      }
    }
  }
}
