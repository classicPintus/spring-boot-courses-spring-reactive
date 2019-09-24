package com.codeway.reactive;

import com.codeway.adapter.persistence.exception.DocumentNotFoundException;
import com.codeway.domain.PostDomain;
import com.codeway.domain.port.PostPersistencePort;
import com.codeway.rest.controller.PostRestControllerImpl;
import com.codeway.rest.mapper.PostMapper;
import com.codeway.rest.model.PostModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostRestControllerImpl.class)
public class PostDomainRestControllerIT {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PostPersistencePort postPersistencePort;
    @MockBean
    private PostMapper postMapper;

    @Test
    public void shouldReturnPost() throws Exception {
        PostDomain p = new PostDomain("123", "234", "345");
        BDDMockito.given(postPersistencePort.findByIdentifier("123")).willReturn(p);

        PostModel pm = new PostModel("000", "111", "222");
        BDDMockito.given(postMapper.toRestObject(Mockito.eq(p))).willReturn(pm);

        mvc.perform(get("/posts/123")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.owner", is("000")))
            .andExpect(jsonPath("$.content", is("111")))
            .andExpect(jsonPath("$.domainIdentifier", is("222")));
    }

    @Test
    public void shouldBeNotFoundBecauseThePostIsNotThere() throws Exception {
        PostDomain p = new PostDomain("123", "234", "345");
        BDDMockito.given(postPersistencePort.findByIdentifier("123")).willThrow(new DocumentNotFoundException());

        mvc.perform(get("/posts/123")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldSaveNewPost() throws Exception {
        PostDomain p = new PostDomain("123", "234", "345");
        BDDMockito.given(postPersistencePort.create(Mockito.any(PostDomain.class))).willReturn(p);

        PostModel pm = new PostModel("000", "111", "222");
        BDDMockito.given(postMapper.toRestObject(Mockito.eq(p))).willReturn(pm);

        mvc.perform(post("/posts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }


}
