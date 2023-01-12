package com.hunglp.employeeservice.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            HttpHeaders headers = new HttpHeaders();


            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("auth", servletRequest.getHeader("Authorization").toString().split("")[1]);
            HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//            map.add("auth", httpRequest.getHeader("Authorization").split(" ")[1]);
//            HttpHeaders headers = createHttpHeaders("fred","1234");
//            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Authorization", "Bearer " + httpRequest.getHeader("Authorization").split(" ")[1]);
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<?> response =
                    restTemplate.exchange("http://localhost:8083/auth/authenticate",
                            HttpMethod.POST,
                            entity,Object.class);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        try {
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("auth", httpServletRequest.getHeader("Authorization").toString().split("")[1]);
//
//            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
//            ResponseEntity<?> response =
//                    restTemplate.exchange("http://localhost:8080/auth/authenticate",
//                            HttpMethod.POST,
//                            entity,Object.class);
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//
//    }
}
