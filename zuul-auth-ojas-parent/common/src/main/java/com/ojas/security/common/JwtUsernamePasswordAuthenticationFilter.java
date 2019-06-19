package com.ojas.security.common;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Authenticate the request to url /login by POST with json body '{ username,
 * password }'. If successful, response the client with header 'Authorization:
 * Bearer jwt-token'.
 *
 * 
 */
public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final JwtAuthenticationConfig config;
	private final ObjectMapper mapper;

	public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(config.getUrl(), "POST"));
		setAuthenticationManager(authManager);
		this.config = config;
		this.mapper = new ObjectMapper();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
			throws AuthenticationException, IOException {
		User u = mapper.readValue(req.getInputStream(), User.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
			Authentication auth) {
		Instant now = Instant.now();
		String token = Jwts.builder().setSubject(auth.getName())
				.claim("authorities",
						auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
				.signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes()).compact();
		rsp.addHeader(config.getHeader(), config.getPrefix() + " " + token + " " + auth.getAuthorities());
		rsp.setContentType("application/json");
		rsp.setStatus(HttpServletResponse.SC_OK);
		try {
			JSONObject json = new JSONObject();
			String g = "";
//			String newsLetterJSON = mapper.writeValueAsString(auth.getAuthorities());
			
			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				g +=  grantedAuthority.getAuthority() +  " ";
				//System.out.println(g);
			}
			
			// String json = "{" + "\"authorization\":\"Bearer "+token + "}";
			// String json = "{" + "\"authorization\":\"Bearer " + token + "\"}";

			json.put("authorization", "Bearer " + token);
			json.put("authorities", g);
			String res = json.toString();
			rsp.getWriter().write(res);

			// rsp.getWriter().write(json);

			rsp.getWriter().flush();
			rsp.getWriter().close();
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class User {
		private String username, password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	}
}