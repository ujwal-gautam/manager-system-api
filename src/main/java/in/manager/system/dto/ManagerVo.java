package in.manager.system.dto;

import java.math.BigInteger;

/**
 * @author cropdata-ujwal
 * @package in.manager.system.dto
 * @date 06/02/21
 * @time 5:47 PM
 */
public interface ManagerVo
{
    Integer getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPassword();

    BigInteger getMobile();

    String getStatus();

    String getUserRole();

    String getAddress();

    String getToken();
}
