package example.api.activation;

import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.response.api_activation.SMSActivateGetBalanceAndCashBackResponse;

import java.math.BigDecimal;

/**
 * This example shows how you can get your account balance.
 */
public class GetBalanceAndCashBackRun {
  public static void main(String[] args) {
    try {
      SMSActivateApi smsActivateApi = new SMSActivateApi("API_KEY");

      System.out.println("Your api-key: " + smsActivateApi.getApiKey());

      // request balance
      BigDecimal balance = smsActivateApi.getBalance();

      //request balance and cashback
      SMSActivateGetBalanceAndCashBackResponse smsActivateGetBalanceAndCashBackResponse = smsActivateApi.getBalanceAndCashBack();

      // print info about score
      System.out.println("Balance: " + balance);
      System.out.println("Cashback: " + smsActivateGetBalanceAndCashBackResponse.getCashBack());
      System.out.println("Cashback + balance: " + smsActivateGetBalanceAndCashBackResponse.getBalanceAndCashBack());
    } catch (SMSActivateBaseException e) {
      System.out.println(e.getMessage());
    }
  }
}
