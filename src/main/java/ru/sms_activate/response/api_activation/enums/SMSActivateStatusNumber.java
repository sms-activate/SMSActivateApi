package ru.sms_activate.response.api_activation.enums;

import org.jetbrains.annotations.NotNull;

public enum SMSActivateStatusNumber {
  WAIT_CODE("STATUS_WAIT_CODE", "Ожидание смс.", "Waiting sms."),
  CANCEL("STATUS_CANCEL", "Активация отменена.", "Activation canceled."),
  FULL_SMS("FULL_SMS", "Полный текст смс.", "Full text sms."),
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
   * Constructor status for method getStatus with multi-lang.
   *
   * @param response       response from server.
   * @param russianMessage message on russian.
   * @param englishMessage message on english.
   */
  SMSActivateStatusNumber(@NotNull String response, @NotNull String russianMessage, @NotNull String englishMessage) {
    this.response = response;
    this.russianMessage = russianMessage;
    this.englishMessage = englishMessage;
  }

  /**
   * Returns the message on russian.
   *
   * @return message on russian.
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
   * Returns the state activation by name.
   *
   * @param name state activation name.
   * @return state activation.
   */
  @NotNull
  public static SMSActivateStatusNumber getStatusByName(@NotNull String name) {
    name = name.toUpperCase();

    if (name.startsWith(FULL_SMS.getResponse())) {
      return FULL_SMS;
    }

    for (SMSActivateStatusNumber statusNumber : values()) {
      if (name.contains(statusNumber.getResponse())) {
        return statusNumber;
      }
    }

    return UNKNOWN;
  }

  @Override
  public String toString() {
    return "SMSActivateStatusNumber{" +
      "response='" + response + '\'' +
      ", russianMessage='" + russianMessage + '\'' +
      ", englishMessage='" + englishMessage + '\'' +
      '}';
  }
}
