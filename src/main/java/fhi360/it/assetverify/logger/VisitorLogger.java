//
//package fhi360.it.assetverify.logger;
//
//import fhi360.it.assetverify.model.Visitor;
//import fhi360.it.assetverify.service.VisitorService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.time.LocalDateTime;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class VisitorLogger implements HandlerInterceptor {
//    private final VisitorService visitorService;
//
//    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
//        final String ip = HttpRequestResponseUtils.getClientIpAddress();
//        final String url = HttpRequestResponseUtils.getRequestUrl();
//        final String page = HttpRequestResponseUtils.getRequestUri();
//        final String userAgent = HttpRequestResponseUtils.getUserAgent();
//        final String requestMethod = HttpRequestResponseUtils.getRequestMethod();
//        final LocalDateTime timestamp = LocalDateTime.now();
//        final Visitor visitor = new Visitor();
//        visitor.setIp(ip);
//        visitor.setMethod(requestMethod);
//        visitor.setUrl(url);
//        visitor.setPage(page);
//        visitor.setUserAgent(userAgent);
//        visitor.setLoggedTime(timestamp);
//        visitor.setStatus(response.getStatus());
//        visitor.setUniqueVisit(true);
//        this.visitorService.saveVisitorInfo(visitor);
//        return true;
//    }
//}
