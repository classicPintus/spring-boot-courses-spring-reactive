package com.codeway.persistence.configuration;

import com.codeway.domain.port.PostPersistencePort;
import com.codeway.persistence.mapper.PostDocumentMapper;
import com.codeway.persistence.mapper.PostDocumentMapperImpl;
import com.codeway.persistence.repository.PostDocumentRepository;
import com.codeway.persistence.service.PostServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public PostDocumentMapper postDocumentMapper() {
        return new PostDocumentMapperImpl();
    }

    @Bean
    public PostPersistencePort postService(PostDocumentRepository postDocumentRepository,
                                           PostDocumentMapper postDocumentMapper) {
        return new PostServiceImpl(postDocumentRepository, postDocumentMapper);
    }


}
