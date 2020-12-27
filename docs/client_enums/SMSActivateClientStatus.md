# SMSActivateClientStatus

## *Значения статуса*:

Имя | Описание
---- | ----
<em>**MESSAGE_WAS_SENT**</em> | Завершить активацию.
<em>**FAKE_SMS**</em> | Не нужно принимать смс (только для сервиса любой другой).
<em>**REQUEST_ONE_MORE_CODE**</em> | Запросить еще один код (бесплатно).
<em>**FINISH**</em> | Завершить активацию.
<em>**CANCEL**</em> | Сообщить о том, что номер использован и отменить активацию.

<hr/>

## *Методы*:

Метод | Описание
------------- | -------------
[**getMessage**](SMSActivateClientStatus.md#getMessage) | Возращает описание статуса.
[**getEnglishMessage**](SMSActivateClientStatus.md#getEnglishMessage) | Возращает описание на английском.
[**getRussianMessage**](SMSActivateClientStatus.md#getRussianMessage) | Возращает описание статуса на русском.

<hr/>

<a name="getMessage"></a>
## **getMessage**

Возращает описание статуса.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`

<hr/>

<a name="getEnglishMessage"></a>
## **getEnglishMessage**

Возращает описание статуса на английском.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`

<hr/>

<a name="getRussianMessage"></a>
## **getRussianMessage**

Возращает описание статуса на русском.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`
