package com.sailing.springbootmybatis.filter;

import com.alibaba.fastjson.JSON;
import com.sailing.springbootmybatis.common.response.ResponseEnum;
import com.sailing.springbootmybatis.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.filter
 * @Description: 验证jwt的过滤器
 * @date 2018/12/6 16：56
 */
public class JWTParseFilter implements Filter{

    @Autowired
    private TokenService tokenService;

    //不需要过滤的路径(比如:注册登录等)
    private String[] includeUrls = new String[]{"/yjbj/login","/yjbj/register","/yjbj/token/getToken"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化完成。。。");
        //下面这行解决filter中无法使用spring @Autowired 报空指针的问题
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        boolean needFilter = needFilter(uri);
        if(!needFilter){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            String jwt = request.getHeader("Authorization");
            Map result = new HashMap();
            if("".equals(jwt) || jwt==null){
                result.put("code", ResponseEnum.NOT_LOGIN.getCode());
                result.put("message", ResponseEnum.NOT_LOGIN.getMessage());
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.print(JSON.toJSONString(result));
                writer.flush();
                writer.close();
            }else{
                try {
                    tokenService.parseJWT(jwt);
                    // if no exceptions then wo can truly trust the jwt
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (ExpiredJwtException e) {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    result.put("code", ResponseEnum.TOKEN_EXPIRED.getCode());
                    result.put("message", ResponseEnum.TOKEN_EXPIRED.getMessage());
                    writer.print(JSON.toJSONString(result));
                    writer.flush();
                    writer.close();
                } catch (JwtException e){
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    result.put("code", ResponseEnum.REQUEST_ILLEGAL.getCode());
                    result.put("message", ResponseEnum.REQUEST_ILLEGAL.getMessage());
                    writer.print(JSON.toJSONString(result));
                    writer.flush();
                    writer.close();
                }
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁完成。。。");
    }

    /**
     * 判断是否需要过滤器过滤
     * @param uri
     * @return
     */
    public boolean needFilter(String uri){
        for (String listUri:
                includeUrls) {
            if(listUri.equals(uri)){
                return false;
            }
        }
        return true;
    }
}
