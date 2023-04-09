package com.cafe.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
/*
samuelkawuma
5:20:22 PM
Apr 3, 2023
*/

import org.springframework.stereotype.Service;

import com.cafe.entity.Role;

@Service
public class JwtUtil {
	
	
	 public static final String SECRET = "3272357538782F413F4428472B4B6250655368566D5971337336763979244226";


	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(getSignKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }


	    public String generateToken(String userName, Set<Role> role){
	        Map<String,Object> claims=new HashMap<>();
	        claims.put("role", role);
	        return createToken(claims,userName);
	    }

	    public String generateToken1(UserDetails userDetails) {

	        Map<String, Object> claims = new HashMap<>();

	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() +1000*60*30))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }


	    private String createToken(Map<String, Object> claims, String userName) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(userName)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }

	    private Key getSignKey() {
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
	
	
	

}
