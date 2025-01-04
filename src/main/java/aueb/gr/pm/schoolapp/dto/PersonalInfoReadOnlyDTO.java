package aueb.gr.pm.schoolapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalInfoReadOnlyDTO {

    private String amka;
    private String identityNumber;
}
