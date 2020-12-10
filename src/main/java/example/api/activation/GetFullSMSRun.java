package example.api.activation;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.client_enums.SMSActivateClientStatus;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.base.SMSActivateBaseTypeError;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_activation.SMSActivateActivation;
import ru.sms_activate.response.api_activation.SMSActivateGetFullSmsResponse;

/**
 * This example demonstrates how you can receive a full SMS on activation.
 */
public class GetFullSMSRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // set listener on requests
      smsActivateApi.setSmsActivateWebClientListener((cid, request, statusCode, response) -> System.out.printf(
        "CID: %d REQUEST: %s RESPONSE: %s STATUS_CODE: %d\n",
        cid, request, response, statusCode
      ));

      // 1. Set referral identifier if it was registered by sms-activate.
//      smsActivateApi.setRef("YOUR_REFERRAL_IDENTIFIER");

      // 2. Request to get number.
      SMSActivateActivation activation = smsActivateApi.getNumber(0, "ot");

      // 3. Set status SEND_READY_NUMBER.
      // To receive SMS with a code from the service, you must first set the MESSAGE_WAS_SENT
      // status after your activation will be ready to receive SMS.
      // the number must be used on the service for which you took it, else SMS will not come to it
      System.out.println("Please use your activation " + activation.getNumber() + " with ID " + activation.getId());
      smsActivateApi.setStatus(activation, SMSActivateClientStatus.MESSAGE_WAS_SENT);

      String code = smsActivateApi.waitSms(activation, 2);

      if (code == null) {
        // if you have not received any messages you need sending CANCEL status
        smsActivateApi.setStatus(activation, SMSActivateClientStatus.CANCEL);
      } else {
        SMSActivateGetFullSmsResponse smsActivateGetFullSmsResponse = smsActivateApi.getFullSms(activation);
        System.out.println("Full sms: " + smsActivateGetFullSmsResponse.getText());
        // activation is paid if you have at least one message. You need sending FINISH status
        smsActivateApi.setStatus(activation, SMSActivateClientStatus.FINISH);
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
