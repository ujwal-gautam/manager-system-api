package in.manager.system.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:31 AM
 */

@Entity(name = "employee")
@Data
public class Employee
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "mobile")
	private BigInteger mobile;

	@Column(name = "status")
	private String status;
}
