package ru.hetoiiblpb.client_REST.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.hetoiiblpb.client_REST")
public class SourceHolders implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/bootstrap-3.3.7/**").addResourceLocations("/bootstrap-3.3.7/");
            registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
            registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
