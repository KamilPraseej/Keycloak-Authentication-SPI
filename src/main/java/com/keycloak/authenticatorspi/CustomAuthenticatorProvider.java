package com.keycloak.authenticatorspi;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomAuthenticatorProvider implements Authenticator {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticatorProvider.class);
    @Override
    public void authenticate(AuthenticationFlowContext context) {
            int delay;
            String delayInSeconds = context.getAuthenticatorConfig().getConfig().get("timer");

            try{
                delay = Integer.parseInt(delayInSeconds);
            }
            catch (NumberFormatException | NullPointerException e){
                 delay = 10;
            }

            logger.info("Realm Name: " + context.getRealm().getName());
            logger.info("Session Id: " + context.getSession().toString());
            UserModel userModel = context.getUser();
            logger.info("Email Id: " + userModel.getEmail());
            logger.info("First Name: " + userModel.getFirstName());
            logger.info("Last Name: " + userModel.getLastName());

            try {
                Thread.sleep(delay * 1000L);
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
