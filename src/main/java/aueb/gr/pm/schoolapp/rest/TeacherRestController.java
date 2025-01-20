package aueb.gr.pm.schoolapp.rest;

import aueb.gr.pm.schoolapp.core.exceptions.AppObjectAlreadyExists;
import aueb.gr.pm.schoolapp.core.exceptions.AppObjectInvalidArgumentException;
import aueb.gr.pm.schoolapp.core.exceptions.AppServerException;
import aueb.gr.pm.schoolapp.core.exceptions.ValidationException;
import aueb.gr.pm.schoolapp.dto.TeacherInsertDTO;
import aueb.gr.pm.schoolapp.dto.TeacherReadOnlyDTO;
import aueb.gr.pm.schoolapp.mapper.Mapper;
import aueb.gr.pm.schoolapp.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeacherRestController {

    private final TeacherService teacherService;
    private final Mapper mapper;

    @GetMapping("/teachers")
    public ResponseEntity<Page<TeacherReadOnlyDTO>> getPaginatedTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size) {

        Page<TeacherReadOnlyDTO> teachersPage = teacherService.getPaginatedTeachers(page, size);
        return new ResponseEntity<>(teachersPage , HttpStatus.OK);
    }

    @PostMapping("/teachers/save")
    public ResponseEntity<TeacherReadOnlyDTO> saveTeacher(
            @Valid
            @RequestPart(name = "teacher")TeacherInsertDTO teacherInsertDTO, MultipartFile amkaFile,
            BindingResult bindingResult) throws AppObjectInvalidArgumentException , ValidationException , AppObjectAlreadyExists , AppServerException {

        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult);
        }

        try{
            TeacherReadOnlyDTO teacherReadOnlyDTO = teacherService.saveTeacher(teacherInsertDTO , amkaFile);
            return new ResponseEntity<>(teacherReadOnlyDTO , HttpStatus.OK);
        } catch (IOException e){
                throw  new AppServerException("Attachment " , "Attachment can not get uploaded");
        }
    }
}
