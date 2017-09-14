package com.github.frapontillo.pulse.crowd.data.entity;

import java.util.List;

/**
 * Model for applications that can handle the data in the database.
 *
 * @author Francesco Pontillo
 */
public class App extends Entity {
    private String name;
    private String secret;
    private String redirectUri;
    private List<String> allowedGrants;

    /**
     * Get the name of the App.
     *
     * @return The name of the App.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the App.
     *
     * @param name The name of the App.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the secret code of the App.
     *
     * @return The secret of the App.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Set the secret code of the App.
     *
     * @param secret The secret of the App.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Get the URL that will be used by the App to redirect an authenticated {@link User} to.
     *
     * @return The OAuth redirect URL for the App.
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * Set the URL that will be used by the App to redirect an authenticated {@link User} to.
     *
     * @param redirectUri The OAuth redirect URL for the App.
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    /**
     * Get a {@link List} of grants that the App is associated to.
     *
     * @return The {@link List} of grants as {@link String}s.
     */
    public List<String> getAllowedGrants() {
        return allowedGrants;
    }

    /**
     * Set a new {@link List} of grants that the App is associated to.
     *
     * @param allowedGrants The {@link List} of grants as {@link String}s.
     */
    public void setAllowedGrants(List<String> allowedGrants) {
        this.allowedGrants = allowedGrants;
    }
}
