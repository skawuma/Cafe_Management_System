package com.cafe.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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

import com.cafe.dao.UserDao;
import com.cafe.entity.Role;
import com.cafe.entity.User;

@Service
public class JwtUtil {
	@Autowired
	UserDao userDao;
	
	
	 public static final String SECRET ="655468576D5A7134743777217A25432A462D4A614E635266556A586E32723575";


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

	    public Claims extractAllClaims(String token) {
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


	    public String generateToken(String username, String role){
	        Map<String,Object> claims=new HashMap<>();
	        claims.put("role", role);
	        return createToken(claims,username);
	    }

	    public String generateToken1(UserDetails userDetails, Set<Role> role) {

	        Map<String, Object> claims = new HashMap<>();
			
			claims.put("role", role);
			// claims.put ("roles", roles);

	        // return Jwts.builder()
	        //         .setClaims(claims)
	        //         .setSubject(userDetails.getUsername())
	        //         .setIssuedAt(new Date(System.currentTimeMillis()))
	        //         .setExpiration(new Date(System.currentTimeMillis() +1000*60*30))
	        //         .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	   
			return createToken1(claims,userDetails);
		}


		public String generateToken2(UserDetails userDetails) {
			//User user = new User();
			Map<String, Object> claims = new HashMap<>();
        Set<String> Userroles = new HashSet<>();
		//String email = user.getEmail();
        User user = userDao.findByEmailId(userDetails.getUsername());
        for(Role role:user.getRoles2()){
            Userroles.add(role.getRoleName());
        }
        claims.put("role",Userroles.toString().replace("[", "").replace("]", ""));
        return createToken(claims, userDetails.getUsername());
			
		}

		public String createToken1(Map<String, Object> claims, UserDetails userDetails) {

	        

	        return Jwts.builder()
	                .setClaims(claims)
					
	                .setSubject(userDetails.getUsername())
					
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() +1000*60*60*10))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }


	    private String createToken(Map<String, Object> claims, String subject) {
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
	                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	    }

	    private Key getSignKey() {
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
	
	
		public boolean isTokenValid(String token, UserDetails userDetails) {
			final String username = extractUsername(token);
			return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		  }
	

}
