package model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class Booking {
  public String firstname;
  public String lastname;
  public Integer totalprice;
  public Boolean depositpaid;
  public BookingDates bookingdates;
  public String additionalneeds;
}
