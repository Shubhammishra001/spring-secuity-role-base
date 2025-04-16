package com.secureCrud.secureCrud;

import java.security.Key;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootApplication
public class SecureCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecureCrudApplication.class, args);
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Generated Key: " + encodedKey);
		System.err.println(" ready to run ");
	}
}
