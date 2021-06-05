package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
//@Component //Spring에서 bean으로 관리되도록 함
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
        //filter단에서는 request와 response를 변경시켜줄 수 있음
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //다 읽어버려서 다른 곳에서 읽지 못하는 문제를 해결하기 위해서 적용 -> 몇 번이고 읽을 수 있도록 해줌
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        //read를 한 번 하면 다른 곳에서는 read를 할 수 없게 됨
        //읽는 것은 spring에서 mapping이 완료된 이후에 진행해야 함 -> doFilter 이후에 읽어야 함
//        BufferedReader br = httpServletRequest.getReader();
//        br.lines().forEach(line -> {
//            log.info("url : {} line : {}",url,line);
//        });

        chain.doFilter(httpServletRequest,httpServletResponse);

        String url = httpServletRequest.getRequestURI();

        //후처리 -> chain.doFilter가 동작하면 response가 만들어짐
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("req url : {}, req body : {}",url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
        //body의 cursor가 끝까지 내려가서 내용이 없는 상황 -> 한 번 더 복사가 필요함
        httpServletResponse.copyBodyToResponse(); //body 내용을 채워넣음

        log.info("res status : {}, response body : {}",httpStatus,resContent);
    }
}
