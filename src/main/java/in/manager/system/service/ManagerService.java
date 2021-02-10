package in.manager.system.service;


import in.manager.system.constants.ErrorConstants;
import in.manager.system.model.Manager;
import in.manager.system.repository.ManagerRepository;
import in.manager.system.util.APIConstants;
import in.manager.system.util.ResponseMessage;
import in.manager.system.util.ResponseMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ujwal-Gautam
 * @Date 06/02/21
 */

@Service
public class ManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerService.class);

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ResponseMessageUtil responseMessageUtil;

    public ResponseMessage register(Manager manager) {

        try
        {

//            ManagerVo foundManager = managerRepository.findByEmail(manager.getEmail());

            if (!(managerRepository.findManagerByEmail(manager.getEmail()).isPresent())) {
                manager.setUserRole("Manager");
                manager.setPassword(bcryptEncoder.encode(manager.getPassword()));
                managerRepository.save(manager);
                LOGGER.info("Manager saved successfully");
                return responseMessageUtil.sendResponse(true, "Record" + APIConstants.RESPONSE_ADD_SUCCESS,
                        "");
            } else {
                return responseMessageUtil.sendResponse(false, "",
                        "something not found");
            }
        } catch (Exception ex){
            return responseMessageUtil.sendResponse(false, "", "Server Error : " + ex.getMessage());
        }
    }

    public ResponseMessage forgetPassword(String emailId, Manager manager){
        try {
            if (emailId != null){
                Optional<Manager> existingManager = managerRepository.findManagerByEmail(emailId);

                if (!(existingManager.isPresent())){
                    return responseMessageUtil.sendResponse(false, "",
                            "something not found");
                }
                managerRepository.updatePassword(bcryptEncoder.encode(manager.getPassword()), existingManager.get().getId());
                return responseMessageUtil.sendResponse(true, "Record" + APIConstants.RESPONSE_UPDATE_SUCCESS,
                        "");
            } else {
                return responseMessageUtil.sendResponse(false, "",
                        "something not found");
            }

        }catch (Exception e){
            LOGGER.error("exception ---> {}", e.getMessage());
            return responseMessageUtil.sendResponse(false, "", ErrorConstants.INVALID_USER);
        }
    }

}
