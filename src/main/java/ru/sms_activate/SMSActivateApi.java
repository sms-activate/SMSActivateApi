package ru.sms_activate;

import com.google.gson.reflect.TypeToken;
import ru.sms_activate.client_enums.SMSActivateClientRentStatus;
import ru.sms_activate.client_enums.SMSActivateClientStatus;
import ru.sms_activate.error.SMSActivateBannedException;
import ru.sms_activate.error.SMSActivateUnknownException;
import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.error.base.SMSActivateBaseTypeError;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameter;
import ru.sms_activate.error.wrong_parameter.SMSActivateWrongParameterException;
import ru.sms_activate.listener.SMSActivateExceptionListener;
import ru.sms_activate.listener.SMSActivateWebClientListener;
import ru.sms_activate.response.api_activation.*;
import ru.sms_activate.response.api_activation.enums.SMSActivateGetStatusActivation;
import ru.sms_activate.response.api_activation.enums.SMSActivateServerStatus;
import ru.sms_activate.response.api_activation.enums.SMSActivateStatusNumber;
import ru.sms_activate.response.api_activation.extra.SMSActivateCountryInfo;
import ru.sms_activate.response.api_activation.extra.SMSActivateGetPriceInfo;
import ru.sms_activate.response.api_activation.extra.SMSActivateServiceInfo;
import ru.sms_activate.response.api_rent.SMSActivateGetRentListResponse;
import ru.sms_activate.response.api_rent.SMSActivateGetRentServicesAndCountriesResponse;
import ru.sms_activate.response.api_rent.SMSActivateGetRentStatusResponse;
import ru.sms_activate.response.api_rent.enums.SMSActivateRentStatus;
import ru.sms_activate.response.api_rent.extra.SMSActivateRentActivation;
import ru.sms_activate.response.api_rent.extra.SMSActivateSMS;
import ru.sms_activate.response.qiwi.SMSActivateGetQiwiRequisitesResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>The class is a high-level API for interacting with the SMS-Activate API.
 * API capabilities allow you to work with the service through your software.
 * Before starting work, you must have an API key and a referral link to use all the API capabilities.
 * Use the methods that are implemented in this class.</p>
 *
 * <p>All methods and constructor in SMSActivateApi throw SMSActivateWrongParameterException</p>
 * <p>Before request set referral identifier</p>
 *
 * @see SMSActivateWrongParameterException
 * @see SMSActivateBaseException
 * @see SMSActivateWrongParameter
 * @see SMSActivateBaseTypeError
 */
public class SMSActivateApi {
  /**
   * The minimal rent time.
   */
  public static final int MINIMAL_RENT_TIME = 4;

  /**
   * Regular expression for numbers.
   */
  private static final Pattern patternDigit = Pattern.compile("\\d+(?:[\\.,]\\d+)?");

  /**
   * Special validator for server responses.
   */
  private final SMSActivateValidator validator = new SMSActivateValidator();

  /**
   * Api key from site.
   */
  private final String apiKey;

  /**
   * Referral identifier.
   */
  private String ref = null;

  /**
   * Listener on data from server.
   */
  private SMSActivateWebClientListener smsActivateWebClientListener;

