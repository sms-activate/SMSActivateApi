# SMSActivateGetRentServicesAndCountriesResponse

Имя | Описание
---- | ----
[**getRentServiceByShortName**](SMSActivateGetRentServicesAndCountriesResponse.md#getRentServiceByShortName) | Возвращает сервис по его короткому имени.
[**getAllRentServices**](SMSActivateGetRentServicesAndCountriesResponse.md#getAllRentServices) | Возвращает все сервисы.
[**getRentServiceNameSet**](SMSActivateGetRentServicesAndCountriesResponse.md#getRentServiceNameSet) | Возвращает список имен сервисов
[**getCountryIdSet**](SMSActivateGetRentServicesAndCountriesResponse.md#getCountryIdSet) | Возращает список идентификаторов стран.
[**getOperatorNameSet**](SMSActivateGetRentServicesAndCountriesResponse.md#getOperatorNameSet) | Возращает список имен операторов.

<hr/>

<a name="getRentServiceByShortName"></a>
## **getRentServiceByShortName**

Возращает сервис по его короткому имени.

Принимаемые параметры:

Имя | Тип | Описание
---- | ---- | ----
serviceShortName | String | Короткое имя сервиса.

Возвращаемый тип:
[**SMSActivateRentService**](extra/SMSActivateRentService.md)

<hr/>

<a name="getAllRentServices"></a>
## **getAllRentServices**

Возращает список всех сервисов поддеживаемых аренду.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
Map<String, [**SMSActivateRentService**](extra/SMSActivateRentService.md)>

<hr/>

<a name="getRentServiceNameSet"></a>
## **getRentServiceNameSet**

Возвращает список имен сервисов.

Принимаемые параметры:
`отсуствуют`

Возвращаемый тип:
`Set<String>`

<hr/>

<a name="getCountryIdSet"></a>
## **getCountryIdSet**

Возвращает список идентификаторов стран.

Принимаемые параметры:
`отсуствуют`

Возвращаемый тип:
`SortedSet<Integer>`

<hr/>

<a name="getOperatorNameSet"></a>
## **getOperatorNameSet**

Возращает список имен операторов

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`Set<String>`
