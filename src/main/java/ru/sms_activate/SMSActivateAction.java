package ru.sms_activate;

import org.jetbrains.annotations.NotNull;

enum SMSActivateAction {
  GET_BALANCE("getBalance"),
  GET_BALANCE_AND_CASHBACK("getBalanceAndCashBack"),
  GET_NUMBER("getNumber"),
  GET_NUMBERS_STATUS("getNumbersStatus"),
  GET_MULTI_SERVICE_NUMBER("getMultiServiceNumber"),
  SET_STATUS("setStatus"),
  GET_STATUS("getStatus"),
  GET_FULL_SMS("getFullSms"),
  GET_PRICES("getPrices"),
  GET_COUNTRIES("getCountries"),
  GET_QIWI_REQUISITES("getQiwiRequisites"),
  GET_ADDITIONAL_SERVICE("getAdditionalService"),
  GET_RENT_SERVICES_AND_COUNTRIES("getRentServicesAndCountries"),
  GET_RENT_NUMBER("getRentNumber"),
  GET_RENT_STATUS("getRentStatus"),
  SET_RENT_STATUS("setRentStatus"),
  GET_RENT_LIST("getRentList"),
  GET_CURRENT_ACTIVATIONS("getListOfActiveActivations")
  ;

  /**
   * The name action for send request to server.
   */
  private final String name;

  /**
   * Constructor specific action for sms activate.
   *
   * @param name name action for send request to server.
   */
  SMSActivateAction(@NotNull String name) {
    this.name = name;
  }

  /**
   * Returns the name action for send request to server.
   *
   * @return name action for send request to server.
   */
  @NotNull
  public String getName() {
    return name;
  }
}
