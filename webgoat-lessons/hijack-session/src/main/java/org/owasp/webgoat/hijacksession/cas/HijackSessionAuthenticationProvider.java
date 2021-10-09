package org.owasp.webgoat.hijacksession.cas;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.function.DoublePredicate;
import java.util.function.Supplier;

import org.joda.time.Instant;

/**
 *
 * @author Angel Olle Blazquez
 *
 */

// weak id value and mechanism
public class HijackSessionAuthenticationProvider implements AuthenticationProvider<Authentication> {

    private Queue<String> sessions = new LinkedList<>();
    private static long id = new Random().nextLong();
    private static final int MAX = 150;

    protected static final DoublePredicate PROBABILITY_DOUBLE_PREDICATE = pr -> pr < 0.75;
    protected static final Supplier<Authentication> AUTHENTICATION_SUPPLIER = () -> Authentication
        .builder()
        .credentials(generateSessionId())
        .build();

    @Override
    public Authentication authenticate(Authentication authentication) {
        if (authentication == null) {
            authorizedUserAutoLogin();
            return AUTHENTICATION_SUPPLIER.get();
        }

        if (sessions.contains(authentication.getCredentials())) {
            authentication.authenticated = true;
        }

        return authentication;
    }

    private void authorizedUserAutoLogin() {

    }

    private static String generateSessionId() {
        return ++id + "-" + Instant.now();
    }

    private void cleanOldSessions() {
        if (sessions.size() > MAX) {
            sessions.remove();
        }
    }

}
