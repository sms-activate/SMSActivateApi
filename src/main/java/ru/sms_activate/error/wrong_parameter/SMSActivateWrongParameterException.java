package ru.sms_activate.error.wrong_parameter;

import ru.sms_activate.error.base.SMSActivateBaseException;
import org.jetbrains.annotations.NotNull;

/**
 * This exception occurs if one or more parameters that were passed are invalid or do not exist.
 */
public class SMSActivateWrongParameterException extends SMSActivateBaseException {
  private SMSActivateWrongParameter wrongParameter = SMSActivateWrongParameter.UNKNOWN;

  /**
   * Constructor wrong parameter exception with multi-lang.
   *
   * @param smsActivateWrongParameter specified constant with description.
   */
  public SMSActivateWrongParameterException(@NotNull SMSActivateWrongParameter smsActivateWrongParameter) {
    this(smsActivateWrongParameter.getEnglishMessage(), smsActivateWrongParameter.getRussianMessage());
    this.wrongParameter = smsActivateWrongParameter;
  }

  /**
   * Constructor wrong parameter exception with multi-lang.
   *
   * @param englishMessage message on english language.
   * @param russianMessage message on russian language.
   */
  public SMSActivateWrongParameterException(@NotNull String englishMessage, @NotNull String russianMessage) {
    super(englishMessage, russianMessage);
  }

  /**
   * Constructor wrong parameter exception with status with other desc.
   *
   * @param smsActivateWrongParameter specified constant with description.
   * @param englishMessage            message on english language.
   * @param russianMessage            message on russian language.
   */
  private SMSActivateWrongParameterException(@NotNull SMSActivateWrongParameter smsActivateWrongParameter, @NotNull String englishMessage, @NotNull String russianMessage) {
    this(englishMessage, russianMessage);
    this.wrongParameter = smsActivateWrongParameter;
  }

  /**
   * Returns the parameter who is incorrect.
   *
   * @return parameter.
   */
  @NotNull
  public SMSActivateWrongParameter getWrongParameter() {
    return this.wrongParameter;
  }
}