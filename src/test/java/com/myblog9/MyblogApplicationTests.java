package com.myblog9;

import com.myblog9.entity.Post;
import com.myblog9.repository.PostRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyblogApplicationTests {
    @Autowired
    PostRepository postRepo;

    @Test
    @Order(1)
    public void testCreated() {
        Post p = new Post();
        p.setId(11l);
        p.setTitle("Java Full Stack");
        p.setDescription("FrontEnd BackEnd DataBase");
        p.setContent("Developing Complete Websites Or WEb Apllications");
        postRepo.save(p);
        assertNotNull(postRepo.findById(11l).get());
    }

    @Test
    @Order(2)
    public void testReadAll() {
        List<Post> all = postRepo.findAll();
        //assertThat(all).isNotNull();
        assertThat(all).satisfies(posts -> new GreaterThan(0));
    }

    @Test
    @Order(3)
    public void testingSingleProduct() {
        Post post = postRepo.findById(11l).get();
        assertEquals("Developing Complete Websites Or WEb Apllications", post.getContent());
    }

    @Test
    @Order(4)
    public void testUpdate() {
        Post p = postRepo.findById(11l).get();
        p.setContent("Python Full Stack");
        postRepo.save(p);
        assertEquals("Python Full Stack", postRepo.findById(11l).get().getContent());
    }

    @Test
    @Order(5)
    public void testDelete() {
        postRepo.deleteById(27l);
        assertThat(postRepo.existsById(26l)).isFalse();
    }
}
