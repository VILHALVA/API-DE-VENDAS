package com.github.thiagosousagarcia.sistemavendas.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.thiagosousagarcia.sistemavendas.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration-time}")
	private String expirationTime;
	
	@Value("${security.jwt.sign-in-key}")
	private String signInKey;
	
	
	public String generateToken(Usuario usuario){
		long expTime = Long.valueOf(expirationTime);
		LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expTime);
		Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		
		return Jwts.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, signInKey)
				.compact();
	}
	
	public String getUserLogin(String token) throws ExpiredJwtException {
		return getClaims(token).getSubject();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Claims claims = getClaims(token);
			Date expirationDate = claims.getExpiration();
			LocalDateTime expirantionDateTime = expirationDate.toInstant().
													atZone(ZoneId.systemDefault()).toLocalDateTime();
			
			if(LocalDateTime.now().isAfter(expirantionDateTime)) {
				return false;
			}
			
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	
	private Claims getClaims(String token) throws ExpiredJwtException {
		return Jwts.parser()
					.setSigningKey(signInKey)
					.parseClaimsJws(token)
					.getBody();
	}
}
