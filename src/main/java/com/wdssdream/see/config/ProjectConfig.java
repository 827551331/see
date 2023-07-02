//package com.wdssdream.see.config;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.annotation.Bean;
//
///**
// * description: ProjectConfig
// * date: 2023/6/22 00:45
// *
// * @author wang_yw
// */
////@EnableAsync
////@Configuration
//public class ProjectConfig {
//
//    @Bean
//    public InitializingBean initializingBean(){
//        return ()-> SecurityContextHolder.setStrategyName(
//                SecurityContextHolder.MODE_GLOBAL
//        );
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("john").password("123456").authorities("read").build();
//        userDetailsService.createUser(user);
//        return  userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
