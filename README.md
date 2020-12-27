# SMSActivateApi

## Начало работы 

Включите библиотеку в maven.

```xml
<dependecy>
  <groupId>ru.sms-activate</groupId>
  <artifactId>SMSActivateApi</artifactId>
  <version>1.0</version>
</dependecy>
```

Импортируйте главный класс для взаимодействия с API sms-activate.
```java
import ru.sms_activate.SMSActivateApi;
```

Для использования библитеки вам необходим API ключ, его можно получить по ссылкам ниже.

* [SMS-Activate API docs](https://sms-activate.ru/ru/api2)

* [SMS-Activate API-Key](https://sms-activate.ru/ru/profile)

<hr/>

### Получение баланса на вашем аккаунте

Для получение текущего баланса на вашем аккаунте используйте метод [**getBalance**](./docs/SMSActivateApi.md#getBalance). Если вас интересует помимо баланса кэшбэк, то вызовите метод [**getBalanceAndCashBack**](./docs/SMSActivateApi.md#getBalanceAndCashBack).

Метод [**getBalanceAndCashBack**](./docs/SMSActivateApi.md#getBalanceAndCashBack) возращает объект класса [**SMSActivateGetBalanceAndCashBack**](docs/response/api_activation/SMSActivateGetBalanceAndCashBack.md).

`Пример`
```java
import ru.sms_activate.SMSActivateApi;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.response.api_activation.SMSActivateGetBalanceAndCashBackResponse;

import java.math.BigDecimal;

public class Run {
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
```

### *Для большего числа примеров воспользуйтесь репозиторием на [github](https://github.com/sms-activate/examples-using-api/).*
