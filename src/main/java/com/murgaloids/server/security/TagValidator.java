package com.murgaloids.server.security;

/**
 * This class represents the user's response after receiving a challenge.
 * The server will create its own tag and compare it with the one that the
 * user gives.
 */
public class TagValidator {
    private String email;
    private String challenge;
    private String tag;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChallenge() {
        return this.challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}