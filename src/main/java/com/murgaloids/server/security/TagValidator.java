package com.murgaloids.server.security;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the user's response after receiving a challenge.
 * The server will create its own tag and compare it with the one that the
 * user gives.
 */
public class TagValidator {
    @Getter @Setter private String email;
    @Getter @Setter private String challenge;
    @Getter @Setter private String tag;
}