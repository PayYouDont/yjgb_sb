package com.gospell.chitong.rdcenter.broadcast.complexManage.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher{
	@Override  
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {  
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String tokenCredentials = String.valueOf(token.getPassword());  
        String accountCredentials = info.getCredentials().toString();
		return tokenCredentials.equals(accountCredentials);
	}
}
