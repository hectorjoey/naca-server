//
//package fhi360.it.assetverify.logger;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import fhi360.it.assetverify.model.Users;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@Slf4j
//public final class HttpRequestResponseUtils {
//    private static final String[] IP_HEADER_CANDIDATES;
//
//    private HttpRequestResponseUtils() {
//    }
//
//    public static String getClientIpAddress() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "0.0.0.0";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        for (final String header : HttpRequestResponseUtils.IP_HEADER_CANDIDATES) {
//            final String ipList = request.getHeader(header);
//            if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
//                final String ip = ipList.split(",")[0];
//                return ip;
//            }
//        }
//        return request.getRemoteAddr();
//    }
//
//    public static String getRequestUrl() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return request.getRequestURL().toString();
//    }
//
//    public static String getRequestUri() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return request.getRequestURI();
//    }
//
//    public static String getRefererPage() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        final String referer = request.getHeader("Referer");
//        return (referer != null) ? referer : request.getHeader("referer");
//    }
//
//    public static String getPageQueryString() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return request.getQueryString();
//    }
//
//    public static String getUserAgent() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        final String userAgent = request.getHeader("User-Agent");
//        return (userAgent != null) ? userAgent : request.getHeader("user-agent");
//    }
//
//    public static String getRequestMethod() {
//        if (RequestContextHolder.getRequestAttributes() == null) {
//            return "";
//        }
//        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return request.getMethod();
//    }
//
//    public static String getLoggedInUser() {
//        String userJson = String.valueOf(new Users());
//        if (SecurityContextHolder.getContext().getAuthentication() != null &&
//                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
//                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
//            final Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            final ObjectMapper mapper = new ObjectMapper();
//            try {
//                userJson = mapper.writeValueAsString((Object) user);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            log.debug("userJson::{}", userJson);
//            return userJson;
//        }
//        return userJson;
//    }
//
//    static {
//        IP_HEADER_CANDIDATES = new String[]{"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};
//    }
//}
