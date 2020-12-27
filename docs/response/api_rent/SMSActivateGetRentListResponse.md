# SMSActivateGetRentListResponse

Имя | Описание
---- | ----
[**get**](SMSActivateGetRentListResponse.md#get) | Возращает арендованный номер по идентификатору.
[**getRentSet**](SMSActivateGetRentListResponse.md#getRentSet) | Возвращает идентификаторы арендованных номеров.
[**getRentNumberList**](SMSActivateGetRentListResponse.md#getRentNumberList) | Возращает список арендованных номеров.

<hr/>

<a name="get"></a>
## **get**

Возращает арендованный номер по идентификатору.

Принимаемые параметры:

Имя | Тип | Описание 
---- | ---- | ----
rentId | int | Идентификатор аренды. 

Возвращаемый тип:
[**SMSActivateRentNumber**](extra/SMSActivateRentNumber.md)

<hr/>

<a name="getRentSet"></a>
## **getRentSet**
Возвращает идентификаторы арендованных номеров.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
`SortedSet<Integer>`

<hr/>

<a name="getRentNumberList"></a>
## **getRentNumberList**

Возращает список арендованных номеров.

Принимаемые параметры:
`отсутствуют`

Возвращаемый тип:
List<[**SMSActivateRentNumber**](extra/SMSActivateRentNumber.md)>

  