package in.manager.system.controller;


import in.manager.system.model.Employee;
import in.manager.system.service.EmployeeService;
import in.manager.system.util.ResponseMessage;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:01 AM
 */

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController
{

    @Autowired
    EmployeeService employeeService;

    /**
     * get employee list after manager login
     */
    @GetMapping("/list")
    public List<Employee> getAllEmployees()
    {
        return employeeService.getAllEmployees();
    }

    /**
     * provide the access of manager for added the new employee
     *
     * @param employee
     */
    @PostMapping("/add")
    public ResponseMessage addEmployee(@RequestBody Employee employee)
    {
        return employeeService.addEmployee(employee);
    }

    /**
     * update employee details based on employee id
     *
     * @param id
     * @param employee
     */
    @PutMapping("/{id}/update")
    public ResponseMessage updateEmployeeById(@PathVariable Integer id, @RequestBody Employee employee)
    {
        return employeeService.updateEmployeeById(id, employee);
    }

    /**
     * get existing employee detail based on employee id
     *
     * @param id
     */
    @GetMapping("/{id}")
    public Employee findAgriAgrochemicalMasterById(@PathVariable int id) throws NotFoundException
    {
        return employeeService.findEmployeeById(id);
    }

    /**
     * it is soft delete based on employee id, only change status in database
     *
     * @param id
     */
    @DeleteMapping("/{id}/delete")
    public ResponseMessage deleteAgriAgrochemicalMasterById(@PathVariable Integer id)
    {
        return employeeService.deleteEmployeeById(id);
    }
}
