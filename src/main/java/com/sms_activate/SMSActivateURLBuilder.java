package com.sms_activate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An object keys and values in URL.
 * This class is designed for use as a drop-in replacement create url parameter string yourself.
 */
class SMSActivateURLBuilder {
  /**
   * API url.
   */
  private static final String BASE_URL = "https://sms-activate.ru/stubs/handler_api.php?";

  /**
   * Map parameter URL.
   */
  private final Map<SMSActivateURLKey, String> parameterMap;

  /**
   * Constructor QueryStringBuilder with initialize values.
   *
   * @param apiKey API key from site (not be null).
   * @param action action type (not be null).
   */
  public SMSActivateURLBuilder(@NotNull String apiKey, @NotNull SMSActivateAction action) {
    parameterMap = new HashMap<SMSActivateURLKey, String>() {{
      put(SMSActivateURLKey.API_KEY, apiKey);
      put(SMSActivateURLKey.ACTION, action.getName());
    }};
  }

  /**
   * Appends the specified pair (key, value) to parameter URL.
   *
   * @param key   key with which the specified value is to be associated (not be null).
   * @param value value to be associated with the specified key.
   */
  public SMSActivateURLBuilder append(@NotNull SMSActivateURLKey key, @Nullable String value) {
    if (value == null || value.isEmpty()) {
      return this;
    }

    if (!this.parameterMap.containsKey(key)) {
      this.parameterMap.put(key, value);
    }

    return this;
  }

  /**
   * Builds the http query string.
   *
   * @return http query string.
   */
  @NotNull
  public URL build() throws IOException {
    String urlParameters = this.parameterMap
      .entrySet()
      .stream()
      .map(entry -> String.join("=", entry.getKey().getName(), entry.getValue()))
      .collect(Collectors.joining("&"));

    return new URL(BASE_URL + urlParameters);
  }
}