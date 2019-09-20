package com.codeway.domain;

public class Post {

    private final String owner;
    private final String content;
    private final String identifier;

    public Post(String domainIdentifier, String owner, String content) {
        this.owner = owner;
        this.content = content;
        this.identifier = domainIdentifier;
    }

    public String getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public String getIdentifier() {
        return identifier;
    }
}