  /**
   * Constructor API sms-activate with API key.
   *
   * @param apiKey API key (not be null).
   * @throws SMSActivateWrongParameterException if api-key is incorrect.
   */
  public SMSActivateApi(@NotNull String apiKey) throws SMSActivateWrongParameterException {
    if (apiKey.isEmpty()) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.EMPTY_KEY);
    }

    this.apiKey = apiKey;
  }

  /**
   * Sets the listener on request to server.
   *
   * @param smsActivateWebClientListener listener on request to server.
   */
  public void setSmsActivateWebClientListener(@NotNull SMSActivateWebClientListener smsActivateWebClientListener) {
    this.smsActivateWebClientListener = smsActivateWebClientListener;
  }

  /**
   * Sets the listener on error.
   *
   * @param smsActivateExceptionListener listener on error.
   */
  public void setSmsActivateExceptionListener(@NotNull SMSActivateExceptionListener smsActivateExceptionListener) {
    this.validator.setSmsActivateExceptionListener(smsActivateExceptionListener);
  }

  /**
   * Returns the API key.
   *
   * @return apiKey API key (not be null).
   */
  @NotNull
  public String getApiKey() {
    return apiKey;
  }

  /**
   * If you are a partner of sms-activate set the referral identifier. For example ref=softgorregtelegram.
   * The referral identifier are given by support.
   *
   * @param ref referral identifier.
   */
  public void setRef(@NotNull String ref) {
    this.ref = ref;
  }

  /**
   * Returns the referral link.
   *
   * @return referral link.
   */
  @Nullable
  public String getRef() {
    return ref;
  }

  /**
   * Returns the current account balance in rubles.
   *
   * @return current account balance in rubles.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                              </ul>
   */
  @NotNull
  public BigDecimal getBalance() throws SMSActivateBaseException {
    return getBalanceByAction(SMSActivateAction.GET_BALANCE);
  }

  /**
   * Returns the current account balance and cashBack in rubles.
   *
   * @return current account balance and cashBack in rubles.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetBalanceAndCashBackResponse getBalanceAndCashBack() throws SMSActivateBaseException {
    BigDecimal balance = getBalance();
    BigDecimal balanceAndCashBack = getBalanceByAction(SMSActivateAction.GET_BALANCE_AND_CASHBACK);

    return new SMSActivateGetBalanceAndCashBackResponse(balance, balanceAndCashBack.subtract(balance));
  }

  /**
   * Returns the available services from setting site.
   *
   * @return the available services.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetNumbersStatusResponse getNumbersStatusByDefaultSettingFromSite() throws SMSActivateBaseException {
    return getNumbersStatus(null, null);
  }

  /**
   * Returns the available services by country and operator.
   *
   * @param countryId   id country.
   * @param operatorSet set names operators mobile network.
   * @return available services by county and operator (not be null).
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect;</li>
   *                                                <li>WRONG_OPERATOR - if operator or countryId is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetNumbersStatusResponse getNumbersStatus(@Nullable Integer countryId, @Nullable Set<String> operatorSet)
    throws SMSActivateBaseException {
    if (countryId != null && countryId < 0) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
    }

    String operator = null;

    if (operatorSet != null) {
      operatorSet.removeIf(String::isEmpty);

      if (!operatorSet.isEmpty()) {
        operator = String.join(",", operatorSet);
      }
    }

    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_NUMBERS_STATUS);
    smsActivateURLBuilder.append(SMSActivateURLKey.OPERATOR, operator);

    if (countryId != null) {
      smsActivateURLBuilder.append(SMSActivateURLKey.COUNTRY, String.valueOf(countryId));
    }

    String serviceJsonResponse = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    Map<String, Integer> serviceMap = jsonParser.tryParseJson(serviceJsonResponse, new TypeToken<Map<String, Integer>>() {
    }.getType(), validator);
    Map<String, SMSActivateServiceInfo> serviceInfoMap = new HashMap<>();

    for (Map.Entry<String, Integer> entry : serviceMap.entrySet()) {
      String serviceName = entry.getKey();
      String[] parts = serviceName.split("_");

      serviceInfoMap.put(serviceName, new SMSActivateServiceInfo(
        parts[0],
        entry.getValue(),
        parts[1].equals("1")
      ));
    }

    return new SMSActivateGetNumbersStatusResponse(serviceInfoMap);
  }

  /**
   * Returns the activation by service, countryId.
   *
   * @param countryId id country.
   * @param service   service name for activation.
   * @return activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                              <ul>
   *                                                <li>NO_NUMBERS - if currently no numbers;</li>
   *                                                <li>NO_BALANCE - if your balance is less than the price;</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateActivation getNumber(int countryId, @NotNull String service) throws SMSActivateBaseException {
    return getNumber(countryId, service, false);
  }

  /**
   * Returns the activation by service, countryId.
   *
   * @param countryId id country.
   * @param service   service name for activation.
   * @param forward true if need number phone with redirection else false.
   * @return activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                              <ul>
   *                                                <li>NO_NUMBERS - if currently no numbers;</li>
   *                                                <li>NO_BALANCE - if your balance is less than the price;</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateActivation getNumber(int countryId, @NotNull String service, boolean forward) throws SMSActivateBaseException {
    return getNumber(countryId, service, forward, null, null);
  }

  /**
   * Returns the activation by service, countryId, phoneException, operator, forward
   *
   * @param countryId         id country.
   * @param service           service name for activation.
   * @param operatorSet       set mobile operators if operatorSet is null then .
   * @param phoneExceptionSet set excepted id numbers prefix if phoneExceptionSet is null then.
   * @param forward           is it necessary to request a number with forwarding.
   * @return activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                               <ul>
   *                                                 <li>NO_NUMBERS - if currently no numbers;</li>
   *                                                <li>NO_BALANCE - if your balance is less than the price;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_OPERATOR - if operator or countryId is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_PHONE_EXCEPTION - if one or more numbers prefix is incorrect.</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateActivation getNumber(
    int countryId,
    @NotNull String service,
    boolean forward,
    @Nullable Set<String> operatorSet,
    @Nullable Set<String> phoneExceptionSet
  ) throws SMSActivateBaseException {

    if (countryId < 0) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
    }

    String phoneException = null;
    String operator = null;

    if (phoneExceptionSet != null) {
      phoneExceptionSet.removeIf(String::isEmpty);

      if (!phoneExceptionSet.isEmpty()) {
        phoneException = String.join(",", phoneExceptionSet);
      }
    }

    if (operatorSet != null) {
      operatorSet.removeIf(String::isEmpty);

      if (!operatorSet.isEmpty()) {
        operator = String.join(",", operatorSet);
      }
    }

    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_NUMBER);
    smsActivateURLBuilder.append(SMSActivateURLKey.REF, ref)
      .append(SMSActivateURLKey.COUNTRY, String.valueOf(countryId))
      .append(SMSActivateURLKey.SERVICE, service)
      .append(SMSActivateURLKey.FORWARD, forward ? "1" : "0")
      .append(SMSActivateURLKey.OPERATOR, operator)
      .append(SMSActivateURLKey.PHONE_EXCEPTION, phoneException);

    String responseFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    validator.throwExceptionWithBan(responseFromServer);

    if (!responseFromServer.startsWith(SMSActivateMagicConstant.ACCESS)) {
      throw validator.getBaseExceptionByErrorNameOrUnknown(responseFromServer, null);
    }

    try {
      String[] parts = responseFromServer.split(":");
      int id = Integer.parseInt(parts[1]);
      long number = Long.parseLong(parts[2]);

      return new SMSActivateActivation(id, number, service);
    } catch (NumberFormatException e) {
      throw validator.getBaseExceptionByErrorNameOrUnknown(responseFromServer, "Error formatting to number.");
    }
  }

  /**
   * Returns the specified object id by countryId, multiService.<br>
   * Separator for multiService is commas.
   *
   * @param countryId       id country.
   * @param multiServiceSet services for ordering (not be null).
   * @return SMSActivateGetMultiServiceNumberResponse object.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_OPERATOR - if operator or countryId is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect;</li>
   *                                                <li>NOT_AVAILABLE  - if country not supported multi-service.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetMultiServiceNumberResponse getMultiServiceNumber(int countryId, @NotNull Set<String> multiServiceSet)
    throws SMSActivateBaseException {
    return getMultiServiceNumber(countryId, multiServiceSet, null, null);
  }

  /**
   * Returns the specified object with activations id by countryId, multiService.<br>
   * Separator for multiService, multiForward and operator is commas.
   *
   * @param countryId  id country.
   * @param serviceMap map service where key is service name, value is forward.
   * @return specified object with activations.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_OPERATOR - if operator or countryId is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect;</li>
   *                                                <li>NOT_AVAILABLE  - if country not supported multi-service.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetMultiServiceNumberResponse getMultiServiceNumber(int countryId, @NotNull Map<String, Boolean> serviceMap) throws SMSActivateBaseException {
    return getMultiServiceNumber(countryId, serviceMap, null);
  }

  /**
   * Returns the specified object with activations id by countryId, multiService.<br>
   * Separator for multiService, multiForward and operator is commas.
   *
   * @param countryId   id country.
   * @param serviceMap  map service where key is service name, value is forward.
   * @param operatorSet mobile operator.
   * @return specified object with activations.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_OPERATOR - if operator or countryId is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect;</li>
   *                                                <li>NOT_AVAILABLE  - if country not supported multi-service.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetMultiServiceNumberResponse getMultiServiceNumber(int countryId, @NotNull Map<String, Boolean> serviceMap, @Nullable Set<String> operatorSet) throws SMSActivateBaseException {
    return getMultiServiceNumber(countryId, serviceMap.keySet(), operatorSet, new ArrayList<>(serviceMap.values()));
  }

  /**
   * Returns the specified object with activations id by countryId, multiService.<br>
   * Separator for multiService, multiForward and operator is commas.
   *
   * @param countryId        id country.
   * @param multiServiceSet  services for ordering (not be null).
   * @param operatorSet      mobile operator.
   * @param multiForwardList is it necessary to request a number with forwarding.
   * @return specified object with activations.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   * @throws SMSActivateBannedException         if your account has been banned.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_OPERATOR - if operator or countryId is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect;</li>
   *                                                <li>NOT_AVAILABLE  - if country not supported multi-service.</li>
   *                                              </ul>
   */
  @NotNull
  private SMSActivateGetMultiServiceNumberResponse getMultiServiceNumber(
    int countryId,
    @NotNull Set<String> multiServiceSet,
    @Nullable Set<String> operatorSet,
    @Nullable List<Boolean> multiForwardList
  ) throws SMSActivateBaseException {
    if (countryId < 0) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
    }

    multiServiceSet.removeIf(String::isEmpty);

    String strMultiService = String.join(",", multiServiceSet);
    String strOperators = null;
    String strMultiForward = null;

    if (multiForwardList != null) {
      strMultiForward = multiForwardList.stream()
        .filter(forward -> !Objects.isNull(forward))
        .map(forward -> forward ? "1" : "0")
        .collect(Collectors.joining(","));
    }

    if (operatorSet != null) {
      operatorSet.removeIf(String::isEmpty);
      strOperators = String.join(",", operatorSet);
    }

    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_MULTI_SERVICE_NUMBER);
    smsActivateURLBuilder.append(SMSActivateURLKey.REF, String.valueOf(ref))
      .append(SMSActivateURLKey.MULTI_SERVICE, strMultiService)
      .append(SMSActivateURLKey.COUNTRY, String.valueOf(countryId))
      .append(SMSActivateURLKey.MULTI_FORWARD, strMultiForward)
      .append(SMSActivateURLKey.OPERATOR, strOperators);

    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    validator.throwExceptionWithBan(jsonFromServer);
    List<SMSActivateActivation> smsActivateActivationList = jsonParser.tryParseJson(jsonFromServer, new TypeToken<List<SMSActivateActivation>>() {
    }.getType(), validator);

    return new SMSActivateGetMultiServiceNumberResponse(smsActivateActivationList);
  }

  /**
   * Sets the status activation.
   * <p>Get number using getNumber method after that the following actions are available:<br>
   * <em>CANCEL</em> - Cancel activation (if the number did not suit you)<br>
   * <em>MESSAGE_WAS_SENT</em> - Report that SMS has been sent (optional)</p>
   *
   * <p>To activate with status READY:<br>
   * <em>CANCEL</em> - Cancel activation</p>
   *
   * <p>Immediately after receiving the code:<br>
   * <em>REQUEST_ONE_MORE_CODE</em> - Request another SMS<br>
   * <em>FINISH</em> - Confirm SMS code and complete activation</p>
   *
   * <p>To activate with status RETRY_GET:<br>
   * <em>FINISH</em> - Confirm SMS code and complete activation</p>
   *
   * @param activationId id to set activation status (not be null).
   * @param status       value to establish (not be null).
   * @return access activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                                <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>BAD_STATUS - if service is incorrect;</li>
   *                                                 <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                                </ul>
   */
  @NotNull
  public SMSActivateSetStatusResponse setStatus(int activationId, @NotNull SMSActivateClientStatus status)
    throws SMSActivateBaseException {
    return setStatusWithForwardPhone(activationId, status, null);
  }

  /**
   * Sets the status activation.
   * <p>Get number using getNumber method after that the following actions are available:<br>
   * <em>CANCEL</em> - Cancel activation (if the number did not suit you)<br>
   * <em>MESSAGE_WAS_SENT</em> - Report that SMS has been sent (optional)</p>
   *
   * <p>To activate with status READY:<br>
   * <em>CANCEL</em> - Cancel activation</p>
   *
   * <p>Immediately after receiving the code:<br>
   * <em>REQUEST_ONE_MORE_CODE</em> - Request another SMS<br>
   * <em>FINISH</em> - Confirm SMS code and complete activation</p>
   *
   * <p>To activate with status RETRY_GET:<br>
   * <em>FINISH</em> - Confirm SMS code and complete activation</p>
   *
   * @param activation activation to set activation status (not be null).
   * @param status     value to establish (not be null).
   * @return access activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                                <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>BAD_STATUS - if service is incorrect;</li>
   *                                                 <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                                </ul>
   */
  @NotNull
  public SMSActivateSetStatusResponse setStatus(@NotNull SMSActivateActivation activation, @NotNull SMSActivateClientStatus status)
    throws SMSActivateBaseException {
    return setStatus(activation.getId(), status);
  }

  /**
   * Sets the status activation.
   * <p>Get number using getNumber method after that the following actions are available:<br>
   * <em>CANCEL</em> - Cancel activation (if the number did not suit you)<br>
   * <em>SEND_READY_NUMBER</em> - Report that SMS has been sent (optional)</p>
   *
   * <p>To activate with status READY:<br>
   * <em>CANCEL</em> - Cancel activation</p>
   *
   * <p>Immediately after receiving the code:<br>
   * <em>REQUEST_ONE_MORE_CODE</em> - Request another SMS<br>
   * <em>FINISH</em> - Confirm SMS code and complete activation</p>
   *
   * <p>To activate with status RETRY_GET:<br>
   * <em>FINISH</em> - Confirm SMS code and complete activation</p>
   *
   * @param activationId id to set activation status (not be null).
   * @param status       value to establish (not be null).
   * @param forwardPhone number phone for forward.
   * @return access activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                                <ul>
   *                                                  <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                  <li>BAD_STATUS - if service is incorrect;</li>
   *                                                  <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                                </ul>
   */
  @NotNull
  public SMSActivateSetStatusResponse setStatusWithForwardPhone(
    int activationId,
    @NotNull SMSActivateClientStatus status,
    @Nullable Long forwardPhone
  ) throws SMSActivateBaseException {
    if (forwardPhone != null && forwardPhone <= 0) {
      throw new SMSActivateWrongParameterException(
        "Phone number for forwarding must be positive.",
        "Телефон для переадресации должен быть положительный."
      );
    }

    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.SET_STATUS);
    smsActivateURLBuilder.append(SMSActivateURLKey.STATUS, String.valueOf(status.getId()))
      .append(SMSActivateURLKey.ID, String.valueOf(activationId));

    if (forwardPhone != null) {
      smsActivateURLBuilder.append(SMSActivateURLKey.FORWARD, String.valueOf(forwardPhone));
    }

    String statusFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);

    SMSActivateServerStatus smsActivateServerStatus = SMSActivateServerStatus.getStatusByName(statusFromServer);

    if (smsActivateServerStatus != SMSActivateServerStatus.UNKNOWN) {
      return new SMSActivateSetStatusResponse(smsActivateServerStatus);
    }

    throw validator.getBaseExceptionByErrorNameOrUnknown(statusFromServer, null);
  }

  /**
   * Returns the state by id activation.
   *
   * @param activationId id activation to get activation state.
   * @return state activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetStatusResponse getStatus(int activationId) throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_STATUS);
    smsActivateURLBuilder.append(SMSActivateURLKey.ID, String.valueOf(activationId));

    String code = null;
    String statusFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);

    if (statusFromServer.contains(":")) {
      String[] parts = statusFromServer.split(":");

      statusFromServer = parts[0];
      code = parts[1];
    }

    SMSActivateGetStatusActivation status = SMSActivateGetStatusActivation.getStatusByName(statusFromServer);

    if (status != SMSActivateGetStatusActivation.UNKNOWN) {
      return new SMSActivateGetStatusResponse(status, code);
    }

    throw validator.getBaseExceptionByErrorNameOrUnknown(statusFromServer, null);
  }

  /**
   * Returns the state by activation.
   *
   * @param activation activation to get state.
   * @return state activation.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetStatusResponse getStatus(@NotNull SMSActivateActivation activation) throws SMSActivateBaseException {
    return getStatus(activation.getId());
  }

  /**
   * Returns the specified object from server with text sms.
   *
   * @param activationId id activation.
   * @return full text sms with status:
   * <ul>
   *    <li>if SMS arrived, then the answer will be with the FULL_SMS status</li>
   *    <li>if expected then WAIT_CODE</li>
   *    <li>if canceled then CANCEL</li>
   * </ul>
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetFullSmsResponse getFullSms(int activationId) throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_FULL_SMS);
    smsActivateURLBuilder.append(SMSActivateURLKey.ID, String.valueOf(activationId));

    String smsFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);

    SMSActivateStatusNumber smsActivateStatusNumber = SMSActivateStatusNumber.getStatusByName(smsFromServer);

    if (smsActivateStatusNumber != SMSActivateStatusNumber.UNKNOWN) {
      String message = "";

      if (smsActivateStatusNumber == SMSActivateStatusNumber.FULL_SMS) {
        message = smsFromServer.split(":", 2)[1];
      }

      return new SMSActivateGetFullSmsResponse(smsActivateStatusNumber, message);
    }

    throw validator.getBaseExceptionByErrorNameOrUnknown(smsFromServer, null);
  }

  /**
   * Returns the specified object from server with text sms.
   *
   * @param activation activation to get fullSMS.
   * @return full text sms with status:
   * <ul>
   *    <li>if SMS arrived, then the answer will be with the FULL_SMS status</li>
   *    <li>if expected then WAIT_CODE</li>
   *    <li>if canceled then CANCEL</li>
   * </ul>
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>NO_ACTIVATION - if activation is not exist.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetFullSmsResponse getFullSms(@NotNull SMSActivateActivation activation) throws SMSActivateBaseException {
    return getFullSms(activation.getId());
  }

  /**
   * Returns the all actual prices in all countries and all services.
   *
   * @return price list country.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetPricesResponse getAllPrices() throws SMSActivateBaseException {
    return getPricesByCountryIdAndServiceShortName(null, null);
  }

  /**
   * Returns the all actual price services by country id.
   *
   * @param countryId id country.
   * @return all actual price services by country id.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetPricesResponse getPricesAllServicesByCountryId(int countryId) throws SMSActivateBaseException {
    return getPricesByCountryIdAndServiceShortName(countryId, null);
  }

  /**
   * Returns the all actual price services by country id.
   *
   * @param serviceShortName short name service.
   * @return all actual price services by country id.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetPricesResponse getPricesAllCountryByServiceShortName(@NotNull String serviceShortName)
    throws SMSActivateBaseException {
    return getPricesByCountryIdAndServiceShortName(null, serviceShortName);
  }

  /**
   * Returns the actual rent prices by country and service short name.
   *
   * @param countryId        id number (default 0).
   * @param serviceShortName service for needed price list (default null).
   *                         <pre> {@code null, null -> all service and all country}</pre>
   *                         <pre> {@code countryId, null -> all service in country}</pre>
   *                         <pre> {@code null, serviceShortName -> all country by service}</pre>
   * @return price list country.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>WRONG_OPERATOR - if country id is incorrect;</li>
   *                                                 <li>WRONG_SERVICE - if service is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetPricesResponse getPricesByCountryIdAndServiceShortName(@Nullable Integer countryId, @Nullable String serviceShortName)
    throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_PRICES);

    if (serviceShortName != null) {
      if (serviceShortName.length() != 2) {
        throw new SMSActivateWrongParameterException("Wrong service short name.", "Некорректное короткое имя сервиса.");
      }

      smsActivateURLBuilder.append(SMSActivateURLKey.SERVICE, serviceShortName);
    }

    if (countryId != null) {
      if (countryId < 0) {
        throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
      }

      smsActivateURLBuilder.append(SMSActivateURLKey.COUNTRY, String.valueOf(countryId));
    }

    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    Map<Integer, Map<String, SMSActivateGetPriceInfo>> smsActivateGetPriceMap = jsonParser.tryParseJson(jsonFromServer,
      new TypeToken<Map<Integer, Map<String, SMSActivateGetPriceInfo>>>() {
      }.getType(), validator);

    return new SMSActivateGetPricesResponse(smsActivateGetPriceMap);
  }

  /**
   * Returns the country with information.
   *
   * @return country with information.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetCountriesResponse getCountries() throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_COUNTRIES);
    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    Map<Integer, SMSActivateCountryInfo> countryInformationMap = jsonParser.tryParseJson(jsonFromServer, new TypeToken<Map<Integer, SMSActivateCountryInfo>>() {
    }.getType(), validator);

    return new SMSActivateGetCountriesResponse(countryInformationMap);
  }

  /**
   * Returns the qiwi response with data on wallet.
   *
   * @return qiwi response with data on wallet with status:
   * <ul>
   *    <li>SUCCESS - all is OK;</li>
   *    <li>FALSE - acceptance of qiwi payments is not possible.</li>
   *  </ul>
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetQiwiRequisitesResponse getQiwiRequisites() throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_QIWI_REQUISITES);
    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();
    return jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateGetQiwiRequisitesResponse>() {
    }.getType(), validator);
  }

  /**
   * Returns the activation for additional service by forwarding.
   *
   * @param parentActivation activation to get additional service.
   * @param service service short name.
   * @return id activation for additional service by forwarding
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                               <ul>
   *                                                 <li>NO_BALANCE  - if your balance is less than the price.</li>
   *                                               </ul>
   *                                               <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>WRONG_ADDITIONAL_SERVICE  - if additional service is incorrect;</li>
   *                                                 <li>WRONG_ACTIVATION_ID   - if id parent is incorrect;</li>
   *                                                 <li>WRONG_SECURITY    - if trying to transfer an activation ID without forwarding, or a completed / inactive activation;</li>
   *                                                 <li>REPEAT_ADDITIONAL_SERVICE     - if ordered again service that has already been purchased;</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateActivation getAdditionalService(@NotNull SMSActivateActivation parentActivation, @NotNull String service) throws SMSActivateBaseException {
    return getAdditionalService(parentActivation.getId(), service);
  }

  /**
   * Returns the activation for additional service by forwarding.
   *
   * @param parentActivationId id activation.
   * @param service service short name.
   * @return id activation for additional service by forwarding
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                               <ul>
   *                                                 <li>NO_BALANCE  - if your balance is less than the price.</li>
   *                                               </ul>
   *                                               <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                 <li>WRONG_ADDITIONAL_SERVICE  - if additional service is incorrect;</li>
   *                                                 <li>WRONG_ACTIVATION_ID   - if id parent is incorrect;</li>
   *                                                 <li>WRONG_SECURITY    - if trying to transfer an activation ID without forwarding, or a completed / inactive activation;</li>
   *                                                 <li>REPEAT_ADDITIONAL_SERVICE     - if ordered again service that has already been purchased;</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateActivation getAdditionalService(int parentActivationId, @NotNull String service) throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_ADDITIONAL_SERVICE);
    smsActivateURLBuilder.append(SMSActivateURLKey.ID, String.valueOf(parentActivationId))
      .append(SMSActivateURLKey.SERVICE, service);

    String responseFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);

    if (!responseFromServer.startsWith(SMSActivateMagicConstant.ADDITIONAL)) {
      throw validator.getBaseExceptionByErrorNameOrUnknown(responseFromServer, null);
    }

    try {
      String[] parts = responseFromServer.split(":");
      int childId = Integer.parseInt(parts[1]);
      long number = Long.parseLong(parts[2]);

      return new SMSActivateActivation(childId, number, service);
    } catch (NumberFormatException e) {
      throw validator.getBaseExceptionByErrorNameOrUnknown(responseFromServer, "Error formatting to number.");
    }
  }

  /**
   * Returns the rent object with countries supported rent and accessed services by country.
   *
   * @param countryId   id country (default 0).
   * @param operatorSet mobile operators.
   * @param hours       rent time in hours (default {@value MINIMAL_RENT_TIME}).
   * @return the rent object with countries supported rent and accessed services by country.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter error types in this method:</p>
   *                                               <ul>
   *                                                 <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                               </ul>
   */
  @NotNull
  public SMSActivateGetRentServicesAndCountriesResponse getRentServicesAndCountries(int countryId, @Nullable Set<String> operatorSet, int hours)
    throws SMSActivateBaseException {
    if (hours < MINIMAL_RENT_TIME) {
      throw new SMSActivateWrongParameterException(
        "Time rent can't be negative or equals " + MINIMAL_RENT_TIME,
        "Время аренды не может быть меньше или равно " + MINIMAL_RENT_TIME
      );
    }
    if (countryId < 0) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
    }

    String operator = null;

    if (operatorSet != null) {
      operatorSet.removeIf(String::isEmpty);

      if (!operatorSet.isEmpty()) {
        operator = String.join(",", operatorSet);
      }
    }

    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_RENT_SERVICES_AND_COUNTRIES);
    smsActivateURLBuilder.append(SMSActivateURLKey.COUNTRY, String.valueOf(countryId))
      .append(SMSActivateURLKey.OPERATOR, operator)
      .append(SMSActivateURLKey.RENT_TIME, String.valueOf(hours));

    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    return jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateGetRentServicesAndCountriesResponse>() {
    }.getType(), validator);
  }

  /**
   * Returns the object rent on {@value MINIMAL_RENT_TIME} by countryId and service short name.
   *
   * @param countryId id country.
   * @param service   service to which you need to get a number.
   * @return object rent.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                              <ul>
   *                                                <li>NO_NUMBERS - if currently no numbers;</li>
   *                                                <li>NO_BALANCE - if your balance is less than the price;</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>ACCOUNT_INACTIVE  - if no free numbers.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateRentActivation getRentNumber(int countryId, @NotNull String service) throws SMSActivateBaseException {
    return getRentNumber(countryId, service, MINIMAL_RENT_TIME);
  }

  /**
   * Returns the object rent on {@value MINIMAL_RENT_TIME} by countryId and service short name.
   *
   * @param countryId id country.
   * @param service   service to which you need to get a number.
   * @param hours     rent time in hours.
   * @return object rent.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                              <ul>
   *                                                <li>NO_NUMBERS - if currently no numbers;</li>
   *                                                <li>NO_BALANCE - if your balance is less than the price;</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>ACCOUNT_INACTIVE  - if no free numbers.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateRentActivation getRentNumber(int countryId, @NotNull String service, int hours) throws SMSActivateBaseException {
    return getRentNumber(countryId, service, null, hours, null);
  }

  /**
   * Returns the object rent on time.
   *
   * @param countryId  id country (default 0 - Russia).
   * @param service    service to which you need to get a number.
   * @param operator   mobile operator.
   * @param hours      rent time in hours (default MINIMAL_RENT_TIME hour).
   * @param urlWebhook url for webhook.
   * @return object rent.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                              <ul>
   *                                                <li>NO_NUMBERS - if currently no numbers;</li>
   *                                                <li>NO_BALANCE - if your balance is less than the price;</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>BAD_SERVICE - if service is incorrect;</li>
   *                                                <li>ACCOUNT_INACTIVE  - if no free numbers;</li>
   *                                                <li>WRONG_OPERATOR  - if operator is incorrect;</li>
   *                                                <li>WRONG_COUNTRY_ID  - if country id is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateRentActivation getRentNumber(
    int countryId,
    @NotNull String service,
    @Nullable String operator,
    int hours,
    @Nullable String urlWebhook
  ) throws SMSActivateBaseException {
    if (countryId < 0) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_COUNTRY_ID);
    }

    if (hours < MINIMAL_RENT_TIME) {
      throw new SMSActivateWrongParameterException(
        String.format("The rental time cannot be less than %d.", MINIMAL_RENT_TIME),
        String.format("Время аренды не может быть меньше чем %d.", MINIMAL_RENT_TIME)
      );
    }

    if (operator != null && operator.isEmpty()) {
      throw new SMSActivateWrongParameterException(SMSActivateWrongParameter.WRONG_OPERATOR);
    }

    if (urlWebhook != null && urlWebhook.isEmpty()) {
      throw new SMSActivateWrongParameterException(
        "Parameter url-webhook can't be empty.",
        "Параметер url-webhook не может быть пустым."
      );
    }

    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_RENT_NUMBER);
    smsActivateURLBuilder.append(SMSActivateURLKey.RENT_TIME, String.valueOf(hours))
      .append(SMSActivateURLKey.COUNTRY, String.valueOf(countryId))
      .append(SMSActivateURLKey.OPERATOR, operator)
      .append(SMSActivateURLKey.URL, urlWebhook)
      .append(SMSActivateURLKey.SERVICE, service);

    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    if (validator.containsSuccessStatus(jsonFromServer)) {
      SMSActivateErrorResponse errorResponse = jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateErrorResponse>() {
      }.getType(), validator);

      throw validator.getBaseExceptionByErrorNameOrUnknown(errorResponse.getMessage(), null);
    }

    SMSActivateGetRentNumberResponse smsActivateGetRentNumberResponse = jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateGetRentNumberResponse>() {
    }.getType(), validator);
    return smsActivateGetRentNumberResponse.getSMSmsActivateGetRentNumber();
  }

  /**
   * Returns the list sms.
   *
   * @param rentId id received in response when ordering a number.
   * @return list sms.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>NO_ID_RENT   - if is not input.</li>
   *                                                <li>STATUS_WAIT_CODE    - if not sms.</li>
   *                                                <li>STATUS_CANCEL     - if rent is canceled.</li>
   *                                                <li>STATUS_FINISH      - if rent is finished.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetRentStatusResponse getRentStatus(int rentId) throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_RENT_STATUS);
    smsActivateURLBuilder.append(SMSActivateURLKey.ID, String.valueOf(rentId));

    String jsonResponseFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    if (validator.containsSuccessStatus(jsonResponseFromServer)) {
      SMSActivateErrorResponse errorResponse = jsonParser.tryParseJson(jsonResponseFromServer, new TypeToken<SMSActivateErrorResponse>() {
      }.getType(), validator);
      throw validator.getBaseExceptionByErrorNameOrUnknown(errorResponse.getMessage(), null);
    }

    return jsonParser.tryParseJson(jsonResponseFromServer, new TypeToken<SMSActivateGetRentStatusResponse>() {
    }.getType(), validator);
  }

  /**
   * Returns the list sms.
   *
   * @param rentActivation to get the list sms.
   * @return list sms.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>NO_ID_RENT   - if is not input.</li>
   *                                                <li>STATUS_WAIT_CODE    - if not sms.</li>
   *                                                <li>STATUS_CANCEL     - if rent is canceled.</li>
   *                                                <li>STATUS_FINISH      - if rent is finished.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetRentStatusResponse getRentStatus(@NotNull SMSActivateRentActivation rentActivation) throws SMSActivateBaseException {
    return getRentStatus(rentActivation.getId());
  }

  /**
   * Sets the status on rent.
   *
   * @param rentId id activation for set status rent.
   * @param status status rent.
   * @return response status from server.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>NO_ID_RENT - if is not input.</li>
   *                                                <li>INCORRECT_STATUS - if status is incorrect.</li>
   *                                                <li>CANT_CANCEL - if it is impossible to cancel the rent (more than 20 min.).</li>
   *                                                <li>ALREADY_FINISH - if rent is finished.</li>
   *                                                <li>ALREADY_CANCEL - if rent is canceled.</li>
   *                                                <li>INVALID_PHONE - if id is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateRentStatus setRentStatus(int rentId, @NotNull SMSActivateClientRentStatus status)
    throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.SET_RENT_STATUS);
    smsActivateURLBuilder.append(SMSActivateURLKey.ID, String.valueOf(rentId))
      .append(SMSActivateURLKey.STATUS, String.valueOf(status.getId()));

    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    if (validator.containsSuccessStatus(jsonFromServer)) {
      SMSActivateErrorResponse response = jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateErrorResponse>() {
      }.getType(), validator);
      throw validator.getBaseExceptionByErrorNameOrUnknown(response.getMessage(), null);
    }

    return SMSActivateRentStatus.SUCCESS;
  }

  /**
   * Sets the status on rent.
   *
   * @param rentActivation rent to set status.
   * @param status         status rent.
   * @return response status from server.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                                <li>NO_ID_RENT - if is not input.</li>
   *                                                <li>INCORRECT_STATUS - if status is incorrect.</li>
   *                                                <li>CANT_CANCEL - if it is impossible to cancel the rent (more than 20 min.).</li>
   *                                                <li>ALREADY_FINISH - if rent is finished.</li>
   *                                                <li>ALREADY_CANCEL - if rent is canceled.</li>
   *                                                <li>INVALID_PHONE - if id is incorrect.</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateRentStatus setRentStatus(SMSActivateRentActivation rentActivation, @NotNull SMSActivateClientRentStatus status)
    throws SMSActivateBaseException {
    return setRentStatus(rentActivation.getId(), status);
  }

  /**
   * Returns the current rents.
   *
   * @return current rents.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   *                                            <p>Types errors:</p>
   *                                            <p>Base type error in this method:</p>
   *                                              <ul>
   *                                                <li>NO_NUMBERS - if no rented phone numbers numbers.</li>
   *                                              </ul>
   *                                              <p>Wrong parameter type error:</p>
   *                                              <ul>
   *                                                <li>BAD_KEY - if your api-key is incorrect;</li>
   *                                              </ul>
   */
  @NotNull
  public SMSActivateGetRentListResponse getRentList() throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, SMSActivateAction.GET_RENT_LIST);

    String jsonFromServer = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    SMSActivateJsonParser jsonParser = new SMSActivateJsonParser();

    if (validator.containsSuccessStatus(jsonFromServer)) {
      SMSActivateErrorResponse smsActivateErrorResponse = jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateErrorResponse>() {
      }.getType(), validator);
      throw validator.getBaseExceptionByErrorNameOrUnknown(smsActivateErrorResponse.getMessage(), null);
    }

    return jsonParser.tryParseJson(jsonFromServer, new TypeToken<SMSActivateGetRentListResponse>() {
    }.getType(), validator);
  }

  /**
   * Wait the sms on activation by minutes.
   *
   * @param activationId   activation id to get sms.
   * @param maxWaitMinutes minutes to wait.
   * @return code from sms.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   */
  @Nullable
  public String waitSms(int activationId, int maxWaitMinutes) throws SMSActivateBaseException {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, maxWaitMinutes);
    SMSActivateGetStatusResponse statusResponse = getStatus(activationId);

    while (System.currentTimeMillis() < calendar.getTime().getTime()) {
      if (
        statusResponse.getSMSActivateGetStatus() == SMSActivateGetStatusActivation.OK &&
          !statusResponse.getCodeFromSMS().equalsIgnoreCase(SMSActivateMagicConstant.NO_CODE)
      ) {

        return statusResponse.getCodeFromSMS();
      }

      try {
        Thread.sleep(5 * 1000);
      } catch (Exception ignored) {
      }
      statusResponse = getStatus(activationId);
    }

    return statusResponse.getCodeFromSMS();
  }

  /**
   * Wait the sms on activation by minutes.
   *
   * @param activation     activation to get sms.
   * @param maxWaitMinutes minutes to wait.
   * @return code from sms.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   */
  @Nullable
  public String waitSms(@NotNull SMSActivateActivation activation, int maxWaitMinutes) throws SMSActivateBaseException {
    return waitSms(activation.getId(), maxWaitMinutes);
  }

  /**
   * Returns a list of sms that came for rent after a while.
   *
   * @param rentActivation rent for rent for which you need to return the list of sms.
   * @param maxWaitMinutes how many minutes to wait.
   * @return list of sms that came for rent.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   */
  @NotNull
  public List<SMSActivateSMS> waitSmsForRent(@NotNull SMSActivateRentActivation rentActivation, int maxWaitMinutes) throws SMSActivateBaseException {
    return this.waitSmsForRent(rentActivation.getId(), maxWaitMinutes);
  }

  /**
   * Returns a list of sms that came for rent after a while.
   *
   * @param rentId         rent for rent for which you need to return the list of sms.
   * @param maxWaitMinutes how many minutes to wait.
   * @return list of sms that came for rent.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   * @throws SMSActivateUnknownException        if error type not documented.
   */
  @NotNull
  public List<SMSActivateSMS> waitSmsForRent(int rentId, int maxWaitMinutes) throws SMSActivateBaseException {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, maxWaitMinutes);
    int countSMS = 0;
    List<SMSActivateSMS> smsActivateSMS = new ArrayList<>();

    try {
      SMSActivateGetRentStatusResponse rentStatus = this.getRentStatus(rentId);
      countSMS = rentStatus.getCountSms();
      smsActivateSMS = rentStatus.getSmsActivateSMSList();
    } catch (SMSActivateBaseException e) {
      if (e.getTypeError() != SMSActivateBaseTypeError.WAIT_CODE) {
        throw e;
      }
    }

    while (System.currentTimeMillis() < calendar.getTime().getTime()) {
      try {
        SMSActivateGetRentStatusResponse rentStatus = this.getRentStatus(rentId);

        if (rentStatus.getCountSms() != countSMS) {
          return rentStatus.getSmsActivateSMSList();
        }
      } catch (SMSActivateBaseException e) {
        if (e.getTypeError() != SMSActivateBaseTypeError.WAIT_CODE) {
          throw e;
        }
      }

      try {
        Thread.sleep(5 * 1000);
      } catch (Exception ignored) {
      }
    }

    return smsActivateSMS;
  }

  /**
   * Returns the current account balance by specific action.
   *
   * @param smsActivateAction name specific action to get balance.
   * @return current account balance.
   * @throws SMSActivateWrongParameterException if one of parameters is incorrect.
   */
  @NotNull
  private BigDecimal getBalanceByAction(@NotNull SMSActivateAction smsActivateAction) throws SMSActivateBaseException {
    SMSActivateURLBuilder smsActivateURLBuilder = new SMSActivateURLBuilder(apiKey, smsActivateAction);
    String balance = new SMSActivateWebClient(smsActivateWebClientListener).getOrThrowCommonException(smsActivateURLBuilder, validator);
    Matcher matcher = patternDigit.matcher(balance);

    if (!matcher.find()) {
      throw new SMSActivateBaseException("Error: " + balance, "Error: " + balance);
    }

    return new BigDecimal(matcher.group());
  }
}
