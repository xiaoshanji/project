package com.shanji.security.distributed.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.shanji.security.distributed.util.EncryptUtil;
import com.shanji.security.distributed.util.JsonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: V1.0
 * @className: AuthFilter
 * @packageName: com.shanji.security.distributed.filter
 * @data: 2021/3/12 16:38
 * @description:
 */

/**
 *  token 传递拦截
 */
public class AuthFilter extends ZuulFilter
{
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /**
         * 获取令牌内容
         */
        RequestContext ctx = RequestContext.getCurrentContext();
        //从安全上下文中拿 到用户身份对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof OAuth2Authentication)){  // 无令牌访问资源的情况
            return null;
        }

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        Object principal = userAuthentication.getPrincipal();

        /**
         * 组装明文token
         */
        List<String> authorities = new ArrayList<>();

        userAuthentication.getAuthorities().stream().forEach(s -> authorities.add(s.getAuthority()));


        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String,Object> jsonToekn = new HashMap<>(requestParameters);
        jsonToekn.put("principal",userAuthentication.getName());
        jsonToekn.put("authorities",authorities);

        try
        {
            ctx.addZuulRequestHeader("json-token", EncryptUtil.encodeUTF8StringBase64(JsonUtil.ToJsonStr(jsonToekn)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
