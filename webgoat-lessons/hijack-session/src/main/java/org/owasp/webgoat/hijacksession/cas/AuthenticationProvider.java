package org.owasp.webgoat.hijacksession.cas;

import java.security.Principal;

/**
 *
 * @author Angel Olle Blazquez
 *
 */

@FunctionalInterface
public interface AuthenticationProvider<T extends Principal> {

    T authenticate(T t);

}
