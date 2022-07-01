package microKotikiApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //    @Autowired
//    private JWTFilter jwtFilter;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("u1")
//                .password("p1")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("u2")
//                .password("p2")
//                .authorities("ROLE_USER");
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic()
                .and()
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/admin/adm/**").hasRole("ADMIN")
                .antMatchers("/kotiki/cat/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/kotiki/owner/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/kotiki/**").hasRole("ADMIN")
                .antMatchers("/admin/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN");
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/admin/**").hasRole( "ADMIN")
////                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/admin/register").permitAll()
////                .and().formLogin();
//
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http
////                .httpBasic().disable()
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
//                .authorizeRequests()
//                .antMatchers("/kotiki/cat/*").hasRole("owner")
//                .antMatchers("/admin/register","/auth").permitAll()
//
////                .and()
////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
