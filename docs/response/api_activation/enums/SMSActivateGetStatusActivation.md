# SMSActivateGetStatusActivation

## *Значения статуса*:

Имя | Описание
---- | ----
<em>**WAIT_CODE**</em> | Ожидание смс.
<em>**WAIT_RETRY**</em> | Ожидание уточнения кода.
<em>**WAIT_RESEND**</em> | Ожидание повторной отправки смс.
<em>**CANCEL**</em> | Активация отменена.
<em>**OK**</em> | Код получен.

<hr/>

## *Методы*:

Метод | Описание
------------- | -------------
[**getMessage**](SMSActivateGetStatusActivation.md#getMessage) |Возращает описание статуса активации.
[**getEnglishMessage**](SMSActivateGetStatusActivation.md#getEnglishMessage) | Возращает описание статуса активации на английском языке.
[**getRussianMessage**](SMSActivateGetStatusActivation.md#getRussianMessage) | Возвращает описание статуса активации на русском языке.

<hr/>

<a name="getMessage"></a>
## **getMessage**

Возвращает описание статуса.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`

<hr/>

<a name="getEnglishMessage"></a>
## **getEnglishMessage**

Возвращает описание статуса на английском языке.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`

<hr/>

<a name="getRussianMessage"></a>
## **getRussianMessage**

Возвращает описание статуса на русском языке.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`
