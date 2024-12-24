package com.aurionpro.loan.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.aurionpro.loan.exceptions.UserException;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;

	public String generateToken(Authentication authentication) {

		String username = authentication.getName();

		Date currentDate = new Date();

		Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

		String token = Jwts.builder().claims().subject(username).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(expireDate).and().signWith(key()).claim("role", authentication.getAuthorities()).compact();

//		        
		return token;
	}

	private SecretKey key() {

		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}
	
	public String getUsername(String token){
        Claims claims = Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();     

        String username = claims.getSubject();
     
        return username;
    }
	public boolean validateToken(String token){
        try{
          Jwts.parser().verifyWith(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new UserException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new UserException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new UserException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new UserException("JWT claims string is empty.");
        }
        catch(Exception e)
        {
          throw new UserException("Invalid Credentials");
        }
    }

	
}
