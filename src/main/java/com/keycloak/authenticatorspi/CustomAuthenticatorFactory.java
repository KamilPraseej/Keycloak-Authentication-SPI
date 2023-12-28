package com.keycloak.authenticatorspi;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

import java.util.List;

public class CustomAuthenticatorFactory implements AuthenticatorFactory {

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
                .name("name")
                .label("Boolean Type")
                .helpText("This is a boolean type")
                .type(ProviderConfigProperty.BOOLEAN_TYPE)
                .add()
                .property()
                .label("String function")
                .helpText("This is a string function")
                .type(ProviderConfigProperty.STRING_TYPE)
                .add()
                .property()
                .label("List")
                .type(ProviderConfigProperty.LIST_TYPE)
                .options(LIST_DATA)
                .add().build();
    }

    @Override
    public Authenticator create(KeycloakSession keycloakSession) {
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

    }


}
