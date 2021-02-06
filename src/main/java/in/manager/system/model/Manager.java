package in.manager.system.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:31 AM
 */
@Data
@Entity(name = "manager")
public class Manager
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "address")
	private String address;

	@Column(name = "mobile")
	private BigInteger mobile;

	@Column(name = "user_role")
	private String userRole;

	@Column(name = "status")
	private String status;

	@Transient
	private String token;
}

