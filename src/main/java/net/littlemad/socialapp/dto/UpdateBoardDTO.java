package net.littlemad.socialapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBoardDTO {

    private String name;
    private boolean isPrivate;

}
