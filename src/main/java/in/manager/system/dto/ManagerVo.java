package in.manager.system.dto;

import java.math.BigInteger;

public interface ManagerVo {
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
