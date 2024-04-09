package group.Food_order_bachelor.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import group.Food_order_bachelor.enums.User_role;
import group.Food_order_bachelor.service.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("OPTIONS","GET","POST","DELETE","PUT","PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/**", configuration);
        return source;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .requestMatchers("/api/v1/resource/**").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("/api/v1/allergen/get-allergens").hasAnyAuthority(User_role.MANAGER.name(),User_role.CUSTOMER.name(),User_role.ADMIN.name())
//                        .requestMatchers("/api/v1/allergen/**").permitAll()
                        .requestMatchers("/api/v1/ingredient").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("/api/v1/food/**").hasAnyAuthority(User_role.MANAGER.name(),User_role.CUSTOMER.name(),User_role.DELIVERER.name())
                        .requestMatchers("/api/v1/menu/get-menus").hasAnyAuthority(User_role.CUSTOMER.name()
                                ,User_role.MANAGER.name(),User_role.ADMIN.name(),User_role.DELIVERER.name())
                        .requestMatchers("api/v1/menu/create-menu").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/menu/remove-menu").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/restaurant/create-restaurant").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/restaurant/get-restaurants").permitAll()
                        .requestMatchers("api/v1/restaurant/edit-restaurant").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/restaurant/change-restaurant-status").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/loyalty/create-loyalty").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/order/create-order").hasAuthority(User_role.CUSTOMER.name())
                        .requestMatchers("api/v1/stripe/card/token").hasAuthority(User_role.CUSTOMER.name())
                        .requestMatchers("api/v1/stripe/charge").hasAuthority(User_role.CUSTOMER.name())
                        .requestMatchers("api/v1/restaurant/get-ready-orders-for-deliverer").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/order/take-order").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/order/get-orders-for-customer-initial").hasAuthority(User_role.CUSTOMER.name())
                        .requestMatchers("api/v1/order/get-orders-for-customer-history").hasAuthority(User_role.CUSTOMER.name())
                        .requestMatchers("api/v1/order/get-orders-for-deliverer-initial").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/order/get-orders-for-deliverer-taken").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/order/get-orders-for-deliverer-in-delivery").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/order/get-orders-for-deliverer-history").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/order/get-orders-for-restaurant-history").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/message-for-admin/create-message").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/message-for-admin/get-messages").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/message-for-admin/review-message").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/ingredient/delete-ingredient").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/order/get-food-statistics").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/loyalty/get-loyalties-for-manager").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/food/fetch-drinks-for-loyalty").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/menu/get-menus-for-manager").hasAuthority(User_role.MANAGER.name())
                        .requestMatchers("api/v1/user/get-available-slots").hasAuthority(User_role.DELIVERER.name())
                        .requestMatchers("api/v1/food/get-foods-by-restaurant-id").permitAll()
                        .requestMatchers("api/v1/food/get-foods-by-user-id").hasAnyAuthority(User_role.MANAGER.name(),User_role.DELIVERER.name())
                        .requestMatchers("api/v1/user/create-manager-or-deliverer").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/user/add-manager-or-deliverer-to-restaurant").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/user/get-deliverers-and-managers").hasAuthority(User_role.ADMIN.name())
                        .requestMatchers("api/v1/image/upload-image").permitAll()
                        .requestMatchers("api/v1/image/get-image-test").permitAll()
                        .requestMatchers("/api/v1/restaurant/get-restaurant").permitAll()
                        .requestMatchers("ws/**").permitAll()
                        .requestMatchers("/app/application").permitAll()
                        .requestMatchers("api/v1/location-test/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }
}
