package club.tourdejeu.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import club.tourdejeu.TourDeJeuApplication;

public class WebInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	// TODO Auto-generated method stub
	return builder.sources(TourDeJeuApplication.class);
    }

}
