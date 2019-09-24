package com.codeway.rest.model;

public class PostModel {
    public String owner;
    public String content;
    public String domainIdentifier;


    public PostModel(String owner, String content, String domainIdentifier) {
        this.owner = owner;
        this.content = content;
        this.domainIdentifier = domainIdentifier;
    }

    @Override
    public String toString() {
        return "PostModel{" +
            "owner='" + owner + '\'' +
            ", content='" + content + '\'' +
            ", domainIdentifier='" + domainIdentifier + '\'' +
            '}';
    }
}
