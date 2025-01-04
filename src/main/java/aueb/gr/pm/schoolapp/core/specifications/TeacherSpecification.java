package aueb.gr.pm.schoolapp.core.specifications;

import aueb.gr.pm.schoolapp.model.PersonalInfo;
import aueb.gr.pm.schoolapp.model.Teacher;
import aueb.gr.pm.schoolapp.model.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class TeacherSpecification {

    private TeacherSpecification(){
    }

    public static Specification<Teacher> teacherUserVatIs(String vat){
        return ((root, query, criteriaBuilder) -> {
            if (vat == null || vat.isBlank()){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            Join<Teacher , User> user = root.join("user");
            return criteriaBuilder.equal(user.get("vat"), vat);
        });
    }

    public static Specification <Teacher> teacherUserIsActive(Boolean isActive){
        return ((root, query, criteriaBuilder) -> {
            if (isActive == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            Join<Teacher , User> user = root.join("user");
            return criteriaBuilder.equal(user.get("isActive"), isActive);
        });
    }

    public static Specification<Teacher> teacherPersonalInfo(String amka){
        return ((root, query, criteriaBuilder) -> {
            if (amka == null || amka.isBlank()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            Join<PersonalInfo, Teacher> personalInfo = root.join("personalInfo");
            return criteriaBuilder.equal(personalInfo.get("amka"), amka);
        });
    }

    public static Specification<Teacher> teacherStringFieldLike(String field , String value){
        return ((root, query, criteriaBuilder) -> {
            if(value == null || value.trim().isEmpty()){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.like(criteriaBuilder.upper(root.get(field)), "%" + value.toUpperCase() + "%");
        });
    }
}