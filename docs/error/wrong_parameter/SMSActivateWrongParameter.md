# SMSActivateWrongParameter

## Значения:

Имя | Описание
---- | ----
<em>**BAD_ACTION**</em> | Некорректное действие.
<em>**BAD_SERVICE**</em> | Некорректное наименование сервиса.
<em>**BAD_KEY**</em> | Неверный API-ключ.
<em>**BAD_STATUS**</em> | Попытка установить несуществующий статус.
<em>**EMPTY_KEY**</em> | API ключ не может быть пустым.
<em>**INCORRECT_STATUS**</em> | Отсутствует или неправильно указан статус.
<em>**INVALID_PHONE**</em> | Номер арендован не вами (неправильный id аренды).
<em>**NOT_AVAILABLE**</em> | Для страны, которую вы используете, недоступна покупка мультисервисов.
<em>**NO_ID_RENT**</em> | Не указан ID аренды.
<em>**REPEAT_ADDITIONAL_SERVICE**</em> | Ошибка возникает при попытке заказать купленный сервис еще раз.
<em>**WRONG_ACTIVATION_ID**</em> | Неверный ID родительской активации.
<em>**WRONG_ADDITIONAL_SERVICE**</em> | Неверный дополнительный сервис (допустимы только сервисы для переадресации).
<em>**WRONG_COUNTRY_ID**</em> | Некорректный ID страны.
<em>**WRONG_EXCEPTION_PHONE**</em> | Некорректные исключающие префиксы.
<em>**WRONG_OPERATOR**</em> | Некорректный оператор.
<em>**WRONG_SECURITY**</em> | Ошибка при попытке передать ID активации без переадресации, или же завершенной/не активной активации.
<em>**WRONG_SERVICE**</em> | Некорректные сервисы.
<em>**UNKNOWN**</em> | Неизвестная ошибка.

<hr/>

## *Методы*:

Метод | Описание
------------- | -------------
[**getMessage**](SMSActivateWrongParameter.md#getMessage) | Возращает ошибки статуса.
[**getEnglishMessage**](SMSActivateWrongParameter.md#getEnglishMessage) | Возращает ошибки на английском.
[**getRussianMessage**](SMSActivateWrongParameter.md#getRussianMessage) | Возращает ошибки статуса на русском.

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
