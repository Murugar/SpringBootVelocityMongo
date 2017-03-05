package com.iqmsoft.boot.velocity.shorturl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RedirectController {

 
    @Autowired
    private ShortUrlRepository repository;

    @RequestMapping(value="???????", method = RequestMethod.GET)
    public void redirect(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        ShortUrl shortUrl = findShortUrl(request);

        if (shortUrl != null) {
            redirect(httpServletResponse, shortUrl);
        } else {
            log.debug("Miss!");
            httpServletResponse.sendRedirect("/");
        }

    }

    private ShortUrl findShortUrl(HttpServletRequest request) {
        String currentUrl = request.getRequestURL().toString();
        log.debug("Searching for: " + currentUrl);
        return repository.findByShortUrl(currentUrl);
    }

    private void redirect(HttpServletResponse httpServletResponse, ShortUrl shortUrl)  throws IOException {
        shortUrl.incrementRedirectCount();
        repository.save(shortUrl);
        String destinationUrl = shortUrl.getFullUrl();
        log.debug("Going to redirect to: " + destinationUrl);
        httpServletResponse.sendRedirect(destinationUrl);
    }

}
