package aueb.gr.pm.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import aueb.gr.pm.schoolapp.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment , Long> , JpaSpecificationExecutor<Attachment> {

}
