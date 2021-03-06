package com.iqmsoft.boot.velocity.shorturl;

import org.springframework.data.annotation.Id;

public class ShortUrl {

    @Id
    private String id;

    private String fullUrl;
    private String shortUrl;
    private Integer redirectCount = 0;

    public ShortUrl(String fullUrl, String shortUrl, Integer redirectCount) {
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
        this.redirectCount = redirectCount != null ? redirectCount : ShortUrlFactory.STARTING_REDIRECT_COUNT;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    protected void incrementRedirectCount() {
        redirectCount++;
    }

    @Override
    public String toString() {
        return String.format(
                "ShortUrl[id=%s, fullUrl='%s', shortUrl='%s', redirectCount='%s']",
                id, fullUrl, shortUrl, redirectCount);
    }
}
