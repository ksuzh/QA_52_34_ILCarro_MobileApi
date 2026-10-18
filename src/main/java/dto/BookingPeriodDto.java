package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookingPeriodDto {
    private String startDate;
    private String endDate;
}

