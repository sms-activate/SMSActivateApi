package ru.sms_activate.response.api_activation.enums;

import org.jetbrains.annotations.NotNull;

/**
 * Activation status that You can receive from the sms-activate server
 */
public enum SMSActivateServerStatus {
  READY("ACCESS_READY", "Готовность номера подтверждена.", "Number readiness confirmed."),
  RETRY_GET("ACCESS_RETRY_GET", "Ожидание нового смс.", "Wait new sms."),
  FINISH("ACCESS_ACTIVATION", "Сервис успешно активирован.", "The service has been successfully activated."),
  CANCEL("ACCESS_CANCEL", "Активация была отменена.", "Activation has been canceled."),
  SMS_ADDED("SMS_ADDED", "СМС была добавлена.", "SMS has been added."),
  UNKNOWN("UNKNOWN", "Неизвестный статус активации.", "Unknown status activation.");

  /**
   * Response from server.
   */
  private final String response;

  /**
   * Message on russian language.
   */
  private final String russianMessage;

  /**
   * Message on english language.
   */
  private final String englishMessage;

  /**
   * Constructor AccessStatusActivation with multi-lang.
   *
   * @param russianMessage message on russian.
   * @param englishMessage message on english.
   * @param response       response from server.
   */
  SMSActivateServerStatus(@NotNull String response, @NotNull String russianMessage, @NotNull String englishMessage) {
    this.response = response;
    this.russianMessage = russianMessage;
    this.englishMessage = englishMessage;
  }

  /**
   * Returns the message on russian.
   *
   * @return on russian.
   */
  @NotNull
  public String getRussianMessage() {
    return russianMessage;
  }

  /**
   * Returns the message on english.
   *
   * @return message on english.
   */
  @NotNull
  public String getEnglishMessage() {
    return englishMessage;
  }

  /**
   * Returns the single concat messages.
   *
   * @return single concat messages.
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
   * Returns the state by name.
   *
   * @param name state name.
   * @return state.
   */
  @NotNull
  public static SMSActivateServerStatus getStatusByName(@NotNull String name) {
    name = name.toUpperCase();

    for (SMSActivateServerStatus accessStatusActivation : values()) {
      if (accessStatusActivation.getResponse().equals(name)) {
        return accessStatusActivation;
      }
    }

    return UNKNOWN;
  }
}
