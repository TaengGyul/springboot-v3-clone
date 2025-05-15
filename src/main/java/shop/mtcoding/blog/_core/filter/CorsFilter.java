package shop.mtcoding.blog._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String origin = request.getHeader("Origin");
        System.out.println("Origin : " + origin);

        // 나중에는 이렇게 적으면 안 됨, 열어주고 싶은 데이터만 적어야 함
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5500");
//        response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 이 헤더 응답할지 말지
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS"); // Options은 pre-flight 요청 때문에 열어야 함
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Key, Content-Type, Accept, Authorization"); // Exposr Headers와 Allow-Headers의 차이는 Expose-Headers는 응답(Response) 시 브라우저가 읽을 수 있는 헤더를 명시할 때 사용하고, 
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 쿠키의 세션 값 허용

        // Preflight 요청을 허용하고 바로 응답하는 코드
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setStatus(HttpServletResponse.SC_OK); // HttpServletResponse.SC_OK = 200이다.
            response.setStatus(200);
        } else {
            chain.doFilter(req, res);
        }
    }
}