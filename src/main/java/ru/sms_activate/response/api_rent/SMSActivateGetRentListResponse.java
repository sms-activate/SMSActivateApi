package ru.sms_activate.response.api_rent;

import ru.sms_activate.error.base.SMSActivateBaseException;
import ru.sms_activate.response.api_rent.extra.SMSActivateRentNumber;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SMSActivateGetRentListResponse {
  /**
   * Map current rents where key is id rent.
   */
  private Map<Integer, SMSActivateRentNumber> values;

  private SMSActivateGetRentListResponse() {
  }

  /**
   * Returns the rent by index.
   *
   * @param rentId index rent.
   * @return rent.
   * @throws SMSActivateBaseException if index incorrect.
   */
  @NotNull
  public SMSActivateRentNumber get(int rentId) throws SMSActivateBaseException {
    for (SMSActivateRentNumber activateRentNumber : values.values()) {
      if (activateRentNumber.getId() == rentId) {
        return activateRentNumber;
      }
    }

    throw new SMSActivateBaseException("Rent id is incorrect.", "Некорректный индентификатор аренды.");
  }

  /**
   * Returns the set id rents.
   *
   * @return set id rents.
   */
  @NotNull
  public SortedSet<Integer> getRentSet() {
    SortedSet<Integer> rentIdSet = new TreeSet<>();

    for (SMSActivateRentNumber activateRentNumber : values.values()) {
      rentIdSet.add(activateRentNumber.getId());
    }

    return rentIdSet;
  }

  /**
   * Returns the list current rents.
   *
   * @return the list current rents.
   */
  @NotNull
  public List<SMSActivateRentNumber> getRentNumberList() {
    return new ArrayList<>(values.values());
  }
}
