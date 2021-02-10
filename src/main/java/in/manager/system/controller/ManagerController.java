package in.manager.system.controller;

import in.manager.system.model.Manager;
import in.manager.system.service.ManagerService;
import in.manager.system.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ManagerController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
    @Autowired
    ManagerService managerService;

    /**
     * it is registration of manger
     *
     * @param manager
     */
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody Manager manager)
    {
        LOGGER.info("fetching manager value");
        return managerService.register(manager);
    }

    /**
     * it is reset password base on existing email id
     *
     * @param emailId
     * @param manager
     */
    @PutMapping("/forget-password/{emailId}")
    public ResponseMessage forgetPassword(@PathVariable("emailId") String emailId, @RequestBody Manager manager)
    {

        return managerService.forgetPassword(emailId, manager);
    }
}
