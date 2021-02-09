package in.manager.system.repository;


import in.manager.system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:31 AM
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

}
