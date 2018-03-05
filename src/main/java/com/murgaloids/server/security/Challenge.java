package com.murgaloids.server.security;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a challenge object that is given to the user to
 * validate their credentials.
 */
public class Challenge {
    @Getter @Setter private String salt;
    @Getter @Setter private String challenge;

    public Challenge(String salt) {
        this.salt = salt;
        challenge = RandomStringUtils.randomAlphabetic(100);
    }
}