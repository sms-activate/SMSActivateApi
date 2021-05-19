package ru.sms_activate.response.api_activation.extra;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SMSActivateCurrentActivation {
  /**
   * Activation id.
   */
  private int id;

  /**
   * Activation is forward.
   */
  private int forward;

  /**
   * Activation phone number.
   */
  @SerializedName(value = "phone", alternate = {
    "phoneNumber", "number"
  })
  private long phoneNumber;

  /**
   * Create time of activation.
   */
  @SerializedName("createDate")
  private long createTime;

  /**
   * Service for activation.
   */
  private String service;

  /**
   * Country activation.
   */
  private int country;

  /**
   * Finish time of activation.
   */
  @SerializedName("finishDate")
  private long finishTime;

  /**
   * Forward phone number for activation.
   */
  @SerializedName(value = "forwardNumber", alternate = {
    "forward"
  })
  private Long forwardPhone;

  private SMSActivateCurrentActivation() {
  }

  /**
   * Returns the service activation.
   *
   * @return service activation
   */
  @NotNull
  public String getService() {
    return service;
  }

  /**
   * Returns the country activation.
   *
   * @return country activation.
   */
  public int getCountry() {
    return country;
  }

  /**
   * Returns the activation id.
   *
   * @return activation id
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the forward activation.
   *
   * @return forward activation
   */
  public boolean isForward() {
    return forward == 1;
  }

  /**
   * Returns the activation phone number.
   *
   * @return activation phone number
   */
  public long getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Returns the create time of activation.
   *
   * @return create time of activation
   */
  public long getCreateTime() {
    return createTime;
  }

  /**
   * Returns the finish time of activation.
   *
   * @return finish time of activation.
   */
  public long getFinishTime() {
    return finishTime;
  }

  /**
   * Returns the forward phone number for activation.
   *
   * @return forward phone number for activation.
   */
  @Nullable
  private Long getForwardPhone() {
    return forwardPhone == 0 ? null : forwardPhone;
  }

  @Override
  public String toString() {
    return "SMSActivateCurrentActivation{" +
      "id=" + id +
      ", forward=" + forward +
      ", phoneNumber=" + phoneNumber +
      ", createTime=" + createTime +
      ", service='" + service + '\'' +
      ", country=" + country +
      ", finishTime=" + finishTime +
      ", forwardPhone=" + forwardPhone +
      '}';
  }
}