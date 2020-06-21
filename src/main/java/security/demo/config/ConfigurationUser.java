package security.demo.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
@RequiredArgsConstructor
public class ConfigurationUser extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception{
        managerBuilder
                .inMemoryAuthentication()
                .withUser("user")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("USER","ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().and()
                .authorizeRequests()
                .antMatchers("users/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
                /*.and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);*/
    }
    
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(12);
    }
}
