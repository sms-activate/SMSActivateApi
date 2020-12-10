package ru.sms_activate;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import ru.sms_activate.error.base.SMSActivateBaseException;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

class SMSActivateJsonParser {
  /**
   * Json deserializer and serializer.
   */
  private static final Gson gson = new Gson();

  /**
   * If string is json object then parsing string to json and cast to type else throw exception.
   *
   * @param data   data in string.
   * @param typeOf type to cast after parse.
   * @param <T>    type to return.
   * @return Object from string.
   * @throws SMSActivateBaseException if response from server not equals success.
   */
  @NotNull
  public <T> T tryParseJson(@NotNull String data, @NotNull Type typeOf, @NotNull SMSActivateValidator validator) throws SMSActivateBaseException {
    try {
      return gson.fromJson(data, typeOf);
    } catch (JsonSyntaxException e) {
      throw validator.getBaseExceptionByErrorNameOrUnknown(data, "Error parsing json.");
    }
  }
}
