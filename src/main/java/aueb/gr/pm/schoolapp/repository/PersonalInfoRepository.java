package aueb.gr.pm.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import aueb.gr.pm.schoolapp.model.PersonalInfo;
import java.util.Optional;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> , JpaSpecificationExecutor<PersonalInfo> {

    Optional<PersonalInfo> findByAmka(String amka);
    Optional<PersonalInfo> findByIdentityNumber(String identityNumber);

}
