package ru.sms_activate.response.api_activation.extra;

import org.jetbrains.annotations.NotNull;

public class SMSActivateCountryInfo {
  /**
   * Country id.
   */
  private int id;

  /**
   * Country name on chinese.
   */
  private String chn;

  /**
   * Country name on english.
   */
  private String eng;

  /**
   * Country name on russian.
   */
  private String rus;

  /**
   * Country support rent.
   */
  private int rent;

  /**
   * Country support retry sms.
   */
  private int retry;

  /**
   * Country visible in site.
   */
  private int visible;

  /**
   * Country support multi service.
   */
  private int multiService;

  private SMSActivateCountryInfo() {
  }

  /**
   * Returns the country id.
   *
   * @return country id.
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the name on chinese.
   *
   * @return name on chinese.
   */
  @NotNull
  public String getChineseName() {
    return chn;
  }

  /**
   * Returns the name on english.
   *
   * @return name on english.
   */
  @NotNull
  public String getEnglishName() {
    return eng;
  }

  /**
   * Returns the name on russian.
   *
   * @return name on russian.
   */
  @NotNull
  public String getRussianName() {
    return rus;
  }

  /**
   * Returns the support rent.
   *
   * @return support rent.
   */
  public boolean isSupportRent() {
    return rent == 1;
  }

  /**
   * Returns the support retry sms.
   *
   * @return support retry sms.
   */
  public boolean isSupportRetry() {
    return retry == 1;
  }

  /**
   * Returns the visible in site.
   *
   * @return visible in site.
   */
  public boolean isVisible() {
    return visible == 1;
  }

  /**
   * Returns the support multi service.
   *
   * @return support multi service.
   */
  public boolean isSupportMultiService() {
    return multiService == 1;
  }

  @Override
  public String toString() {
    return "SMSActivateCountryInfo{" +
      "id=" + id +
      ", chn='" + chn + '\'' +
      ", eng='" + eng + '\'' +
      ", rus='" + rus + '\'' +
      ", rent=" + rent +
      ", retry=" + retry +
      ", visible=" + visible +
      ", multiService=" + multiService +
      '}';
  }
}
