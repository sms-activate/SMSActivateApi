# SMSActivateGetCurrentActivations

Метод | Описание
------------- | -------------
[**getCurrentActivationList**](SMSActivateGetCurrentActivations.md#getCurrentActivationList) | Возращает список текущих активаций.
[**getActivationByPhoneNumber**](SMSActivateGetCurrentActivations.md#getActivationByPhoneNumber) | Возращает активацию по номеру телефона.
[**getActivationById**](SMSActivateGetCurrentActivations.md#getActivationById) | Возращает активацию по ее идентификаторку.
[**getActivationsByServiceName**](SMSActivateGetCurrentActivations.md#getActivationsByServiceName) | Возращает список активаций по сервису.
[**getCurrentActivationsByCountryId**](SMSActivateGetCurrentActivations.md#getCurrentActivationsByCountryId) | Возращает список активаций по идентификатору страны.
[**getCurrentActivationWithForward**](SMSActivateGetCurrentActivations.md#getCurrentActivationWithForward) | Возращает активации с переадресацией.
[**getCurrentActivationWithoutForward**](SMSActivateGetCurrentActivations.md#getCurrentActivationWithoutForward) | Возращает активации без переадресации.
[**isExistNext**](SMSActivateGetCurrentActivations.md#isExistNext) | Индикатор, который означает, что возможен ли повторный запрос текущих активаций.
[**getQuant**](SMSActivateGetCurrentActivations.md#getQuant) | Возращает общее количество текущих активаций.

<a name="getCurrentActivationList"></a>
## **getCurrentActivationList**

Возращает список текущих активаций.

Принимаемые праметры:
`отсутсвуют`

Возвращаемый тип:
List<[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)>

<hr/>

<a name="getActivationByPhoneNumber"></a>
## getActivationByPhoneNumber

Возращает активацию по номеру телефона.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
phoneNumber | long | Номер телефона. | Да.

Возвращаемый тип:
[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)

<hr/>

<a name="getActivationById"></a>
## getActivationById

Возращает активацию по ее идентификаторку.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
id | int | Идентификатор активации. | Да.

Возвращаемый тип:
[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)

<hr/>

<a name="getActivationsByServiceName"></a>
## getActivationsByServiceName

Возращает список активаций по сервису.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
serviceShortName | String | Короткое имя сервиса. | Да.

Возвращаемый тип:
List<[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)>

<hr/>

<a name="getCurrentActivationsByCountryId"></a>
## getCurrentActivationsByCountryId

Возращает список активаций по идентификатору страны.

Принимаемые параметры:

Имя | Тип | Описание | Обязательный
---- | ---- | ---- | ----
countryId | int | Идентификатор страны. | Да.

Возвращаемый тип:
List<[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)>

<hr/>

<a name="getCurrentActivationWithForward"></a>
## getCurrentActivationWithForward

Возращает активации с переадресацией.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
List<[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)>

<hr/>

<a name="getCurrentActivationWithoutForward"></a>
## getCurrentActivationWithoutForward

Возращает активации без переадресации.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
List<[**SMSActivateCurrentActivation**](extra/SMSActivateCurrentActivation.md)>

<hr/>

<a name="isExistNext"></a>
## isExistNext

Индикатор, который означает, что возможен ли повторный запрос текущих активаций.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`boolean`

<hr/>

<a name="getQuant"></a>
## getQuant

Возращает общее количество текущих активаций.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`int`