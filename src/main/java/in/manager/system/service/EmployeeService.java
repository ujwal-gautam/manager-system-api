package in.manager.system.service;

import in.manager.system.model.Employee;
import in.manager.system.repository.EmployeeRepository;
import in.manager.system.util.APIConstants;
import in.manager.system.util.ResponseMessage;
import in.manager.system.util.ResponseMessageUtil;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author ujwal-gautam
 * @Date 26/10/20
 */

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ResponseMessageUtil responseMessageUtil;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    public ResponseMessage addEmployee(Employee employee) {
        try {
            employee.setStatus(APIConstants.STATUS_ACTIVE);
            employee = employeeRepository.save(employee);

            return responseMessageUtil.sendResponse(true, "Employee add successfully - " + employee.getFirstName(), "");
        } catch (Exception e) {
            return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
        }
    }

    public ResponseMessage updateEmployeeById(Integer id, Employee employee) {

        try {
            Optional<Employee> foundEmployee = employeeRepository.findById(id);

            if (foundEmployee.isPresent()) {

                employee.setId(id);
                employee.setStatus(APIConstants.STATUS_INACTIVE);

                employeeRepository.save(employee);

                return responseMessageUtil.sendResponse(true,
                        APIConstants.RESPONSE_UPDATE_SUCCESS + id, "");
            } else {
                return responseMessageUtil.sendResponse(false, "",
                        "Unable to update employee, Employee not found");
            }
        } catch (Exception e) {
            return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
        }
    }


    public ResponseMessage deleteEmployeeById(Integer id) {
        try {
            Optional<Employee> foundEmployee = employeeRepository.findById(id);

            if (foundEmployee.isPresent()) {

                Employee employee = foundEmployee.get();
                employee.setStatus(APIConstants.STATUS_DELETED);

                employee = employeeRepository.save(employee);

                return responseMessageUtil.sendResponse(true,
                        "Employee Deleted Successfully : " + employee.getFirstName(), "");

            } else {
                return responseMessageUtil.sendResponse(false, "",
                        "Unable to delete employee, Employee not found");
            }

        } catch (Exception e){
            return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
        }
    }

    public Employee findEmployeeById(int id) throws NotFoundException {
        try {
            Optional<Employee> foundAgrochemicalMaster = employeeRepository.findById(id);
            if (foundAgrochemicalMaster.isPresent()) {

                return foundAgrochemicalMaster.get();
            } else {
                throw new NotFoundException("Employee Not Found With ID -> " + id);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
