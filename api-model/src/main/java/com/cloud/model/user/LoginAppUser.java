package com.cloud.model.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * spring security当前登录对象，没有直接映射表的
 */
@Getter
@Setter //UserDetails,springsecurity的要求
public class LoginAppUser extends AppUser implements UserDetails {

	private static final long serialVersionUID = 1753977564987556640L;

	private Set<SysRole> sysRoles; //当前登录用户的角色

	private Set<String> permissions; //当前登录用户的权限

	@JsonIgnore
	@Override //获取权限的方法，其实就是从sysRoles，permissions去拿数据
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		if (!CollectionUtils.isEmpty(sysRoles)) {
			sysRoles.forEach(role -> { //ROLE_也是spring的要求
				if (role.getCode().startsWith("ROLE_")) {
					collection.add(new SimpleGrantedAuthority(role.getCode()));
				} else {
					collection.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
				}
			});
		}

		if (!CollectionUtils.isEmpty(permissions)) {
			permissions.forEach(per -> {
				collection.add(new SimpleGrantedAuthority(per));
			});
		}

		return collection;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getEnabled();
	}
}
