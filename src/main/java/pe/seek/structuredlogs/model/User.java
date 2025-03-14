package pe.seek.structuredlogs.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Long id;
    private String username;
    private String email;
    private Boolean enabled;

}
