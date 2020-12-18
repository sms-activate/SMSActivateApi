package ru.sms_activate.error.base;

import org.jetbrains.annotations.NotNull;

public enum SMSActivateBaseTypeError {
  UNKNOWN("", "Неизвестный тип ошибки.", "Unknown type error."),
  NO_BALANCE("NO_BALANCE", "Нет денег на счету.", "There is no money in the account."),
  NO_NUMBERS("NO_NUMBERS", "На данный момент номеров нет.", "There are currently no numbers."),
  CANT_CANCEL("CANT_CANCEL", "Невозможно отменить аренду (более 20 мин.).", "It is impossible to cancel the rent(more than 20 min.)."),
  ALREADY_FINISH("ALREADY_FINISH", "Аренда уже заверщена.", "Rent has already been finished."),
  ALREADY_CANCEL("ALREADY_CANCEL", "Аренда уже отменена.", "Rent has already been canceled."),
  WAIT_CODE("STATUS_WAIT_CODE", "Ожидание первой смс", "Waiting for the first SMS."),
  RENT_CANCEL("STATUS_CANCEL", "Аренда отменена с возвратом денег.", "Rent canceled with a refund."),
  RENT_FINISH("STATUS_FINISH", "Аренда оплачена и завершенна.", "Rent has been paid and finished"),
  ;

  /**
   * Description to english.
   */
  private final String englishMessage;

  /**
   * Description to russian.
   */
  private final String russianMessage;

  /**
   * Specified name status.
   */
  private final String response;

  /**
   * Constructor SMSActivateMainStatusResponse with multi-lang and specified name.
   *
   * @param response       response from server.
   * @param russianMessage message on russian.
   * @param englishMessage message on english.
   */
  SMSActivateBaseTypeError(@NotNull String response, @NotNull String russianMessage, @NotNull String englishMessage) {
    this.response = response;
    this.englishMessage = englishMessage;
    this.russianMessage = russianMessage;
  }

  /**
   * Returns the description about status to english.
   *
   * @return description about status to english.
   */
  @NotNull
  public String getEnglishMessage() {
    return englishMessage;
  }

  /**
   * Returns the description about status to russian.
   *
   * @return description about status to russian
   */
  @NotNull
  public String getRussianMessage() {
    return russianMessage;
  }

  /**
   * Returns concatenate the description about status.
   *
   * @return concatenate the description about status
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
   * Returns status by specified name.
   * if name not contains in enum then returns UNKNOWN.
   *
   * @param name specified name status.
   * @return status.
   */
  @NotNull
  public static SMSActivateBaseTypeError getErrorByName(@NotNull String name) {
    name = name.toUpperCase();

    for (SMSActivateBaseTypeError status : values()) {
      if (status.getResponse().equals(name)) {
        return status;
      }
    }

    return UNKNOWN;
  }
}
