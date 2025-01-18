package aueb.gr.pm.schoolapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import aueb.gr.pm.schoolapp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> , JpaSpecificationExecutor<Employee> {
    
}
