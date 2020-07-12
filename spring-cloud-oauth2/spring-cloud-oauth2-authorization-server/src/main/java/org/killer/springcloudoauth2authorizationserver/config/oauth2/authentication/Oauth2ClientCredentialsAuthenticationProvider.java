package org.killer.springcloudoauth2authorizationserver.config.oauth2.authentication;

import com.nimbusds.oauth2.sdk.auth.verifier.InvalidClientException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author killer
 * @date 2020/07/11 - 11:46
 */
public class Oauth2ClientCredentialsAuthenticationProvider implements AuthenticationProvider {

    private ClientDetailsService clientDetailsService;

    public Oauth2ClientCredentialsAuthenticationProvider(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(String.valueOf(token.getPrincipal()));

        if(clientDetails != null) {
            // 校验clientSecret
            String clientSecret = clientDetails.getClientSecret();

        } else {
            throw new OAuth2AuthenticationException(new OAuth2Error("100", "client id not exists", ""));
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // UsernamePasswordAuthenticationToken 是否能够被authentication赋值，，
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
