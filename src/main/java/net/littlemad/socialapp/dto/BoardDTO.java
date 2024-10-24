package net.littlemad.socialapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDTO {

    private String id;
    private String name;
    private boolean isPrivate;
}
