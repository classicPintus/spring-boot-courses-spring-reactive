package com.codeway.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostModel {
    private final String owner;
    private final String content;
    private final String domainIdentifier;
}
