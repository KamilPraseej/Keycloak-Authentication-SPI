package com.keycloak.authenticatorspi;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomAuthenticatorFactory implements AuthenticatorFactory {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticatorProvider.class);

    public static final String PROVIDER_ID = "custom-authentication-provider";
    private static final CustomAuthenticatorProvider SINGLETON = new CustomAuthenticatorProvider();

    private static List<String> LIST_DATA=List.of("Option 1","Option 2","Option 3","Option 4","Option 5","Option 6");


    @Override
    public String getId() {
        return PROVIDER_ID;
    }
    @Override
    public String getDisplayType() {
        return "Custom Authenticator";
    }

    @Override
    public String getReferenceCategory() {
        return null;
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return new AuthenticationExecutionModel.Requirement[]{
                AuthenticationExecutionModel.Requirement.REQUIRED,
                AuthenticationExecutionModel.Requirement.ALTERNATIVE
        };
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "It is a Help text";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property()
                .name("timer")
                .label("Authentication Time (seconds)")
                .helpText("This field is to stop the authentication time for some times")
                .type(ProviderConfigProperty.STRING_TYPE)
                .defaultValue(10)
                .add()
                .build();
    }



    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
        logger.info("Creating instance of CustomAuthenticatorProvider");
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {
        logger.info("Closing CustomAuthenticatorProvider");
    }


}
