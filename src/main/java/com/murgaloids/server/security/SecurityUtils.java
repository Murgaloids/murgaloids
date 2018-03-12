package com.murgaloids.server.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Hex;
import java.util.Date;

/**
 * This class provides utilities for anything related to security. It also hold
 * information about specific public endpoints and how long each JWT can last
 * for.
 */
public class SecurityUtils {
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String SECRET = Secrets.SHHHHHH;
    public static final String TOKEN_PREFIX = "Murgaloids ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/students/add";
    public static final String GET_CHALLENGE_URL = "/students/get-challenge";
    public static final String VALIDATE_TAG_URL = "/students/validate-tag";

    public static String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
            .compact();
    }

    public static String generateTag(String challenge, String password) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(password.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(challenge.getBytes("UTF-8"))));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}