package ru.sms_activate.client_enums;

import org.jetbrains.annotations.NotNull;

public enum SMSActivateLanguage {
  RUSSIAN("ru"),
  ENGLISH("eng"),
  CHINESE("chinese");

  /**
   * Short name language.
   */
  private final String shortName;

  /**
   * Constructor smsactivate language supported.
   * @param shortName short name language.
   */
  SMSActivateLanguage(@NotNull String shortName) {
    this.shortName = shortName;
  }

  /**
   * Returns the name of language.
   *
   * @return name of language.
   */
  @NotNull
  public String getShortName() {
    return shortName;
  }

  @Override
  public String toString() {
    return "SMSActivateLanguage{" +
      "shortName='" + shortName + '\'' +
      '}';
  }
}
