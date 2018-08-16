package com.wp.myboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//其中@Order注解表示执行过滤顺序，值越小，越先执行
@Order(1)
@WebFilter(filterName = "gooSeFilter",urlPatterns = "/*")
public class GooSeFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(GooSeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("GooSeFilter>>>doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
