package com.kristjan.webshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Photo
{
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
