package example.api.activation;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.response.qiwi.SMSActivateGetQiwiRequisitesResponse;
import ru.sms_activate.response.qiwi.SMSActivateQiwiStatus;

/**
 * If you need to automatically replenish the balance through your payment API, the getQiwiRequisites method will
 * return the necessary details for this.
 */
public class GetQiwiRequisitesRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // 1. Request to get qiwi requisites.
      SMSActivateGetQiwiRequisitesResponse smsActivateGetQiwiRequisitesResponse = smsActivateApi.getQiwiRequisites();

      // 2. If success then you can payment.
      if (smsActivateGetQiwiRequisitesResponse.getStatus() == SMSActivateQiwiStatus.SUCCESS) {
        System.out.println("Comment: " + smsActivateGetQiwiRequisitesResponse.getComment());
        System.out.println("Wallet: " + smsActivateGetQiwiRequisitesResponse.getWallet());
        System.out.println("Up to date: " + smsActivateGetQiwiRequisitesResponse.getUpToDate());
      } else {
        System.out.println("Now payment of kiwi is not possible.");
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
