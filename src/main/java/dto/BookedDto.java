package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BookedDto {
    private String email;
    private String startDate;
    private String endDate;
}
