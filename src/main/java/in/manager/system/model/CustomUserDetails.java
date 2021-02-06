package in.manager.system.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	private Manager manager;

	public CustomUserDetails(final User _user, Manager manager) {
		this.user = _user;
		this.manager = manager;
	}

	public CustomUserDetails() {
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grntdAuths = new HashSet<>();

		if (user != null) {
			grntdAuths = user.getAuthorities();
		}

		return grntdAuths;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		if (this.user == null) {
			return null;
		}
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}

	public Manager getManager() {
		return manager;
	}


	@Override
	public String toString() {
		return "CustomUserDetails [user=" + user + "]";
	}

}