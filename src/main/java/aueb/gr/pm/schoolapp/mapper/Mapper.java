package aueb.gr.pm.schoolapp.mapper;

import aueb.gr.pm.schoolapp.dto.*;
import org.springframework.stereotype.Component;
import aueb.gr.pm.schoolapp.model.PersonalInfo;
import aueb.gr.pm.schoolapp.model.Teacher;
import aueb.gr.pm.schoolapp.model.User;

@Component
public class Mapper {

    /*
    From DTO to Teacher
     */

    public Teacher mapToTeacherEntity (TeacherInsertDTO teacherInsertDTO){

        //private final PasswordEncoder passwordEncoder;

        Teacher teacher = new Teacher();
        teacher.setIsActive(teacherInsertDTO.isActive());

        User user = new User();
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        user.setFirstname(userInsertDTO.getFirstname());
        user.setLastname(userInsertDTO.getLastname());
        user.setUsername(userInsertDTO.getPassword());
        user.setPassword(userInsertDTO.getPassword());
        user.setFatherLastname(userInsertDTO.getFatherLastname());
        user.setMotherLastname(userInsertDTO.getMotherLastname());
        user.setFatherName(userInsertDTO.getFatherName());
        user.setMotherName(userInsertDTO.getMotherName());
        user.setDateOfBirth(userInsertDTO.getDateOfBirth());
        user.setGender(userInsertDTO.getGender());
        user.setRole(userInsertDTO.getRole());
        user.setIsActive(userInsertDTO.getIsActive());
        teacher.setUser(user);

        PersonalInfo personalInfo = new PersonalInfo();
        PersonalInfoInsetDTO personalInfoInsetDTO = teacherInsertDTO.getPersonalInfo();
        personalInfo.setAmka(personalInfoInsetDTO.getAmka());
        personalInfo.setIdentityNumber(personalInfoInsetDTO.getIdentifyNumber());
        personalInfo.setPlaceOfBirth(personalInfo.getPlaceOfBirth());
        personalInfo.setMunicipalityOfRegistration(personalInfo.getMunicipalityOfRegistration());
        teacher.setPersonalInfo(personalInfo);

        return teacher;
    }

    public TeacherReadOnlyDTO mapToTeacherReadOnlyDTO(Teacher teacher){

        TeacherReadOnlyDTO dto = new TeacherReadOnlyDTO();
        dto.setId(teacher.getId());
        dto.setUuid(teacher.getUuid());
        dto.setIsActive(teacher.getIsActive());

        //map to UserReadOnlyDTO
        User user = new User();
        UserReadOnlyDTO userDTO = new UserReadOnlyDTO();
        userDTO.setFirstname(teacher.getUser().getFirstname());
        userDTO.setLastname(teacher.getUser().getLastname());
        userDTO.setVat(teacher.getUser().getVat());
        dto.setUser(userDTO);

        //map to PersonalInfoDTO
        PersonalInfoReadOnlyDTO personalInfoDTO = new PersonalInfoReadOnlyDTO();
        personalInfoDTO.setAmka(teacher.getPersonalInfo().getAmka());
        personalInfoDTO.setIdentityNumber(teacher.getPersonalInfo().getIdentityNumber());
        dto.setPersonalInfo(personalInfoDTO);

        return dto;
    }
}

