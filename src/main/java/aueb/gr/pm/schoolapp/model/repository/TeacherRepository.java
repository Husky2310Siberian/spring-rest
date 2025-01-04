package aueb.gr.pm.schoolapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import aueb.gr.pm.schoolapp.model.Teacher;
import aueb.gr.pm.schoolapp.model.User;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> , JpaSpecificationExecutor<Teacher> {

    Optional<Teacher> findTeacherByUserId(User user);
    Optional<Teacher> findTeacherByUuid(String uuid);

}
