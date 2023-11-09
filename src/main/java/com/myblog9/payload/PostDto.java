package com.myblog9.payload;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String description;
    private String content;
    private String title;

}
