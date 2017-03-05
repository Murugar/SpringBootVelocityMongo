
package com.iqmsoft.boot.velocity.shorturl;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ShortUrlRepository extends MongoRepository<ShortUrl, String>, ShortUrlRepositoryCustom {
    ShortUrl findByFullUrl(String fullUrl);
    ShortUrl findByShortUrl(String shortUrl);
   
}
