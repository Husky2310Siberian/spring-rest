package aueb.gr.pm.schoolapp.service;

import aueb.gr.pm.schoolapp.core.exceptions.AppObjectAlreadyExists;
import aueb.gr.pm.schoolapp.core.exceptions.AppObjectInvalidArgumentException;
import aueb.gr.pm.schoolapp.dto.TeacherInsertDTO;
import aueb.gr.pm.schoolapp.dto.TeacherReadOnlyDTO;
import aueb.gr.pm.schoolapp.model.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ITeacherService {
    public TeacherReadOnlyDTO saveTeacher(TeacherInsertDTO teacherInsertDTO, MultipartFile amkaFile) throws AppObjectAlreadyExists ,
            AppObjectInvalidArgumentException , IOException;
}
