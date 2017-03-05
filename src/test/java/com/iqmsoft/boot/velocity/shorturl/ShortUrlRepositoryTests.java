package com.iqmsoft.boot.velocity.shorturl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iqmsoft.boot.velocity.shorturl.ShortUrl;
import com.iqmsoft.boot.velocity.shorturl.ShortUrlApplication;
import com.iqmsoft.boot.velocity.shorturl.ShortUrlRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShortUrlApplication.class)
public class ShortUrlRepositoryTests {

    @Autowired
    ShortUrlRepository repository;
    
    @Autowired
    private ShortUrlRepositoryImpl repository1;

    @Test
    public void saveAndRetrieveWorks() {
        repository.save(new ShortUrl("http://longurl.com/kjhsdkjfhdskjfds", "http://shrturl.nu/hej", 0));
        ShortUrl shortUrl = repository.findByShortUrl("http://shrturl.nu/hej");
        assertTrue(shortUrl.getFullUrl().equalsIgnoreCase("http://longurl.com/kjhsdkjfhdskjfds"));
    }

    @Test
    public void getTotalRedirectSumWorks() {
        Integer total = repository1.getTotalRedirectSum();
        assertTrue(total.intValue()>=0);
    }
}
