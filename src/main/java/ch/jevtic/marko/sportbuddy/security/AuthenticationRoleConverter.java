package ch.jevtic.marko.sportbuddy.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthenticationRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    public AuthenticationRoleConverter() {
        defaultGrantedAuthoritiesConverter.setAuthoritiesClaimName("realm_access.roles");
        defaultGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
    }

    private static Collection<? extends GrantedAuthority> extractRealmRoles(final Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            Collection<String> roles = (Collection<String>) realmAccess.get("roles");
            if (roles != null) {
                return roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // Add "ROLE_" prefix here
                        .collect(Collectors.toSet());
            }
        }
        return Collections.emptySet();
    }
    
    
    
    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null) {
            Map<String, Object> sportbuddyAccess = (Map<String, Object>) resourceAccess.get("sportbuddy");
            if (sportbuddyAccess != null) {
                Collection<String> roles = (Collection<String>) sportbuddyAccess.get("roles");
                if (roles != null) {
                    return roles.stream()
                            .map(SimpleGrantedAuthority::new)  // No "ROLE_" prefix
                            .collect(Collectors.toSet());
                }
            }
        }
        return Collections.emptySet();
    }
    
    

    @Override
    public AbstractAuthenticationToken convert(final Jwt source) {
        // Extract roles from both the realm and resource access claims
        Collection<GrantedAuthority> authorities = Stream.concat(
                defaultGrantedAuthoritiesConverter.convert(source).stream(),
                Stream.concat(
                        extractRealmRoles(source).stream(),
                        extractResourceRoles(source).stream()
                )
        ).collect(Collectors.toSet());

        // Return the JwtAuthenticationToken with the collected authorities
        return new JwtAuthenticationToken(source, authorities);
    }
}
