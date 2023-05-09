package com.cafe.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cafe.dao.RoleDao;
import com.cafe.dao.UserDao;
import com.cafe.entity.Role;
import com.cafe.entity.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
/*
samuelkawuma
5:26:34 PM
Apr 3, 2023
*/
@Component
public class JwtFilter extends OncePerRequestFilter {

;
@Autowired
RoleDao roleDao;

		    @Autowired
		    private JwtUtil jwtUtil;

		

		    @Autowired
		    private CustomerUserDetailService service;

		     Claims claims = null;
		    private String userName = null;



            // @Override
            // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
            //     final String requestTokenHeader = request.getHeader("Authorization");
        
            //     String username = null;
            //     String jwtToken = null;
        
            //     if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            //         jwtToken = requestTokenHeader.substring(7);
            //         try {
            //             username = jwtUtil.extractUsername(jwtToken);
            //         } catch (IllegalArgumentException e) {
            //             System.out.println("Unable to get JWT Token");
            //         } catch (ExpiredJwtException e) {
            //             System.out.println("JWT Token has expired");
            //         }
            //     } else {
            //         System.out.println("JWT token does not start with Bearer");
            //     }
        
            //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        
            //         UserDetails userDetails = service.loadUserByUsername(username);
        
            //         if (jwtUtil.validateToken(jwtToken, userDetails)) {
        
            //             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            //         }
            //     }
            //     filterChain.doFilter(request, response);
        
            // }
        



     


//|/user/get|/user/getall
		    
			@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       if(request.getServletPath().matches("/user/login|/user/forgotPassword|/user/signup|/user/login1")
	   
	   )
	   {
         filterChain.doFilter(request,response);
	   }
	   else{

		String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
			//claims = jwtUtil.extractAllClaims(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(username);
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
	}
   }


//    @Override
//    protected void doFilterInternal(
//        @NonNull HttpServletRequest request,
//        @NonNull HttpServletResponse response,
//        @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//      if (request.getServletPath().contains(("/user/login|/user/forgotPassword|/user/signup|/user/login1|/user/get|/user/getall"))) {
//        filterChain.doFilter(request, response);
//        return;
//      }
//      final String authHeader = request.getHeader("Authorization");
//      final String jwt;
//      final String userEmail;
//      String token = null;
//      if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//        filterChain.doFilter(request, response);
//        return;
//      }
//      jwt = authHeader.substring(7);
//      userEmail = jwtUtil.extractUsername(jwt);
//      if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        UserDetails userDetails = service.loadUserByUsername(userEmail);
//     //    var isTokenValid = tokenRepository.findByToken(jwt)
//     //        .map(t -> !t.isExpired() && !t.isRevoked())
//     //        .orElse(false);
//     if (jwtUtil.validateToken(token, userDetails)) {
//          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//              userDetails,
//              null,
//              userDetails.getAuthorities()
//          );
//          authToken.setDetails(
//              new WebAuthenticationDetailsSource().buildDetails(request)
//          );
//          SecurityContextHolder.getContext().setAuthentication(authToken);
//        }
//      }
//      filterChain.doFilter(request, response);
//    }



	// @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //     String authHeader = request.getHeader("Authorization");
    //     String token = null;
    //     String username = null;
    //     if (authHeader != null && authHeader.startsWith("Bearer ")) {
    //         token = authHeader.substring(7);
    //         username = jwtUtil.extractUsername(token);
    //     }

    //     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    //         UserDetails userDetails = service.loadUserByUsername(username);
    //         if (jwtUtil.validateToken(token, userDetails)) {
    //             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    //             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //             SecurityContextHolder.getContext().setAuthentication(authToken);
    //         }
    //     }
    //     filterChain.doFilter(request, response);
    // }



			
			
			   public boolean isAdmin(){
               // User user = new User();
               // String password = user.getPassword();
              // Role role = roleDao.findById("Admin").get();
				//return "Admin".equalsIgnoreCase((String) claims.get(role.getRoleName()));
				return "Admin".equalsIgnoreCase((String) claims.get("roles"));
                // User user = new User();
				// Role role = roleDao.findById("Admin").get();
				// Set<Role> userRoles = new HashSet<>();
				// userRoles.add(role);
				// user.setRole(userRoles);
				// return "admin".equalsIgnoreCase((String) claims.get(userRoles));
         
				//return "admin".equalsIgnoreCase((String) claims.get(role.getRoleName()));
				//return "admin".equalsIgnoreCase((String) claims.get(role));
			   
			}

			//     public boolean isUser(){
            //     //    return role.getRoleName() claims.get("role");
			// 		// Role role = roleDao.findById("User").get();
			//       return "user".equalsIgnoreCase((String) claims.get("roles"));
			// 	}

			    public String getCurrentUser(){
			        return userName;
			    }
			
}
