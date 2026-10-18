package dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarsDto {
    private List<Car> cars;
}
