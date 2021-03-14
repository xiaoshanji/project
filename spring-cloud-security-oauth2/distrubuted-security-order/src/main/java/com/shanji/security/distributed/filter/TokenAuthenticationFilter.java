package com.shanji.security.distributed.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanji.security.distributed.util.EncryptUtil;
import com.shanji.security.distributed.util.JsonUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @version: V1.0
 * @className: TokenAuthenticationFilter
 * @packageName: com.shanji.security.distributed.filter
 * @data: 2021/3/14 11:03
 * @description:
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

        String header = request.getHeader("json-token");
        if(!StringUtils.isEmpty(header))
        {
            String json = EncryptUtil.decodeUTF8StringBase64(header);
            JsonNode jsonNode = null;
            try
            {
                jsonNode = JsonUtil.ToJsonNode(json);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if(jsonNode != null)
            {


                String userJson = jsonNode.get("principal").asText();
                String[] authorities = new String[jsonNode.get("authorities").size()];
                for(int i = 0,len = authorities.length ; i < len ; i++)
                {
                    authorities[i] = jsonNode.get("authorities").get(i).asText();
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userJson,null, AuthorityUtils.createAuthorityList(authorities));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);
    }
}
