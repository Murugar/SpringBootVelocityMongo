package com.iqmsoft.boot.velocity.shorturl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
public class WelcomeController {

  
    @Autowired
    private ShortUrlRepository repository;

    @Value("${application.message:Spring Boot}")
    private String message = "Spring Boot";

    @Value("${server.address:localhost}")
    private String serverAddress = "localhost";

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${application.baseurl}")
    private String applicationBaseURL;

    private ShortUrlFactory shortUrlFactory;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        model.put("redirects", repository.getTotalRedirectSum());
        model.put("shortenedURLs", repository.count());
        return "welcome";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String shortenURL(String url, Map<String, Object> model) {
        log.debug("Shortening URL: " + url);
        shortUrlFactory = new ShortUrlFactory(applicationBaseURL);
        ShortUrl shortUrl = shortUrlFactory.makeShortUrl(url);
        log.debug("Result: " + shortUrl.getShortUrl());
        repository.save(shortUrl);
        model.put("time", new Date());
        model.put("message", this.message);
        model.put("url", shortUrl.getShortUrl());
        model.put("fullurl", shortUrl.getFullUrl());
        model.put("redirects", repository.getTotalRedirectSum());
        model.put("shortenedURLs", repository.count());
        return "welcome";
    }

}