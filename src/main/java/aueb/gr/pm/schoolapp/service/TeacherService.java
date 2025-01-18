package aueb.gr.pm.schoolapp.service;

import aueb.gr.pm.schoolapp.core.exceptions.AppObjectAlreadyExists;
import aueb.gr.pm.schoolapp.core.exceptions.AppObjectInvalidArgumentException;
import aueb.gr.pm.schoolapp.dto.TeacherInsertDTO;
import aueb.gr.pm.schoolapp.dto.TeacherReadOnlyDTO;
import aueb.gr.pm.schoolapp.mapper.Mapper;
import aueb.gr.pm.schoolapp.model.Attachment;
import aueb.gr.pm.schoolapp.model.PersonalInfo;
import aueb.gr.pm.schoolapp.model.Teacher;
import aueb.gr.pm.schoolapp.repository.AttachmentRepository;
import aueb.gr.pm.schoolapp.repository.PersonalInfoRepository;
import aueb.gr.pm.schoolapp.repository.TeacherRepository;
import aueb.gr.pm.schoolapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final AttachmentRepository attachmentRepository;
    private Mapper mapper;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TeacherReadOnlyDTO saveTeacher(TeacherInsertDTO teacherInsertDTO, MultipartFile amkaFile)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException, IOException {


        if (userRepository.findByVat(teacherInsertDTO.getUser().getVat()).isPresent()){
            throw  new AppObjectAlreadyExists("User" , "User with vat "
                    +teacherInsertDTO.getUser().getVat()+ "already exists");
        }

        if (userRepository.findUserByUsername(teacherInsertDTO.getUser().getUsername()).isPresent()){
            throw new AppObjectAlreadyExists("User" , "User with username"
                    + teacherInsertDTO.getUser().getUsername() + "already exist");
        }

        if (personalInfoRepository.findByAmka(teacherInsertDTO.getPersonalInfo().getAmka()).isPresent()){
            throw new AppObjectAlreadyExists("PersonalInfo" ,"Personal info with AMKA"
                    + teacherInsertDTO.getPersonalInfo().getAmka()+ "already exists");
        }

        if (personalInfoRepository.findByIdentityNumber(teacherInsertDTO.getPersonalInfo().getIdentifyNumber()).isPresent()){
            throw new AppObjectAlreadyExists("PersonalInfo" ,"Personal info with identity number"
                    + teacherInsertDTO.getPersonalInfo().getIdentifyNumber()+ "already exists");
        }

        Teacher teacher = mapper.mapToTeacherEntity(teacherInsertDTO);
        saveAmkaFile(teacher.getPersonalInfo(), amkaFile);

        Teacher savedTeacher = teacherRepository.save(teacher);
        return mapper.mapToTeacherReadOnlyDTO(savedTeacher);
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveAmkaFile(PersonalInfo personalInfo , MultipartFile amkaFile) throws IOException{

        if (amkaFile != null && amkaFile.isEmpty()){

            //returns the filename
            String originalFilename = amkaFile.getOriginalFilename();

            //returns the savedname , containins uuid and "."
            String savedName = UUID.randomUUID() + getFileExtension(originalFilename);

            String uploadDir = "uploads/";
            Path filepath = Paths.get(uploadDir + savedName);
            Files.createDirectories(filepath.getParent());
            Files.write(filepath, amkaFile.getBytes());

            Attachment attachment = new Attachment();
            attachment.setFilename(originalFilename);
            attachment.setSavedName(savedName);
            attachment.setFilePath(filepath.toString());
            attachment.setContentType(amkaFile.getContentType());
            attachment.setExtension(getFileExtension(originalFilename));

            personalInfo.setAmkaFile(attachment);
        }
    }

    public String getFileExtension(String filename){
        if (filename != null && filename.contains(".")){
            return filename.substring(filename.lastIndexOf("."));
        }
        return "";
    }

    public Page<TeacherReadOnlyDTO> getPaginatedTeachers(int size , int page){
        String defaultSort = "id";
        Pageable pageable = PageRequest.of(page , size , Sort.by(defaultSort).ascending());
        return teacherRepository.findAll(pageable).map(mapper::mapToTeacherReadOnlyDTO);
    }

    public Page<TeacherReadOnlyDTO> getPaginatedSortedTeachers(int size , int page , String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(size , page , sort);
        return teacherRepository.findAll(pageable).map(mapper::mapToTeacherReadOnlyDTO);
    }
}