package aueb.gr.pm.schoolapp.model.static_data;

import jakarta.persistence.*;
import lombok.*;

import aueb.gr.pm.schoolapp.model.Employee;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "educational_units")
public class EducationalUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Getter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "eduUnits")
    private Set<Employee> employees = new HashSet<>();


}
