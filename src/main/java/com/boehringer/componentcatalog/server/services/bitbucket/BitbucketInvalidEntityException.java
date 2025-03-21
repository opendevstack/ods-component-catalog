package com.boehringer.componentcatalog.server.services.bitbucket;

public class BitbucketInvalidEntityException extends RuntimeException {
    public BitbucketInvalidEntityException(String message) {
        this(message, null);
    }

    public BitbucketInvalidEntityException(String message, Exception e) {
        super(message, e);
    }
}
