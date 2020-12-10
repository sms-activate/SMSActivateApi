package example.api.rent;

import com.sms_activate.SMSActivateApi;
import com.sms_activate.client_enums.SMSActivateClientRentStatus;
import com.sms_activate.error.base.SMSActivateBaseException;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.response.api_rent.SMSActivateGetRentListResponse;
import com.sms_activate.response.api_rent.extra.SMSActivateRentActivation;

/**
 * How to get number and the current list of rented phone numbers.
 */
public class GetRentRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // set listener on requests
      smsActivateApi.setSmsActivateWebClientListener((cid, request, statusCode, response) -> {
        System.out.println("Response: " + response);
      });

      // 1. Request to get rent number
      SMSActivateRentActivation rentActivation = smsActivateApi.getRentNumber(0, "vk", 6);
      // print info about rent
      System.out.println(">> ID: " + rentActivation.getId());
      System.out.println(">> Number: " + rentActivation.getNumber());
      System.out.println(">> End date: " + rentActivation.getEndDate());

      // 2. You can request the current list rent.
      SMSActivateGetRentListResponse smsActivateGetRentListResponse = smsActivateApi.getRentList();

      // print info about each rent
      smsActivateGetRentListResponse.getRentNumberList().forEach(activateRentNumber -> {
        System.out.println("ID: " + activateRentNumber.getId());
        System.out.println("Number: " + activateRentNumber.getNumber());
        System.out.println("========================================");
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
      // todo check
      System.out.println(e.getTypeError() + "  " + e.getMessage());
    }
  }
}
