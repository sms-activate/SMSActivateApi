package example.api.activation;

import com.sms_activate.SMSActivateApi;
import com.sms_activate.client_enums.SMSActivateClientStatus;
import com.sms_activate.error.base.SMSActivateBaseException;
import com.sms_activate.error.base.SMSActivateBaseTypeError;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.response.api_activation.SMSActivateActivation;
import com.sms_activate.response.api_activation.SMSActivateGetMultiServiceNumberResponse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * In addition to simple activation,
 * you can get a multi-service one,
 * that is, one activation can be used for several services.
 */
public class GetMultiServiceRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // 1. Set referral identifier if it was registered by sms-activate.
//      smsActivateApi.setRef("YOUR_REFERRAL_IDENTIFIER");

      // multi-service number is an activation that can be used for more than 1 service.

      Set<String> operatorSet = new HashSet<>();
      operatorSet.add("mts");
      operatorSet.add("tele2");

      Map<String, Boolean> serviceMap = new HashMap<>();
      serviceMap.put("av", false); // true - number with forward.
      serviceMap.put("sd", false);

      // 2. Request to get multi-service number.
      SMSActivateGetMultiServiceNumberResponse smsActivateGetMultiServiceNumberResponse = smsActivateApi.getMultiServiceNumber(0, serviceMap, operatorSet);

      // print info about activations
      smsActivateGetMultiServiceNumberResponse.getSMSActivateActivationList().forEach(activation -> {
        System.out.println("Id: " + activation.getId());
        System.out.println("Number: " + activation.getNumber());
        System.out.println("Service name: " + activation.getShortName());
        System.out.println("===============================================");
      });

      for (SMSActivateActivation activation : smsActivateGetMultiServiceNumberResponse.getSMSActivateActivationList()) {
        //for the test we send CANCEL status
        smsActivateApi.setStatus(activation, SMSActivateClientStatus.CANCEL);
      }

    } catch (SMSActivateWrongParameterException e) {
      if (e.getWrongParameter() == SMSActivateWrongParameter.BAD_ACTION) {
        System.out.println("Contact support.");
      }
      if (e.getWrongParameter() == SMSActivateWrongParameter.BAD_KEY) {
        System.out.println("Your api-key is incorrect.");
      }

      // todo check other wrong parameter
      System.out.println(e.getMessage() + "  " + e.getMessage());
    } catch (SMSActivateBaseException e) {
      if (e.getTypeError() == SMSActivateBaseTypeError.NO_BALANCE) {
        System.out.println("Top up balance.");
      }
      if (e.getTypeError() == SMSActivateBaseTypeError.NO_NUMBERS) {
        System.out.println("Send request later....");
      }

      // todo check other type error
      System.out.println(e.getTypeError() + "  " + e.getMessage());
    }
  }
}
