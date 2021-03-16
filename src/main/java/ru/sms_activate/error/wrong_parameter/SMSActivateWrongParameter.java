package ru.sms_activate.error.wrong_parameter;

import org.jetbrains.annotations.NotNull;

public enum SMSActivateWrongParameter {
  BAD_ACTION("BAD_ACTION", "Некорректное действие.", "Wrong action."),
  BAD_SERVICE("BAD_SERVICE", "Некорректное наименование сервиса.", "Wrong name service."),
  BAD_KEY("BAD_KEY", "Неверный API-ключ.", "Wrong api-key."),
  BAD_STATUS("BAD_STATUS", "Попытка установить несуществующий статус.", "An attempt to setup a non-existent status."),
  EMPTY_KEY("EMPTY_KEY", "API ключ не может быть пустым.", "API-key can't be empty.", false),
  INCORRECT_STATUS("INCORECT_STATUS", "Отсутствует или неправильно указан статус.", "Missing or incorrect status."),
  INVALID_PHONE("INVALID_PHONE", "Номер арендован не вами (неправильный id аренды).", "Number was not rented by you (incorrect id rent)."),
  NOT_AVAILABLE("NOT_AVAILABLE ", "Для страны, которую вы используете, недоступна покупка мультисервисов.", "Country does not supported multi-service."),
  NO_ID_RENT("NO_ID_RENT", "Не указан ID аренды.", "No rent Id."),
  REPEAT_ADDITIONAL_SERVICE("REPEAT_ADDITIONAL_SERVICE", "Ошибка возникает при попытке заказать купленный сервис еще раз.", "The error occurs when you try to order the purchased service again."),
  WRONG_ACTIVATION_ID("WRONG_ACTIVATION_ID", "Неверный ID родительской активации.", "Wrong ID parent activation."),
  WRONG_ADDITIONAL_SERVICE("WRONG_ADDITIONAL_SERVICE", "Неверный дополнительный сервис (допустимы только сервисы для переадресации).", "Invalid additional service (only services for forwarding are allowed)."),
  WRONG_COUNTRY_ID("WRONG_COUNTRY_ID", "Некорректный ID страны.", "Wrong country ID.", false),
  WRONG_EXCEPTION_PHONE("WRONG_EXCEPTION_PHONE", "Некорректные исключающие префиксы.", "Wrong exception prefix."),
  WRONG_OPERATOR("WRONG_OPERATOR", "Некорректный оператор.", "Wrong operator."),
  WRONG_SECURITY("WRONG_SECURITY", "Ошибка при попытке передать ID активации без переадресации, или же завершенной/не активной активации.", "An error occurred when trying to transfer an activation ID without forwarding, or a completed / inactive activation."),
  WRONG_SERVICE("WRONG_SERVICE", "Некорректные сервисы.", "Wrong services."),
  BAD_OPERATOR("BAD_OPERATOR", WRONG_OPERATOR.getRussianMessage(), WRONG_OPERATOR.getEnglishMessage()),
  BAD_COUNTRY("BAD_COUNTRY", WRONG_COUNTRY_ID.getRussianMessage(), WRONG_COUNTRY_ID.getEnglishMessage()),
  UNKNOWN("UNKNOWN", "Неизвестная ошибка.", "Unknown error."),
  ;

  /**
   * Response from server.
   */
  private final String response;

  /**
   * Description error on russian language.
   */
  private final String russianMessage;

  /**
   * Description error on english language.
   */
  private final String englishMessage;

  /**
   * Mark for response from server.
   */
  private final boolean fromServer;

  /**
   * Constructor WrongParameter with multi-lang.
   *
   * @param response       response from server.
   * @param russianMessage error Description on russian.
   * @param englishMessage error Description on english.
   */
  SMSActivateWrongParameter(@NotNull String response, @NotNull String russianMessage, @NotNull String englishMessage) {
    this(response, russianMessage, englishMessage, true);
  }

  /**
   * Constructor WrongParameter with multi-lang.
   *
   * @param response       response from server.
   * @param russianMessage error Description on russian.
   * @param englishMessage error Description on english.
   * @param fromServer     mark for response from server.
   */
  SMSActivateWrongParameter(@NotNull String response, @NotNull String russianMessage, @NotNull String englishMessage, boolean fromServer) {
    this.response = response;
    this.russianMessage = russianMessage;
    this.englishMessage = englishMessage;
    this.fromServer = fromServer;
  }

  /**
   * Returns the true if status from server else false.
   *
   * @return true if status from server else false.
   */
  public boolean isFromServer() {
    return fromServer;
  }

  /**
   * Returns the error description on russian.
   *
   * @return error description on russian.
   */
  @NotNull
  public String getRussianMessage() {
    return russianMessage;
  }

  /**
   * Returns the error description on english.
   *
   * @return error description on english.
   */
  @NotNull
  public String getEnglishMessage() {
    return englishMessage;
  }

  /**
   * Returns the single concatenation description.
   *
   * @return single concatenation description.
   */
  @NotNull
  public String getMessage() {
    return String.join(" | ", englishMessage, russianMessage);
  }

  /**
   * Returns the response from server.
   *
   * @return response from server.
   */
  @NotNull
  public String getResponse() {
    return response;
  }

  /**
   * Returns the wrongParameter by name.
   *
   * @param name name wrong parameter.
   * @return wrongParameter if contains, else unknown.
   */
  @NotNull
  public static SMSActivateWrongParameter getWrongParameterByName(@NotNull String name) {
    name = name.toUpperCase();

    for (SMSActivateWrongParameter smsActivateWrongParameter : values()) {
      if (smsActivateWrongParameter.getResponse().equals(name)) {
        return smsActivateWrongParameter;
      }
    }

    return UNKNOWN;
  }

  @Override
  public String toString() {
    return "SMSActivateWrongParameter{" +
      "response='" + response + '\'' +
      ", russianMessage='" + russianMessage + '\'' +
      ", englishMessage='" + englishMessage + '\'' +
      ", fromServer=" + fromServer +
      '}';
  }
}
