package com.codeway.adapter.persistence.configuration;

import com.codeway.adapter.persistence.repository.PostDocumentRepository;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.adapter.persistence.mapper.PostDocumentMapper;
import com.codeway.adapter.persistence.mapper.PostDocumentMapperImpl;
import com.codeway.adapter.persistence.repository.InMemoryPostDocumentRepository;
import com.codeway.adapter.persistence.service.PostService;
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
        return new PostService(postDocumentRepository, postDocumentMapper);
    }

    @Bean
    public PostPersistencePort inMemoryPostService(PostDocumentMapper postDocumentMapper) {
        return new PostService(new InMemoryPostDocumentRepository(), postDocumentMapper);
    }


}
