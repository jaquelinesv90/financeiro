package com.financeiro.api.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.financeiro.api.model.Usuario;
import com.financeiro.api.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticationService {
	
	//VALIDADE DO TOKEN 2 DIAS
	private static final long EXPIRATION_TIME = 172800000;
	
	private static final String SECRET = "123";
	
	//prefixo padrão de token
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	public void addAuthentication(HttpServletResponse response,String username) throws Exception {
		
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		response.addHeader(HEADER_STRING, token);
		
		response.getWriter().write("{\"Authorization\": \""+token+"\"}");
		
		
	}
	
	
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null) {
			
			// Faz a validação do token do usuário na requisição
			String user = Jwts.parser().setSigningKey(SECRET)
							  .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
							  .getBody().getSubject();
			if(user != null) {
				Usuario usuarioConsulta = ApplicationContextLoad.getApplicationContext()
						.getBean(UsuarioRepository.class).findUserByLogin(user);
				if(usuarioConsulta != null) {
					return new UsernamePasswordAuthenticationToken(
							usuarioConsulta.getNome(),
							usuarioConsulta.getSenha());
				}
			}
		}
		return null;
	}
}
