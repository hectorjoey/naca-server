package fhi360.it.assetverify.config;

import fhi360.it.assetverify.logger.VisitorLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("cors.allowedOrigins")
    private String allowedOrigins;
    private final VisitorLogger visitorLogger;

    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(this.visitorLogger);
    }

    public void addCorsMappings(final CorsRegistry registry) {
        final long MAX_AGE_SECS = 3600L;
        registry.addMapping("/**").allowedOrigins(this.allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").maxAge(3600L);
    }
}
