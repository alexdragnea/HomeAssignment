package com.example.interviewskeleton.config;

import com.example.interviewskeleton.auth.RequestHeaderAuthenticationProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final RequestHeaderAuthenticationProvider requestHeaderAuthenticationProvider;

    @Value("${api.auth.header.name}")
    private String apiAuthHeaderName;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(requestHeaderAuthenticationFilter(), HeaderWriterFilter.class)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.POST, "/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/greet/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()).exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_FORBIDDEN)));

        return http.build();
    }

    @Bean
    public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() {
        RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
        filter.setPrincipalRequestHeader(apiAuthHeaderName);
        filter.setExceptionIfHeaderMissing(false);
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/greet/**"));
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

    @Bean
    protected AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(requestHeaderAuthenticationProvider));
    }
}
