package aueb.gr.pm.schoolapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import aueb.gr.pm.schoolapp.core.enums.GenderType;
import aueb.gr.pm.schoolapp.core.enums.Role;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotNull(message = "Is active must not be null")
    private Boolean isActive;

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 2, message = "Firstname must be at least 2 characters long")
    private String firstname;

    @NotEmpty(message = "Firstname must not be empty")
    @Size(min = 2, message = "Lastname must be at least 2 characters long")
    private String lastname;

    @Email(message = "Invalid username")
    private String username;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*-]).{8,}$", message = "Invalid password")
    private String password;

    @NotEmpty(message = "Vat must not be empty")
    @Pattern(regexp = "^\\d{9}$")
    private String vat;

    @NotEmpty(message = "Father name must not be empty")
    private String fatherName;

    private String fatherLastname;

    @NotEmpty(message = "Mother name must not be empty")
    private String motherName;
    private String motherLastname;

    @NotNull(message = "Date of birth must not be null")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Pattern(regexp = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" +
            "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" +
            "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender must not be null")
    private GenderType gender;

    @NotNull(message = "Role must not be null")
    private Role role;
}
