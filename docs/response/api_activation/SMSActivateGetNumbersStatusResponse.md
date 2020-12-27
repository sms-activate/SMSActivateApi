# SMSActivateGetNumbersStatusResponse

Метод | Описание
------------- | -------------
[**getSMSActivateServiceInfoByShortName**](SMSActivateGetNumbersStatusResponse.md#getSMSActivateServiceInfoByShortName) | Возращает информацию о сервисе по его короткому имени.
[**getSMSActivateServiceForwardInfoByShortName**](SMSActivateGetNumbersStatusResponse.md#getSMSActivateServiceForwardInfoByShortName) | Возращает информацию о сервисе, который поддерживает переадресацию, по его короткому имени.
[**getAllServiceInfoList**](SMSActivateGetNumbersStatusResponse.md#getAllServiceInfoList) | Возращает список всех сервисов.

<a name="getSMSActivateServiceInfoByShortName"></a>
## **getSMSActivateServiceInfoByShortName**

Возращает информацию о сервисе по его короткому имени.

Принимаемые параметры:

Имя | Тип | Описание
------------- | ------------- | -------------
shortName | String | Короткое имя сервиса. 

Возвращаемый тип:
[**SMSActivateServiceInfo**](./SMSACtivateServiceInfo.md)

<a name="getSMSActivateServiceForwardInfoByShortName"></a>
## **getSMSActivateServiceForwardInfoByShortName**
    
Принимаемые параметры:

Имя | Тип | Описание
------------- | ------------- | -------------
[**getSMSActivateServiceInfoByShortName**](SMSActivateGetNumbersStatusResponse.md#getSMSActivateServiceInfoByShortName) | String | Возращает информацию о сервисе по его короткому имени.

Возвращаемый тип:
[**SMSActivateServiceInfo**](./SMSACtivateServiceInfo.md)

<a name="getAllServiceInfoList"></a>
## **getAllServiceInfoList**
Возвращет информацию по всем доступным сервисам.

Принимаемые параметры:
`Отсутвуют`

Возвращаемый тип:
List<[**SMSActivateServiceInfo**](./SMSACtivateServiceInfo.md)>

