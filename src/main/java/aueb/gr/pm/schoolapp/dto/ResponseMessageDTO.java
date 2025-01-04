package aueb.gr.pm.schoolapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessageDTO {
    private String description;
    private String code;

    public ResponseMessageDTO(String code){
        this.code = code;
        this.description = "";
    }
}
