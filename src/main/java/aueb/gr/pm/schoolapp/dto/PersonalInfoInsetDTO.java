package aueb.gr.pm.schoolapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalInfoInsetDTO {

    @Pattern(regexp = "^\\{11}$" , message = "AMKA must be an 11-digit string")
    private String amka;

    @NotEmpty(message = "Identity number must not be empty")
    private String identifyNumber;

    @NotEmpty(message = "Place of birth must not be empty")
    private String placeOfBirth;

    @NotEmpty(message = "Municipality must not be empty")
    private String municipalityOfRegistration;
}
