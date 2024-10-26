package net.littlemad.socialapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {

    private String title;
    private String description;
    private String mediaUrl;

}
