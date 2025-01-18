package aueb.gr.pm.schoolapp.repository;

import aueb.gr.pm.schoolapp.model.Student;
import aueb.gr.pm.schoolapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student , Long> , JpaSpecificationExecutor<Student> {

    Optional<Student> findStudentByUser(User user);
    Optional<Student> findStudentByUuid(String uuid);
}