package cn.lz.spring.filter;

import cn.lz.spring.dao.DepartmentDao;
import cn.lz.spring.po.DepartmentPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        DepartmentDao departmentDao = (DepartmentDao) webApplicationContext.getBean("departmentDao");
        DepartmentPo departmentPo = departmentDao.selectByPrimaryKey(1);
        log.info("departmentPo ==> {}", departmentPo);
//        response.getOutputStream().println("hello, filter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
