package com.zlzl.estate.service.impl;

import com.zlzl.estate.dto.AdminParam;
import com.zlzl.estate.model.Admin;
import com.zlzl.estate.service.AdminService;
import com.zlzl.estate.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl  implements AdminService {
    private static final Logger LOGGER= LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Admin register(AdminParam adminParam) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token=null;
        //登录密码以加密方式传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     */
    public void insertLoginLog(String username){

    }
}
