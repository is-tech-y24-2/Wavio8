package restApiKotiki.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import restApiKotiki.models.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static io.jsonwebtoken.lang.Strings.hasText;

//@Component
public class JWTFilter extends GenericFilterBean {
    public static final String AUTHORIZATION="Authorization";

//    @Autowired
    private JwtProvider jwtProvider;

//    @Autowired
    private CustomUserDetailService customUserDetailService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token=getTokenFromRequest((HttpServletRequest) servletRequest);
        if(token!=null && jwtProvider.validateToken(token)){
            String userLogin=jwtProvider.getLoginFromToken(token);
//            Account account=customUserDetailService.loadUserByUsername(userLogin);
            //UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(account,null,account.getAuthorities());
           // SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request){
        String bearer=request.getHeader(AUTHORIZATION);
        if(hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
}
