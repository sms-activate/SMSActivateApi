package example.api.activation;

import com.sms_activate.SMSActivateApi;
import com.sms_activate.client_enums.SMSActivateClientStatus;
import com.sms_activate.error.SMSActivateBannedException;
import com.sms_activate.error.base.SMSActivateBaseException;
import com.sms_activate.error.base.SMSActivateBaseTypeError;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.response.api_activation.SMSActivateActivation;

/**
 * To get activation for a specific service, use the getNumber method.
 */
public class GetNumberRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // 1. Set referral identifier if it was registered by sms-activate.
//      smsActivateApi.setRef("YOUR_REFERRAL_IDENTIFIER");

      /*Set<String> operatorSet = new HashSet<>();
      operatorSet.add("mts");
      operatorSet.add("tele2");*/

      /*Set<String> phoneExceptionSet = new HashSet<>();
      phoneExceptionSet.add("7918");
      phoneExceptionSet.add("7928");*/

      // 2. Request to get number.
      SMSActivateActivation activation = smsActivateApi.getNumber(
        0,
        "vk"
        /*operatorSet, phoneExceptionSet*/);

      // info about your activation.
      System.out.println(activation);

      //for the test we send CANCEL status
      smsActivateApi.setStatus(activation, SMSActivateClientStatus.CANCEL);

      // check: https://sms-activate.ru/ru/getNumber
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
