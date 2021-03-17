package ru.sms_activate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sms_activate.error.SMSActivateUnknownException;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.listener.SMSActivateWebClientListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;

class SMSActivateWebClient {
  /**
   * Counter of the number of requests.
   */
  protected final AtomicInteger COUNT_REQUEST = new AtomicInteger();

  /**
   * Listener for every request.
   */
  protected SMSActivateWebClientListener smsActivateWebClientListener;

  /**
   * Constructor with listener for every request.
   *
   * @param smsActivateWebClientListener listener for every request
   */
  public SMSActivateWebClient(@Nullable SMSActivateWebClientListener smsActivateWebClientListener) {
    this.smsActivateWebClientListener = smsActivateWebClientListener;
  }

  /**
   * Sets the listener.
   *
   * @param listener listener value.
   */
  public void setSmsActivateWebClientListener(@NotNull SMSActivateWebClientListener listener) {
    this.smsActivateWebClientListener = listener;
  }

  /**
   * Returns the request listener.
   *
   * @return request listener.
   */
  @Nullable
  public SMSActivateWebClientListener getSmsActivateWebClientListener() {
    return smsActivateWebClientListener;
  }

  /**
   * Get data or throw common exception if error is happened.
   *
   * @param smsActivateURLBuilder url builder.
   * @param validator             data validator.
   * @return load data from url.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateBaseException           if error happened by base type.
   * @throws SMSActivateUnknownException        if error not documented.
   */
  @NotNull
  public String getOrThrowCommonException(@NotNull SMSActivateURLBuilder smsActivateURLBuilder, @NotNull SMSActivateValidator validator)
    throws SMSActivateBaseException {
    try {
      int cid = COUNT_REQUEST.incrementAndGet();

      HttpURLConnection urlConnection = (HttpURLConnection) smsActivateURLBuilder.build().openConnection();
      urlConnection.setRequestMethod("GET");
      urlConnection.setRequestProperty("Accept-Encoding", "gzip, json");

      int statusCode = urlConnection.getResponseCode();

      String data = load(urlConnection);

      if (smsActivateWebClientListener != null) {
        smsActivateWebClientListener.handle(cid, urlConnection.getURL().toString(), statusCode, data);
      }

      validator.throwCommonExceptionByName(data);

      return data;
    } catch (IOException e) {
      throw new SMSActivateUnknownException("Problems with network connection.", e.getMessage());
    }
  }

  /**
   * Returns the data from connection.
   *
   * @param urlConnection connection on server.
   * @return data from server.
   * @throws IOException if an I/O exception occurs.
   */
  @NotNull
  protected String load(@NotNull HttpURLConnection urlConnection) throws IOException {
    InputStreamReader inputStreamReader;

    if (urlConnection.getErrorStream() == null) {
      if (urlConnection.getContentEncoding() != null && urlConnection.getContentEncoding().contains("gzip")) {
        inputStreamReader = new InputStreamReader(new GZIPInputStream(urlConnection.getInputStream()));
      } else {
        inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
      }
    } else {
      inputStreamReader = new InputStreamReader(new GZIPInputStream(urlConnection.getErrorStream()));
    }

    try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
      String data;
      StringBuilder builder = new StringBuilder();

      while ((data = reader.readLine()) != null) {
        builder.append(data);
      }

      return builder.toString();
    }
  }
}
