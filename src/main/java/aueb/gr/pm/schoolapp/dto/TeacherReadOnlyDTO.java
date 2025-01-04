package aueb.gr.pm.schoolapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherReadOnlyDTO {

    private Long id;
    private String uuid;
    private Boolean isActive;
    private UserReadOnlyDTO user;
    private PersonalInfoReadOnlyDTO personalInfo;
}
