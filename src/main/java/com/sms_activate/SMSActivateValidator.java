package com.sms_activate;

import com.sms_activate.error.SMSActivateBannedException;
import com.sms_activate.error.SMSActivateUnknownException;
import com.sms_activate.error.base.SMSActivateBaseException;
import com.sms_activate.error.base.SMSActivateBaseTypeError;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import com.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import com.sms_activate.listener.SMSActivateExceptionListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SMSActivateValidator {
  /**
   * Success value status.
   */
  public static final String SUCCESS_STATUS = "success";

  /**
   * Banned status.
   */
  private static final String BANNED = "BANNED";

  /**
   * Status sql error.
   */
  private static final String SQL = "SQL";

  /**
   * Listener on error.
   */
  private SMSActivateExceptionListener smsActivateExceptionListener;

  /**
   * Sets the listener on error.
   *
   * @param smsActivateExceptionListener listener on error.
   */
  public void setSmsActivateExceptionListener(@NotNull SMSActivateExceptionListener smsActivateExceptionListener) {
    this.smsActivateExceptionListener = smsActivateExceptionListener;
  }

  /**
   * Throws WrongParameterException if name contains in wrong parameter.
   *
   * @param name name parameter.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   */
  private void throwWrongParameterExceptionByName(@NotNull String name) throws SMSActivateWrongParameterException {
    SMSActivateWrongParameter wrongParameter = SMSActivateWrongParameter.getWrongParameterByName(name);

    if (wrongParameter != SMSActivateWrongParameter.UNKNOWN) {
      if (smsActivateExceptionListener != null) {
        smsActivateExceptionListener.handle(name);
      }

      throw new SMSActivateWrongParameterException(wrongParameter);
    }
  }

  /**
   * Throw error by name.
   *
   * @param name name error.
   *             unknown if wrong parameter not contains in enum (it's may be SQLServerException), else throw WrongParameter.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateBaseException           if error happened on SQL-server.
   */
  public void throwCommonExceptionByName(@NotNull String name) throws SMSActivateBaseException {
    throwWrongParameterExceptionByName(name);

    if (name.toUpperCase().contains(SQL)) {
      if (smsActivateExceptionListener != null) {
        smsActivateExceptionListener.handle(name);
      }

      throw new SMSActivateBaseException("Error SQL-server.", "Ошибка SQL-сервера.");
    }
  }

  /**
   * Throws sms-activate exception by name.
   *
   * @param name name sms-activate exception.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateBannedException         if your account nas been banned.
   * @throws SMSActivateBaseException           if error happened on SQL-server.
   */
  public void throwExceptionWithBan(@NotNull String name) throws SMSActivateBaseException {
    throwCommonExceptionByName(name);

    if (name.toUpperCase().contains(BANNED)) {
      if (smsActivateExceptionListener != null) {
        smsActivateExceptionListener.handle(name);
      }

      throw new SMSActivateBannedException("Your account has been banned", "Ваш акаунт был забанен", name.split(BANNED + ":")[1]);
    }
  }

  /**
   * Returns the smsactivate base exception by name.
   *
   * @param errorName name exception.
   * @return smsactivate base exception.
   */
  @NotNull
  public SMSActivateBaseException getBaseExceptionByErrorNameOrUnknown(@NotNull String errorName, @Nullable String message) throws SMSActivateBaseException {
    throwCommonExceptionByName(errorName);
    SMSActivateBaseTypeError error = SMSActivateBaseTypeError.getErrorByName(errorName);

    if (smsActivateExceptionListener != null) {
      smsActivateExceptionListener.handle(errorName);
    }

    if (error != SMSActivateBaseTypeError.UNKNOWN) {
      return new SMSActivateBaseException(error);
    }

    return new SMSActivateUnknownException(errorName, message);
  }

  /**
   * Returns the true if data contains success status else false.
   *
   * @param data data from server.
   * @return true if data contains success status else false.
   */
  public boolean containsSuccessStatus(@NotNull String data) {
    return !data.toLowerCase().contains(SUCCESS_STATUS);
  }
}
