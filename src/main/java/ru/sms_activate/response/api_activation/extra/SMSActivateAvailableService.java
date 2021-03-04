package ru.sms_activate.response.api_activation.extra;

import org.jetbrains.annotations.NotNull;

public class SMSActivateAvailableService {
  /**
   * Service short name.
   */
  private String shortName;

  /**
   * Full name service on russian.
   */
  private String fullNameRu;

  /**
   * Full name service on english.
   */
  private String fullNameEn;

  /**
   * Supported service forward.
   */
  private int forward;

  private SMSActivateAvailableService() {
  }

  /**
   * Returns the service short name.
   *
   * @return service short name.
   */
  @NotNull
  public String getShortName() {
    return shortName;
  }

  /**
   * Returns the full name service on russian.
   *
   * @return full name service on russian
   */
  @NotNull
  public String getFullNameRu() {
    return fullNameRu.replaceAll("<.+?>", "");
  }

  /**
   * Returns the service full name on english.
   *
   * @return service full name on english
   */
  @NotNull
  public String getFullNameEn() {
    return fullNameEn.replaceAll("<.+?>", "");
  }

  /**
   * Returns the service supported activation forward.
   *
   * @return supported activation forward
   */
  public boolean isForward() {
    return forward == 1;
  }

  @Override
  public String toString() {
    return "SMSActivateAvailableService{" +
      "shortName='" + shortName + '\'' +
      ", fullNameRu='" + fullNameRu + '\'' +
      ", fullNameEn='" + fullNameEn + '\'' +
      ", forward=" + forward +
      '}';
  }
}
