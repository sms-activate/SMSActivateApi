# SMSActivateApi

Метод | HTTP запрос | Описание
------------- | ------------- | -------------
[**getBalance**](SMSActivateApi.md#getBalance) | **GET** | Возращает текущий баланс на аккаунте.
[**getBalanceAndCashBack**](SMSActivateApi.md#getBalanceAndCashBack) | **GET** | Возращает баланс и кэшбэк на аккаунте.
[**getNumbersStatusByDefaultSettingFromSite**](SMSActivateApi.md#getNumbersStatusByDefaultSettingFromSite) | **GET** | Возращает информацию о доступных сервисах исходя из настроек сайтов.
[**getNumbersStatus**](SMSActivateApi.md#getNumbersStatus) | **GET** | Возращает информаию о доступных сервисах.
[**getNumber**](SMSActivateApi.md#getNumber) | **GET** | Возращает активацию.
[**getMultiServiceNumber**](SMSActivateApi.md#getMultiServiceNumber) | **GET** | Возращает активацию для нескольких сервисов.
[**setStatus**](SMSActivateApi.md#setStatus) | **GET** | Устанавливает статус активации.
[**setStatusWithForwardPhone**](SMSActivateApi.md#setStatusWithForwardPhone) | **GET** | Устанавливает статус.
[**getStatus**](SMSActivateApi.md#getStatus) | **GET** | Возвращает статус активации.
[**getFullSms**](SMSActivateApi.md#getFullSms) | **GET** | Возвращает полную смс, которая пришла на активацию.
[**getAllPrices**](SMSActivateApi.md#getAllPrices) | **GET** | Возращает полный список цен на активации.
[**getPricesAllServicesByCountryId**](SMSActivateApi.md#getPricesAllServicesByCountryId) | **GET** | Возращает полный список цен на активации по идентификатору страны.
[**getPricesAllCountryByServiceShortName**](SMSActivateApi.md#getPricesAllCountryByServiceShortName) | **GET** | Возращает полный список цен на активации для всех стран по сервису.
[**getPricesByCountryIdAndServiceShortName**](SMSActivateApi.md#getPricesByCountryIdAndServiceShortName) | **GET** | Возращает полный список цен на активации стране и сервису. 
[**getCountries**](SMSActivateApi.md#getCountries) | **GET** | Возращает полный список всех стран. 
[**getQiwiRequisites**](SMSActivateApi.md#getQiwiRequisites) | **GET** | Возращает актуальные реквизиты Киви кошелька для пополнения баланса. 
[**getAdditionalService**](SMSActivateApi.md#getAdditionalService) | **GET** | Возращает дополнительный сервис для номера с переадрисацией. 
[**getRentServicesAndCountries**](SMSActivateApi.md#getRentServicesAndCountries) | **GET** | Возращает список сервисов и стран, которые поддерживают аренду.
[**getRentNumber**](SMSActivateApi.md#getRentNumber) | **GET** | Возвращает арендовый номер.
[**getRentStatus**](SMSActivateApi.md#getRentStatus) | **GET** | Возращает статус аренды.
[**setRentStatus**](SMSActivateApi.md#setRentStatus) | **GET** | Устанавливает статус аренды.
[**getRentList**](SMSActivateApi.md#getRentList) | **GET** | Возращает список арендованых номеров.
[**waitSms**](SMSActivateApi.md#waitSms) | **GET** | Останавливает поток на заданное число минут до прихода СМС на активацию, после чего возвращает текст СМС.
[**waitSmsForRent**](SMSActivateApi.md#waitSmsForRent) | **GET** | Останавливает поток на заданное число минут до прихода СМС на арендованный номер, после чего список СМС.

<hr/>

<a name="getBalance"></a>
## **getBalance**

Возращает текущий баланс на аккаунте.

Принимаемые праметры:
`отсутсвуют`

Возвращаемый тип:
`BigDecimal`

<hr/>

<a name="getBalanceAndCashBack"></a>
## **getBalanceAndCashBack**

Возращает сумму баланса и кэшбэка, как полный баланс на аккаунте.

Принимаемые праметры:
`отсутсвуют`

Возвращаемый тип:
[**SMSActivateGetBalanceAndCashBack**](response/api_activation/SMSActivateGetBalanceAndCashBack.md)

<hr/>

<a name="getNumbersStatusByDefaultSettingFromSite"></a>
## **getNumbersStatusByDefaultSettingFromSite**

Возвращает информацию о сервисах, исходя из настроек сайта.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
[**SMSActivateGetNumbersStatus**](response/api_activation/SMSActivateGetNumbersStatusResponse.md)

<hr/>

<a name="getNumbersStatus"></a>
## **getNumbersStatus**

Возращает информацию о доступных сервисах.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
------------- | ------------- | ------------- |  -------------
countryId | Integer | Идентификатор страны. | Да.
operatorSet | Set<String> | Set из операторов, которые доступны у сервиса. | Да.

Возвращаемый тип:
[**SMSActivateGetNumbersStatus**](response/api_activation/SMSActivateGetNumbersStatusResponse.md)

<hr/>

<a name="getNumber"></a>
## **getNumber**

Возращает активацию.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
------------- | ------------- | ------------- | ------------- 
countryId | int | Идентификатор страны. | Да.
service | String | Короткое имя сервиса. | Да.
forward | boolean | Необходимо ли запросить номер с переадресацией. | Нет.
operatorSet | Set<String> | Набор операторов. | Нет.
phoneException | Set<String> | Исключающие префиксы только для России, набор чисел от 3 до 6 (7918,7900111). | Нет.

Возвращаемый тип:
[**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md)

<hr/>

<a name="getMultiServiceNumber"></a>
## **getMultiServiceNumber**

Возращает активацию для нескольких сервисов. 

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
------------- | ------------- | ------------- | ------------- 
countryId | int | Идентификатор страны. | Да.
serviceMap | Set<String> | Набор сервисов, для которых приобретация активация. | Да.
operatorSet | Set<String> | Набор операторов. | Нет.
multiForwardList | List<String> | Список сервисов с переадрисацей. | Нет.

Возвращаемый тип:
[**SMSActivateGetMultiServiceNumberResponse**](response/api_activation/SMSActivateGetMultiServiceNumberResponse.md)

<hr/>

<a name="setStatus"></a>
## **setStatus**

Устанавливает статус активации.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
activationId | int | Идентификатор активации | Да.
status | [**SMSActivateClientStatus**](client_enums/SMSActivateClientStatus.md) | Статус, который нужно установить активации.| Да.

Вместо идентификатора активации можно передать полученный объет класса [**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md) 
после использования метода [**getNumber**](SMSActivateApi.md#getNumber) или одну из активаций после использования [**getMultiServiceNumber**](SMSActivateApi.md#getMultiServiceNumber).

Возвращаемый тип:
[**SMSActivateSetStatusResponse**](response/api_activation/SMSActivateSetStatusResponse.md)

<hr/>

<a name="setStatusWithForwardPhone"></a>
## **setStatusWithForwardPhone**

Устанавливает статус активации вместе с номером телефона, на который нужно выполнить переадресацию.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный.
------------- | ------------- | ------------- | ------------- 
activationId | int | Идентификатор активации. | Да.
status | [**SMSActivateClientStatus**](client_enums/SMSActivateClientStatus.md) | Статус активации, который нужно установить. | Да.
forwardPhone | Long | Номер телефона на который нужно выполнить переадресацию. | Да.

Возвращаемый тип:
[**SMSActivateSetStatusResponse**](response/api_activation/SMSActivateSetStatusResponse.md)

<hr/>

<a name=""></a>
## **getStatus**

Возращает статус активации.

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
activationId | int | Идентификатор активации. | Да.

Вместо идентификатора активации можно передать полученный объет класса [**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md)
после использования метода [**getNumber**](SMSActivateApi.md#getNumber) или одну из активаций после использования [**getMultiServiceNumber**](SMSActivateApi.md#getMultiServiceNumber).

Возвращаемый тип:
[**SMSActivateGetStatusResponse**](response/api_activation/SMSActivateGetStatusResponse.md)

<hr/>

<a name="getFullSms"></a>
## **getFullSms**

Возвращает полную СМС, которая пришла на активацию.

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
activationId | int | Идентификатор активации. | Да.

Вместо идентификатора активации можно передать полученный объет класса [**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md)
после использования метода [**getNumber**](SMSActivateApi.md#getNumber) или одну из активаций после использования [**getMultiServiceNumber**](SMSActivateApi.md#getMultiServiceNumber).

Возвращаемый тип:
[**SMSActivateGetFullSmsResponse**](response/api_activation/SMSActivateGetFullSmsResponse.md)
<a name="getAllPrices"></a>
## **getAllPrices**
Возвращает полный список цен на активации

Принимаемые параметры:
`отсуствуют`

Возвращаемый тип:
[**SMSActivateGetPricesResponse**](response/api_activation/SMSActivateGetPricesResponse.md)

<hr/>

<a name="getPricesAllServicesByCountryId"></a>
## **getPricesAllServicesByCountryId**

Возращает полный список сервисов по идентификатору страны.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
countryId | int | Идентификатор страны. | Да.

Возвращаемый тип:
[**SMSActivateGetPricesResponse**](response/api_activation/SMSActivateGetPricesResponse.md)

<hr/>

<a name="getPricesAllCountryByServiceShortName"></a>
## **getPricesAllCountryByServiceShortName**

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
serviceShortName | String | Короткое имя сервиса | Да.

Возвращаемый тип:
[**SMSActivateGetPriceInfo**](response/api_activation/extra/SMSActivateGetPriceInfo.md)

<hr/>

<a name="getPricesByCountryIdAndServiceShortName"></a>
## **getPricesByCountryIdAndServiceShortName**

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
countryId | Integer | Идентификатор страны. | Да.
serviceShortName | String | Короткое имя сервиса | Да.

Возвращаемый тип:
[**SMSActivateGetPriceInfo**](response/api_activation/extra/SMSActivateGetPriceInfo.md)

<hr/>

<a name="getCountries"></a>
## **getCountries**

Возращает полный список всех стран.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
[**SMSActivateGetCountriesResponse**](response/api_activation/SMSActivateGetCountriesResponse.md)

<hr/>

<a name="getQiwiRequisites"></a>
## **getQiwiRequisites**

Возвращает актуальный реквизиты киви кошелька для пополнения баланса.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
[**SMSActivateGetQiwiRequisitesResponse**](response/qiwi/SMSActivateGetQiwiRequisitesResponse.md)

<hr/>

<a name="getAdditionalService"></a>
## **getAdditionalService**

Возвращает дополнительный сервис для номера с переадрисацией.

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
activationId | int | Идентификатор активации. | Да.
service | String | Короткое имя сервиса. | Да.

Вместо идентификатора активации можно передать полученный объет класса [**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md)
после использования метода [**getNumber**](SMSActivateApi.md#getNumber) или одну из активаций после использования [**getMultiServiceNumber**](SMSActivateApi.md#getMultiServiceNumber).

Возвращаемый тип:
[**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md)

<hr/>

<a name="getRentServicesAndCountries"></a>
## **getRentServicesAndCountries**

Возвращает список сервисов и стран, поддерживают аренду.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный 
---- | ---- | ---- | ---- 
countryId | int | Идентификатор страны. | Да. 
operatorSet | Set<String> | Набор сотовых операторов
hours | int | Время аренды | 

Возвращаемый тип:
[**SMSActivateGetRentServicesAndCountriesResponse**](response/api_rent/SMSActivateGetRentServicesAndCountriesResponse.md)

<hr/>

<a name="getRentNumber"></a>
## **getRentNumber**

Возращает арендовый номер.

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
countryId | int | Идентификатор страны. | Да.
service | String | Короткое имя сервиса. | Да.
hours | int | Время аренды. | Нет.
operator | String | Имя мобильного оператора. | Нет.
urlWebhook | String | ... | Нет.

Возвращаемый тип:
[**SMSActivateRentActivation**](response/api_rent/SMSActivateRentActivation.md)

<hr/>

<a name="getRentStatus"></a>
## **getRentStatus**

Возвращает статус аренды.

Имя | Тип | Описание | Обязательный.
---- | ---- | ---- | ----
rentId | int | Идентификатор аредованного номера телефона. | Да.

Вместо rentId можно передать объект [**SMSActivateRentActivation**](response/api_rent/SMSActivateRentActivation.md),
который был получен после использования метода [**getRentNumber**](SMSActivateApi.md#getRentNumber).

Возвращаемый тип:
[**SMSActivateGetRentStatusResponse**](response/api_rent/SMSActivateGetRentStatusResponse.md)

<hr/>

<a name="setRentStatus"></a>
## **setRentStatus**

Устанавливает статус аренде.

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
rentId | int | Идентификатор арендованного телефона. | Да.
status | [**SMSActivateClientRentStatus**](client_enums/SMSActivateClientRentStatus.md) | Статус аренды, который нужно установить. | Да.

Вместо rentId можно передать объект [**SMSActivateRentActivation**](response/api_rent/SMSActivateRentActivation.md),
который был получен после использования метода [**getRentNumber**](SMSActivateApi.md#getRentNumber).

Возвращаемый тип:
[**SMSActivateRentStatus**](response/api_rent/enums/SMSActivateRentStatus.md)

<hr/>

<a name="getRentList"></a>
## **getRentList**

Возращает список арендованых номеров.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
[**SMSActivateGetRentListResponse**](response/api_rent/SMSActivateGetRentListResponse.md)

<hr/>

<a name="waitSms"></a>
## **waitSms**

Останавливает поток на заданное число минут до прихода СМС на активацию, после чего возвращает текст СМС.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
activationId | int | Идентификатор активации. | Да.
maxWaitMinutes | int | Количество минут, которое необходимо ожидать СМС. | Да.

Вместо идентификатора активации можно передать полученный объет класса [**SMSActivateActivation**](response/api_activation/SMSActivateActivation.md)
после использования метода [**getNumber**](SMSActivateApi.md#getNumber) или одну из активаций после использования [**getMultiServiceNumber**](SMSActivateApi.md#getMultiServiceNumber).

Возвращаемый тип:
`String`

<hr/>

<a name="waitSmsForRent"></a>
## **waitSmsForRent**

Останавливает поток на заданное число минут до прихода СМС на арендованный номер, после чего список СМС.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
rentId | int | Идентификатор аренды. | Да.
maxWaitMinutes | int | Количество минут, которое необходимо ожидать СМС. | Да.

Вместо rentId можно передать объект [**SMSActivateRentActivation**](response/api_rent/SMSActivateRentActivation.md),
который был получен после использования метода [**getRentNumber**](SMSActivateApi.md#getRentNumber).

Возвращаемый тип:
List<[**SMSActivateSMS**](response/api_rent/extra/SMSActivateSMS.md)>
