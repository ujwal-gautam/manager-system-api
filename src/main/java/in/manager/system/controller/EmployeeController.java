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
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public ResponseMessage addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}/update")
    public ResponseMessage updateEmployeeById(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployeeById(id, employee);
    }

    @GetMapping("/{id}")
    public Employee findAgriAgrochemicalMasterById(@PathVariable int id) throws NotFoundException {
        return employeeService.findEmployeeById(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseMessage deleteAgriAgrochemicalMasterById(@PathVariable Integer id) {
        return employeeService.deleteEmployeeById(id);
    }
}
