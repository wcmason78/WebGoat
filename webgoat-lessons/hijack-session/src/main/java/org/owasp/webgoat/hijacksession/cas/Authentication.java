package org.owasp.webgoat.hijacksession.cas;

import java.security.Principal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Angel Olle Blazquez
 *
 */

@Getter
@Setter
@Builder
public class Authentication implements Principal {

    @Builder.Default
    protected boolean authenticated = false;
    private String name;
    private Object credentials;

    @Override
    public String getName() {
        return name;
    }

    public Object getCredentials() {
        return credentials;
    }

}
