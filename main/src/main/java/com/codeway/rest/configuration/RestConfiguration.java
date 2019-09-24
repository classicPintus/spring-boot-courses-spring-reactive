package com.codeway.rest.configuration;

import com.codeway.rest.mapper.PostMapper;
import com.codeway.rest.mapper.PostMapperRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Bean
    public PostMapper postMapper() {
        return new PostMapperRest();
    }


}
