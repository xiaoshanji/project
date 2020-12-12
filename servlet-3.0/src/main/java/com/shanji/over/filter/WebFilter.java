package com.shanji.over.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @version: V1.0
 * @className: WebFilter
 * @packageName: com.shanji.over.filter
 * @data: 2020/12/12 15:47
 * @description:
 */
@Component
public class WebFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        filterChain.doFilter(request,response);
    }
}
