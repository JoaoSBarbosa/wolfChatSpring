package com.barbosa.wolfChat.config;

import com.barbosa.wolfChat.controllers.exception.TokenServiceExeption;
import com.barbosa.wolfChat.core.models.security.UserDetailsImpl;
import com.barbosa.wolfChat.core.services.security.UserDetailsServiceImpl;
import com.barbosa.wolfChat.core.services.token.interfaces.TokenService;
import com.barbosa.wolfChat.utils.CommonUtil.CommunUtils;
import com.barbosa.wolfChat.utils.model.StandardError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;

import static com.barbosa.wolfChat.utils.CommonUtil.CommunUtils.AUTHORIZATION_HEADER;
import static com.barbosa.wolfChat.utils.CommonUtil.CommunUtils.BEARER_TOKEN;

@Component
@RequiredArgsConstructor
public class AcessTokenFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final TokenService tokenService;
    private final UserDetailsServiceImpl userDetailsService;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            tryDolFilterInternal(request, response, filterChain);
        }catch (TokenServiceExeption e){

            var status = HttpStatus.UNAUTHORIZED;
            var body = StandardError.builder()
                    .status(status.value())
                    .timestamp(Instant.now())
                    .error(status.getReasonPhrase())
                    .message(e.getLocalizedMessage())
                    .build();

            var json = objectMapper.writeValueAsString(body);
            response.setStatus(status.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }

    }


    private void tryDolFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {
        var token = "";
        var userName = "";

        var authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (isIsPresentToken(authorizationHeader)) {
            token = authorizationHeader.substring(BEARER_TOKEN.length()).trim();
            userName = tokenService.getSubjectFromAccessToken(token);

            // Adiciona um log para verificar o que está vindo
            System.out.println("Token: " + token);
            System.out.println("Username extraído do token: " + userName);

            if (userName == null || userName.isEmpty()) {
                throw new TokenServiceExeption("Token inválido ou expirado");
            }

        }

        if(isIsEmailNotInContent(userName)){
            setAuthentication(request, userName);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, String userName)  {
        var userDetails = userDetailsService.loadUserByUsername(userName);
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // pega as informações da propria requisção e acerscenta detalhes - ex IP
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // adicionando no contexto
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static boolean isIsEmailNotInContent(String userName){
        return userName != null && !userName.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private static boolean isIsPresentToken(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith(BEARER_TOKEN);
    }
}
