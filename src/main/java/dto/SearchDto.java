package dto;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SearchDto {
    private String city;
    private String startDate;
    private String endDate;
}
