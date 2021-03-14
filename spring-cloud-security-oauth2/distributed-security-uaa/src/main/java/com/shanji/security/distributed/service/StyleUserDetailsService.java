package com.shanji.security.distributed.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanji.security.distributed.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version: V1.0
 * @className: StyleUserDetailsService
 * @packageName: com.shanji.over.service
 * @data: 2020/12/25 13:18
 * @description:
 */
@Service
public class StyleUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {

        com.shanji.security.distributed.user.entity.User user = userServiceImpl.selectByName(s);

        if(user == null)
        {
            return null; // 查不到数据，统一由provider抛异常
        }

        List<String> authorities = new ArrayList<>();

        user.getRoles().forEach(r -> r.getPerms().forEach(p -> authorities.add(p.getCode())));


        List<String> collect = authorities.stream().distinct().collect(Collectors.toList());
        String[] target = new String[collect.size()];
        collect.toArray(target);

        String userJson = user.getUsername();
        try {

            ObjectMapper mapper = new ObjectMapper();
            userJson = mapper.writeValueAsString(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        UserDetails result = User.withUsername(userJson).password(user.getPassword()).authorities(target).build();
        return result;
    }
}
