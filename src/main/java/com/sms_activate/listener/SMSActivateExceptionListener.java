package com.sms_activate.listener;

import org.jetbrains.annotations.NotNull;

public interface SMSActivateExceptionListener {
  /**
   * The method is called every time it receives invalid data from the server.
   *
   * @param errorFromServer error name from server.
   */
  void handle(@NotNull String errorFromServer);
}
