package example.api.activation;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.client_enums.SMSActivateClientStatus;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.response.api_activation.SMSActivateActivation;
import org.jetbrains.annotations.NotNull;

/**
 * This class shows how to deal with activation statuses.
 * <ul>
 *   <li>If you need to receive an SMS, then you need to set the MESSAGE_WAS_SENT status in advance;</li>
 *   <li>If you need to cancel the activation, then you need to send the CANCEL status (activation, which has already received an SMS, can only be completed);</li>
 *   <li>If you need additional SMS, then send the REQUEST_ONE_MORE_CODE status;</li>
 *   <li>To complete the activation, send the status (you can complete the activation only if you received an SMS) - FINISH.</li>
 * </ul>
 * <br/>
 * After changing the status, you will receive an answer about how the change went:
 * <ul>
 *   <li>READY - number readiness confirmed;</li>
 *   <li>RETRY_GET - waiting new sms;</li>
 *   <li>ACTIVATION - service has been activate;</li>
 *   <li>CANCEL - activation is canceled.</li>
 * </ul>
 * <p>For the ot + redirect service, you can send the FAKE_SMS status.</p>
 */
public class GetAndSetStatusRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      // set listener on error
      smsActivateApi.setSmsActivateExceptionListener(errorFromServer -> {
        System.out.println("Error from server: " + errorFromServer);
      });

      // set listener on requests
      smsActivateApi.setSmsActivateWebClientListener((int cid, @NotNull String request, int statusCode, @NotNull String response) -> {
        System.out.printf(
          "CID: %d REQUEST: %s RESPONSE: %s\n",
          cid, request, response
        );
      });

      // 1. Set referral identifier if it was registered by sms-activate.
//      smsActivateApi.setRef("YOUR_REFERRAL_IDENTIFIER");

      // 2. Request to get number.
      SMSActivateActivation activation = smsActivateApi.getNumber(0, "ot");
      // print info about activation
      System.out.println(activation);

      // check: https://sms-activate.ru/ru/getNumber

      // 3. we set the status that the activation is ready to receive sms
      smsActivateApi.setStatus(activation, SMSActivateClientStatus.MESSAGE_WAS_SENT);

      String code = smsActivateApi.waitSms(activation, 1);
      System.out.println("Code from sms: " + code);

      // 4. if your service timed out and you need a new code (repeated), then you can simply set the REQUEST_ONE_MORE_CODE status,
      // after which you can request a new SMS from your service.
//       SMSActivateSetStatusResponse smsActivateSetStatusResponse = smsActivateApi.setStatus(activation, SMSActivateClientStatus.REQUEST_ONE_MORE_CODE);
//       if (smsActivateSetStatusResponse.getSMSActivateAccessStatus() == SMSActivateServerStatus.RETRY_GET) {
//         code = smsActivateApi.waitSms(activation, 5);
//         System.out.println("New code from sms: " + code);
//       }

      if (code == null) {
        // if you have not received any messages you need sending CANCEL status
        smsActivateApi.setStatus(activation, SMSActivateClientStatus.CANCEL);
        System.out.println("activation is canceled");
      } else {
        // activation is paid if you have at least one message. You need sending FINISH status
        smsActivateApi.setStatus(activation, SMSActivateClientStatus.FINISH);
        System.out.println("activation is finished successfully");
      }
    } catch (Exception ex) {
      if (ex instanceof SMSActivateBaseException) {
        // todo check type error
        System.out.println(((SMSActivateBaseException) ex).getTypeError());
        System.out.println(ex.getMessage());
      }
    }
  }
}
