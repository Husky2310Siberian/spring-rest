package aueb.gr.pm.schoolapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherInsertDTO {

    @NotNull(message = "Is active must not be null")
    private boolean isActive;

    @NotNull(message = "User details must not be null")
    private UserInsertDTO user;

    @NotNull(message = "Personal info must not be null")
    private PersonalInfoInsetDTO personalInfo;
}
