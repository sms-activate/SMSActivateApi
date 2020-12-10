package com.sms_activate.client_enums;

import org.jetbrains.annotations.NotNull;

public enum SMSActivateClientRentStatus {
  FINISH(1, "Завершить активацию.", "Finish the activation."),
  CANCEL(2, "Сообщить о том, что номер использован и отменить активацию", "Report that the number has been used and cancel activation."),
  ;

  /**
   * Special id status
   */
  private final int id;

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
   * @param id             special id status.
   * @param russianMessage description status on russian language.
   * @param englishMessage description status on english language.
   */
  SMSActivateClientRentStatus(int id, @NotNull String russianMessage, @NotNull String englishMessage) {
    this.id = id;
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
   * Return the id status
   *
   * @return id status
   */
  public int getId() {
    return id;
  }
}
