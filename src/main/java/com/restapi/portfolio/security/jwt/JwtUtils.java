
package com.restapi.portfolio.security.jwt;

import com.restapi.portfolio.security.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author "Fausto Stradiotto"
 */
@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${app.jwtSecret}")
	private String jwtSecret;
	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
                }catch (SignatureException e) {
                        logger.error("Error en la firma: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Token Inválido: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("Token Expirado: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("Tolen no soportado: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("Token vacío: {}", e.getMessage());
		}
		return false;
	}
}