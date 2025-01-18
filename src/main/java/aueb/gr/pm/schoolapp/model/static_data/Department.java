package aueb.gr.pm.schoolapp.model.static_data;

import aueb.gr.pm.schoolapp.model.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "department")
    private Set<Student> students = new HashSet<>();

    public Set<Student> getAllStudents() {
        if (students == null) students = new HashSet<>();
        return Collections.unmodifiableSet(students);
    }

    public void addStudents(Student student) {
        if (students == null) students = new HashSet<>();
        students.add(student);
    }

}
