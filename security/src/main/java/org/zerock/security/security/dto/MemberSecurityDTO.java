package org.zerock.security.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSecurityDTO  extends User {
	private String mid;
	private String mpw;
	private String email;
	private boolean del;
	private boolean social;

	private Map<String, Object> props;

	public MemberSecurityDTO(String username, String password, String email, boolean del, boolean social, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);

		this.mid = username;
		this.mpw = password;
		this.email = email;
		this.del = del;
		this.social = social;
	}


}
