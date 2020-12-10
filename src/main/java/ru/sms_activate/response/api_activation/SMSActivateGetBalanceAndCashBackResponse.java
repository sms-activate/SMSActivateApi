package ru.sms_activate.response.api_activation;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class SMSActivateGetBalanceAndCashBackResponse {
  /**
   * Cashback in account (ruble).
   */
  private final BigDecimal cashBack;

  /**
   * Balance in account (ruble).
   */
  private final BigDecimal balance;

  /**
   * Constructor getBalanceAndCashBack response with data from server.
   *
   * @param balance  balance in account (ruble).
   * @param cashBack cashback in account (ruble).
   */
  public SMSActivateGetBalanceAndCashBackResponse(@NotNull BigDecimal balance, @NotNull BigDecimal cashBack) {
    this.balance = balance;
    this.cashBack = cashBack;
  }

  /**
   * Returns the cashback in account (ruble).
   *
   * @return cashback in account (ruble).
   */
  @NotNull
  public BigDecimal getCashBack() {
    return cashBack;
  }

  /**
   * Returns the balance in account (ruble).
   *
   * @return balance in account (ruble).
   */
  @NotNull
  public BigDecimal getBalance() {
    return balance;
  }

  /**
   * Returns the balance amount and cashback (ruble).
   *
   * @return balance amount and cashback (ruble).
   */
  @NotNull
  public BigDecimal getBalanceAndCashBack() {
    return balance.add(cashBack);
  }

  @Override
  public String toString() {
    return "SMSActivateGetBalanceAndCashBackResponse{" +
      "cashBack=" + cashBack +
      ", balance=" + balance +
      '}';
  }
}
