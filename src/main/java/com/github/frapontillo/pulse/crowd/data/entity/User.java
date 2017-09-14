package com.github.frapontillo.pulse.crowd.data.entity;

/**
 * Users can register through some {@link App} and access Crowd Pulse data.
 *
 * @author Francesco
 */
public class User extends Entity {
    private String username;
    private String email;
    private String secret;

    /**
     * Get the username of the User.
     *
     * @return The username of the User.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set a new username of the User.
     *
     * @param username The new username of the User.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the email of the User.
     *
     * @return The email of the User.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Change the email of the User.
     *
     * @param email The new email of the User.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the secret of the User (hashed/salted password).
     *
     * @return The secret of the User.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Change the secret of the User.
     *
     * @param secret The new secret of the User.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }
}
