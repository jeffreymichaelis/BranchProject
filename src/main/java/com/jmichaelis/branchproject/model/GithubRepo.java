package com.jmichaelis.branchproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GithubRepo {
    private String name;
    private String url;
}
