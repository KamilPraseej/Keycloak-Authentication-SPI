package com.keycloak.authenticatorspi;

import org.apache.commons.lang.math.NumberUtils;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class CustomAuthenticatorProvider implements Authenticator {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticatorProvider.class);
    private static final int DEFAULT_DELAY_SECONDS = 10;

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String delayInSeconds = Optional.ofNullable(context.getAuthenticatorConfig())
                .map(config -> config.getConfig().get("timer"))
                .orElse(String.valueOf(DEFAULT_DELAY_SECONDS));

        int delay = NumberUtils.toInt(delayInSeconds, DEFAULT_DELAY_SECONDS);

        try {
            TimeUnit.SECONDS.sleep(delay);
            UserModel userModel = Objects.requireNonNull(context.getUser());
            logger.info("Realm Name: "+ context.getRealm().getName());
            logger.info("Session Id: "+ context.getSession().toString());
            logger.info("Email Id: "+ userModel.getEmail());
        } catch (InterruptedException e) {
            logger.error("Thread sleep interrupted", e);
        }
        context.success();
    }

    @Override
    public void action(AuthenticationFlowContext authenticationFlowContext) {

    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
