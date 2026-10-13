package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String username;

}
