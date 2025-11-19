package com.aye.mobileservice.securityfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class SessionFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session invalid or expired");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
