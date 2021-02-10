package in.manager.system.repository;

import in.manager.system.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:31 AM
 */

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer>
{

//    @Query(value = "select id, email, password, mobile, status, user_role as userRole, address from manager where email = ?1", nativeQuery = true)
//    ManagerVo findByEmail(String email);

    Optional<Manager> findManagerByEmail(String emailId);

    @Transactional
    @Modifying
    @Query(value = "update manager set password =?1 where id =?2", nativeQuery = true)
    public int updatePassword(String password, Integer id);

}
