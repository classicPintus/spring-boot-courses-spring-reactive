package com.codeway.persistence.repository;

import com.codeway.adapter.persistence.document.PostDocument;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PostDocumentRepositoryIT {

    @Autowired
    private MongoRepository<PostDocument, String> postDocumentRepository;

    @Test
    public void shouldFindAllValues() {
        int rowsNumber = postDocumentRepository.findAll().size();
        Assert.assertThat(rowsNumber,is(0));
    }

    @Test
    public void shouldSaveNewPost() {
        PostDocument pd = new PostDocument();
        pd.setIdentifier("123");
        pd.setContent("234");
        pd.setOwner("345");
        PostDocument postDocumentSaved = postDocumentRepository.save(pd);

        Assert.assertThat(postDocumentSaved.getId(),is(notNullValue()));
        Assert.assertThat(postDocumentSaved.getIdentifier(),is("123"));
    }

    @Test
    public void shouldSaveMorePosts() {
        {
            PostDocument pd = new PostDocument();
            pd.setIdentifier("123");
            pd.setContent("234");
            pd.setOwner("345");
            postDocumentRepository.save(pd);
        }

        {
            PostDocument pd = new PostDocument();
            pd.setIdentifier("124");
            pd.setContent("234");
            pd.setOwner("345");
            postDocumentRepository.save(pd);
        }

        PostDocument expectedPostDocument = new PostDocument();
        expectedPostDocument.setIdentifier("123");

        List<PostDocument> postDocumentsSaved = postDocumentRepository.findAll();

        Assert.assertThat(postDocumentsSaved.size(),is(2));
    }

    @After
    public void tearDown() throws Exception {
        postDocumentRepository.deleteAll();
    }
}
