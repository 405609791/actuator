package fun.codefarmer.actuator.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ @ClassName SecurityConfig
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/23 17:18
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //如果连数据库，和security配置是一样的。加user。注入userService
    /**
     * 主要配置这里的
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeRequests()
                .anyRequest().hasRole("admin")
                .and()
                .httpBasic();
    }
}
