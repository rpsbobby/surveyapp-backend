package com.surver.app.backend.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspect) {
        return new MvcRequestMatcher.Builder(introspect);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, MvcRequestMatcher.Builder mvc) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(config -> {
                    config.configurationSource(corsConfigurationSource);
                })
                .authorizeHttpRequests(config -> {

                    config.requestMatchers(
                            mvc.pattern("/api/auth/**"),
                            mvc.pattern("/api/survey/**")
                    ).permitAll();
                    config.requestMatchers(
                            mvc.pattern("/api/survey/delete/{surveyId}"),
                            mvc.pattern("/api/survey/delete-question/{questionId}/survey/{surveyId}"),
                            mvc.pattern("/api/survey/add"),
                            mvc.pattern("/api/survey/getAllByCreator")
                    ).authenticated();
                    config.anyRequest().authenticated();
                })
                .sessionManagement(config -> {
                    config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
