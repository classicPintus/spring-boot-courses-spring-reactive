package com.codeway.rest.configuration;

import com.codeway.adapter.persistence.mapper.PostDocumentMapper;
import com.codeway.adapter.persistence.repository.InMemoryPostDocumentRepository;
import com.codeway.adapter.persistence.repository.PostDocumentRepository;
import com.codeway.adapter.persistence.service.PostService;
import com.codeway.domain.port.PostPersistencePort;
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
