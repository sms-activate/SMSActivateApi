package ru.sms_activate.listener;

import org.jetbrains.annotations.NotNull;

public interface SMSActivateWebClientListener {
  /**
   * The method is called every time a request is made to the server.
   *
   * @param cid        number of request.
   * @param request    full request to the server.
   * @param statusCode response code.
   * @param response   response from server.
   */
  void handle(int cid, @NotNull String request, int statusCode, @NotNull String response);
}
