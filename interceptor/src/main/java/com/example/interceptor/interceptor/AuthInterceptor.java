package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    //controller 실행 이전에 수행
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();
        //권한 check 진행
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 조건 : 서버는 public으로 동작하는데 단 Auth를 가진 요청에 대해서는 세션, 쿠키를 확인할 것
        if(hasAnnotation){
            //권한 check
            String query = uri.getQuery();
            log.info(query);
            if(query.equals("kang")){
                return true;
            }
            throw new AuthException();
        }
        //false일 경우 동작하지 않음 -> interceptor를 넘어 controller로 넘어가지 못함
        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        //resource에 대한 요청을 통과
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }
        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            //Auth annotation이 있을 때는 true
            return true;
        }
        return false;
    }
}
