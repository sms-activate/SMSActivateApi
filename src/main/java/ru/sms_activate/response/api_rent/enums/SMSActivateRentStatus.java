package ru.sms_activate.response.api_rent.enums;

import org.jetbrains.annotations.NotNull;

public enum SMSActivateRentStatus {
  SUCCESS("SUCCESS", "Все успешно.", "Successfully."),
  ERROR("ERROR", "Сообщить о том, что номер использован и отменить активацию", "Report that the number has been used and cancel activation."),
  ;

  /**
   * Type response from server
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
   * Constructor status activation.
   *
   * @param response       special id status.
   * @param russianMessage description status on russian language.
   * @param englishMessage description status on english language.
   */
  SMSActivateRentStatus(@NotNull String response, @NotNull String russianMessage, @NotNull String englishMessage) {
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
   * Returns the response from server.
   *
   * @return response from server.
   */
  @NotNull
  public String getResponse() {
    return response;
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
}
