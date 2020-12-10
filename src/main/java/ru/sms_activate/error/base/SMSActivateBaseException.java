package ru.sms_activate.error.base;

import org.jetbrains.annotations.NotNull;

public class SMSActivateBaseException extends Exception {
  /**
   * Error type by server response.
   */
  private SMSActivateBaseTypeError type = SMSActivateBaseTypeError.UNKNOWN;

  /**
   * Message on english language.
   */
  private final String englishMessage;

  /**
   * Message on russian language.
   */
  private final String russianMessage;

  /**
   * Constructor base sms activate exception with multi-lang.
   *
   * @param englishMessage message on english language.
   * @param russianMessage message on russian language.
   */
  public SMSActivateBaseException(@NotNull String englishMessage, @NotNull String russianMessage) {
    super(englishMessage);

    this.englishMessage = englishMessage;
    this.russianMessage = russianMessage;
  }

  public SMSActivateBaseException(@NotNull SMSActivateBaseTypeError error) {
    this(error.getEnglishMessage(), error.getRussianMessage());
    this.type = error;
  }

  /**
   * Returns the message on english language.
   *
   * @return message on english language.
   */
  @NotNull
  public String getEnglishMessage() {
    return englishMessage;
  }

  /**
   * Returns the message on russian language.
   *
   * @return message on russian language.
   */
  @NotNull
  public String getRussianMessage() {
    return russianMessage;
  }

  /**
   * Returns the type error response from server.
   *
   * @return type error response from server.
   */
  @NotNull
  public SMSActivateBaseTypeError getTypeError() {
    return type;
  }

  /**
   * Returns the concatenation messages.
   *
   * @return concatenation messages.
   */
  @Override
  @NotNull
  public String getMessage() {
    return String.join(" | ", englishMessage, russianMessage);
  }
}
