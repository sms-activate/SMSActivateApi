# SMSActivateGetPricesResponse

Метод | Описание
------------- | -------------
[**getPriceInfo**](SMSActivateGetPricesResponse.md#getPriceInfo) | Возращает id активации.
[**getSmsActivateGetPriceMap**](SMSActivateGetPricesResponse.md#getSmsActivateGetPriceMap) | Возращает номер телефона активации.
[**getCountryIdSet**](SMSActivateGetPricesResponse.md#getCountryIdSet) | Возращает короткое имя сервиса, для которого была взята активация.
[**getServicesByCountryId**](SMSActivateGetPricesResponse.md#getServicesByCountryId) | Возращает короткое имя сервиса, для которого была взята активация.

<a name="getPriceInfo"></a>
## **getPriceInfo**


Принимаемые параметры:

Имя | Тип | Описание | Обязательный
----- | ----- | ----- | ------
countryId | Integer | Идентификатор страны. | Да.
serviceName | String | Короткое имя сервиса. | Да.

Возвращаемый тип:
[**SMSActivateGetPriceInfo**](extra/SMSActivateGetPriceInfo.md)

<a name="getSmsActivateGetPriceMap"></a>
## **getSmsActivateGetPriceMap**

  СПРОСИТЬ КАК ЛУЧШЕ

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
----- | ----- | ----- | ------
countryId | Integer | Идентификатор страны. | Да.

Возвращаемый тип:
Map<String, [**SMSActivateGetPriceInfo**](extra/SMSActivateGetPriceInfo.md)>, где ключи это короткое имя сервиса.


<a name="getCountryIdSet"></a>
## **getCountryIdSet**

Возращает идентификаторы стран.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`SortedSet<Integer>`

<a name="getServicesByCountryId"></a>
## **getServicesByCountryId**

Возращает сервисы по стране.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
----- | ----- | ----- | ------
countryId | Integer | Идентификатор страны. | Да.

Возвращаемый тип:
`Set<String>`
