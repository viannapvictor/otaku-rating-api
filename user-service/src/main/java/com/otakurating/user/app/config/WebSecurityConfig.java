package com.otakurating.user.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_MODERATOR = "MODERATOR";
    private static final String ROLE_COMMON = "COMMON";

    private final CookieAuthenticationFilter cookieAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/api/auth/login", "/api/auth/refresh", "/api/auth/logout").permitAll()
                            .requestMatchers("/api/auth/**").authenticated()
                            .requestMatchers("/api/v1/**").hasAnyRole(ROLE_ADMIN, ROLE_MODERATOR, ROLE_COMMON)
                            .requestMatchers("/api/user/**").hasAnyRole(ROLE_ADMIN, ROLE_MODERATOR, ROLE_COMMON)
                            .anyRequest().authenticated();
                })
                .addFilterBefore(cookieAuthenticationFilter, BearerTokenAuthenticationFilter.class)
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new KeycloakJwtConverter()));
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .headers(headers -> {
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
                })
                .build();
    }

    static class KeycloakJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
        private final KeycloakAuthoritiesConverter authoritiesConverter;

        public KeycloakJwtConverter() {
            this.authoritiesConverter = new KeycloakAuthoritiesConverter();
        }

        @Override
        public AbstractAuthenticationToken convert(@NonNull final Jwt jwt) {
            return new JwtAuthenticationToken(jwt, extractAuthorities(jwt), extractPrincipal(jwt));
        }

        private String extractPrincipal(final Jwt jwt) {
            return jwt.getClaimAsString(JwtClaimNames.SUB);
        }

        private Collection<? extends GrantedAuthority> extractAuthorities(final Jwt jwt) {
            return this.authoritiesConverter.convert(jwt);
        }
    }

    static class KeycloakAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        private static final String REALM_ACCESS = "realm_access";
        private static final String ROLES = "roles";
        private static final String RESOURCE_ACCESS = "resource_access";
        private static final String SEPARATOR = "_";
        private static final String ROLE_PREFIX = "ROLE_";

        @Override
        public Collection<GrantedAuthority> convert(@NonNull final Jwt jwt) {
            final var realmRoles = extractRealmRoles(jwt);
            final var resourceRoles = extractResourceRoles(jwt);

            return Stream.concat(realmRoles, resourceRoles)
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.toUpperCase()))
                    .collect(Collectors.toSet());
        }

        private Stream<String> extractResourceRoles(final Jwt jwt) {

            final Function<Map.Entry<String, Object>, Stream<String>> mapResource =
                    resource -> {
                        final String key = resource.getKey();
                        final Map<String, Object> value = (Map<String, Object>)resource.getValue();
                        final Collection<String> roles = (Collection<String>)value.get(ROLES);
                        return roles.stream().map(role -> key.concat(SEPARATOR).concat(role));
                    };

            final Function<Set<Map.Entry<String, Object>>, Collection<String>> mapResources =
                    resources -> resources.stream()
                            .flatMap(mapResource)
                            .toList();

            return Optional.ofNullable(jwt.getClaimAsMap(RESOURCE_ACCESS))
                    .map(Map::entrySet)
                    .map(mapResources)
                    .orElse(Collections.emptyList())
                    .stream();
        }

        private Stream<String> extractRealmRoles(final Jwt jwt) {
            return Optional.ofNullable(jwt.getClaimAsMap(REALM_ACCESS))
                    .map(resource -> (Collection<String>) resource.get(ROLES))
                    .orElse(Collections.emptyList())
                    .stream();
        }
    }
}