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
import com.cafe.entity.User;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;

/*
samuelkawuma
5:26:34 PM
Apr 3, 2023
*/
@Component
public class JwtFilter extends OncePerRequestFilter {

;

@Autowired
UserDao userDao;

@Autowired
RoleDao roleDao;

		    @Autowired
		    private JwtUtil jwtUtil;

		

		    @Autowired
		    private CustomerUserDetailService service;

		    //  Claims claims = null;
		     private String userName;
                 Claims claims ;
		    //private String userName;
            String token;

    //     	@Override
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
            
        //    @Override
           protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       
               if (httpServletRequest.getServletPath().matches("/user/login|/user/forgotPassword|/user/signup|/user/login1")) {
                   filterChain.doFilter(httpServletRequest, httpServletResponse);
               } else {
                   String authorizationHeader = httpServletRequest.getHeader("Authorization");
                   //String token  =null;
                  
                   //String userName;
                    token = authorizationHeader.substring(7);
                   if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                       token = authorizationHeader.substring(7);
                       userName = jwtUtil.extractUsername(token);
                       claims = jwtUtil.extractAllClaims(token);
                   }
       
                   if (userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
                       UserDetails userDetails = service.loadUserByUsername(userName);
                       if(jwtUtil.validateToken(token,userDetails)){
                        //if(jwtUtil.isTokenValid(token,userDetails)){
                           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                   new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                           usernamePasswordAuthenticationToken.setDetails(
                                   new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
                           );
                           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                       }
                   }
                   filterChain.doFilter(httpServletRequest, httpServletResponse);
               }
           }


        



		    
// 	@Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if(request.getServletPath().matches("/user/login|/user/forgotPassword|/user/signup|/user/login1")
	   
// 	   )
// 	   {
//          filterChain.doFilter(request,response);
// 	   }
// 	   else{

// 		String authHeader = request.getHeader("Authorization");
//         String token = null;
//         String username = null;
//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             token = authHeader.substring(7);
//             username = jwtUtil.extractUsername(token);
// 			claims = jwtUtil.extractAllClaims(token);
//         }

//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             UserDetails userDetails = service.loadUserByUsername(username);
//             if (jwtUtil.validateToken(token, userDetails)) {
//                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//             }
//         }
//         filterChain.doFilter(request, response);
// 	}
//    }




			
			   public boolean isAdmin(){
            
				return "Admin".equalsIgnoreCase((String) claims.get("role"));
        
			   
			}

			    public boolean isUser(){

		       return "user".equalsIgnoreCase((String) claims.get("role"));
			 	}

			    public String getCurrentUser(){
                    // User user= new  User();
                            
                    // return  user.getEmail();
			       
                   return userName;
			    }
			

            }