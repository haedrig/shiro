package com.haedrig.shiro.spring;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.haedrig.entity.Resource;
import com.haedrig.entity.Role;
import com.haedrig.entity.User;
import com.haedrig.service.IUserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUser(username);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		return new SimpleAuthenticationInfo(user.getAccount(), // 用户名
				user.getPassword(), // 密码
				getName() // realm name
		);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = userService.getUser(username);
		Set<Role> uroles = user.getRoles();
		Set<String> perms = new HashSet<String>();
		for (Role role : uroles) {
			Set<Resource> resources = role.getResources();
			for (Resource resource : resources) {
				Object permission = resource.getPerms();
				if (permission == null
						|| StringUtils.isEmpty(permission.toString())) {
					continue;
				}
				perms.add(StringUtils.substringBetween(permission.toString(),
						"perms[", "]"));
			}
		}
		authorizationInfo.setStringPermissions(perms);
		return authorizationInfo;
	}
}
