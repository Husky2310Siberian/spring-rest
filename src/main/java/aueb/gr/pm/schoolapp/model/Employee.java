package aueb.gr.pm.schoolapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import aueb.gr.pm.schoolapp.model.static_data.EducationalUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Specifies that all persistence operations (such as PERSIST, MERGE, REMOVE, etc.)
     * should cascade from this entity to the associated User entity.
     * For example:
     * If you save the Employee, the associated User is also saved automatically.
     * If you delete the Employee, the associated User is also deleted.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany
    @JoinTable(name = "employees_edu_units")
    private Set<EducationalUnit> eduUnits = new HashSet<>();

    public void addEducationalUnit(EducationalUnit educationalUnit) {
        if (eduUnits == null) eduUnits = new HashSet<>();
        eduUnits.add(educationalUnit);
    }

    public boolean hasEducationalUnit() {
        return eduUnits != null && !eduUnits.isEmpty();
    }

    @PrePersist
    public void initializeUUID() {
        if (uuid == null) uuid = UUID.randomUUID().toString();
    }
}