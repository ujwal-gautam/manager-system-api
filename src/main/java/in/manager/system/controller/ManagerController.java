package in.manager.system.controller;

import in.manager.system.model.Manager;
import in.manager.system.service.ManagerService;
import in.manager.system.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:10 AM
 */

@RestController
@RequestMapping("/manager")
@CrossOrigin("*")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/register")
    public ResponseMessage register(@RequestBody Manager manager) {
       return managerService.register(manager);
    }
}
