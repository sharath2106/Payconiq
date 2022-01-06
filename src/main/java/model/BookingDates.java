package model;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class BookingDates {
  public String checkin;
  public String checkout;
}
