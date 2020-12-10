package example.api.rent;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.client_enums.SMSActivateClientRentStatus;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.base.SMSActivateBaseTypeError;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.api_rent.SMSActivateGetRentStatusResponse;
import ru.sms_activate.response.api_rent.extra.SMSActivateRentActivation;
import ru.sms_activate.response.api_rent.extra.SMSActivateSMS;

import java.util.List;

/**
 * The example shows how you can receive all SMS that came to a specific rental number.
 */
public class GetRentStatusRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // set listener on error
      smsActivateApi.setSmsActivateExceptionListener(errorFromServer -> {
        System.out.println("Error: " + errorFromServer);
      });

      // set listener on requests
      smsActivateApi.setSmsActivateWebClientListener((cid, request, statusCode, response) -> {
        System.out.printf(
          "CID: %d REQUEST: %s STATUS_CODE: %d RESPONSE: %s\n",
          cid, request, statusCode, response
        );
      });

      // 1. Request to get rent number.
      SMSActivateRentActivation rentActivation = smsActivateApi.getRentNumber(0, "ot");

      // print info
      System.out.println(rentActivation);

      System.out.println("Send SMS to the number: " + rentActivation.getNumber());

      List<SMSActivateSMS> smsActivateSMS = smsActivateApi.waitSmsForRent(rentActivation, 1);

      if (smsActivateSMS.isEmpty()) {
        System.out.println("Not a single SMS came.");
        System.exit(1);
      }

      // 2. Request to get all sms who came to the rented phone number
      SMSActivateGetRentStatusResponse smsActivateGetRentStatusResponse = smsActivateApi.getRentStatus(rentActivation);

      // count sms in rent
      System.out.println("Count sms: " + smsActivateGetRentStatusResponse.getCountSms());

      // info about each sms
      smsActivateGetRentStatusResponse.getSmsActivateSMSList().forEach(sms -> {
        System.out.println("Phone from: " + sms.getPhoneFrom());
        System.out.println("Text: " + sms.getText());
        System.out.println("Date: " + sms.getDate());
        System.out.println("=========================================");
      });

      //for the test we send CANCEL status
      smsActivateApi.setRentStatus(rentActivation, SMSActivateClientRentStatus.CANCEL);
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
      if (e.getTypeError() == SMSActivateBaseTypeError.WAIT_CODE) {
        System.out.println("No sms...");
      } else {
        // todo check other type error
        System.out.println(e.getTypeError() + "  " + e.getMessage());
      }
    }
  }
}
