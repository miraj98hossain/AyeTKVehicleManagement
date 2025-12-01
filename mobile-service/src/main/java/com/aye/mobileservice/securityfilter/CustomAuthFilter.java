//package com.aye.mobileservice.securityfilter;
//
//import com.aye.mobileservice.service.UserService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.constraints.NotNull;
//import lombok.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//
//public class CustomAuthFilter extends OncePerRequestFilter {
//
//    private UserService userService;
//
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    protected void doFilterInternal(@NotNull HttpServletRequest request,
//                                    @NonNull HttpServletResponse response,
//                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Basic ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            String base64Credentials = authHeader.substring(6);
//            String decoded = new String(java.util.Base64.getDecoder().decode(base64Credentials));
//
//            String[] values = decoded.split(":", 2);
//            String username = values[0];
//            String password = values[1];
//
//            UserDetails userDetails = userService.loadUserByUsername(username);
//
//            if (passwordEncoder.matches(password, userDetails.getPassword())) {
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
//
//
//
