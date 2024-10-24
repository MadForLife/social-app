package net.littlemad.socialapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBoardDTO {

    private String name;
    private boolean isPrivate;
}
