# SMSActivateBaseTypeError

## Значения:

Имя | Описание
---- | ----
<em>**UNKNOWN**</em> | Неизвестный тип ошибки.
<em>**NO_BALANCE**</em> | Нет денег на счету.
<em>**NO_NUMBERS**</em> | На данный момент номеров нет.
<em>**CANT_CANCEL**</em> | Невозможно отменить аренду (более 20 мин.).
<em>**ALREADY_FINISH**</em> | Аренда уже заверщена.
<em>**ALREADY_CANCEL**</em> | Аренда уже отменена.
<em>**WAIT_CODE**</em> |Ожидание первой смс.
<em>**RENT_CANCEL**</em> | Аренда отменена с возвратом денег.
<em>**RENT_FINISH**</em> | Аренда оплачена и завершенна.

<hr/>

## *Методы*:

Метод | Описание
------------- | -------------
[**getMessage**](SMSActivateBaseTypeError.md#getMessage) | Возращает ошибки статуса.
[**getEnglishMessage**](SMSActivateBaseTypeError.md#getEnglishMessage) | Возращает ошибки на английском.
[**getRussianMessage**](SMSActivateBaseTypeError.md#getRussianMessage) | Возращает ошибки статуса на русском.

<hr/>

<a name="getMessage"></a>
## **getMessage**

Возращает описание ошибки.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`

<hr/>

<a name="getEnglishMessage"></a>
## **getEnglishMessage**

Возращает описание ошибки на английском.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`

<hr/>

<a name="getRussianMessage"></a>
## **getRussianMessage**

Возращает описание ошибки на русском.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`String`
