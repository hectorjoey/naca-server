
package fhi360.it.assetverify.auth;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.security.web.AuthenticationEntryPoint;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
        response.sendError(401, authException.getMessage());
    }
}
