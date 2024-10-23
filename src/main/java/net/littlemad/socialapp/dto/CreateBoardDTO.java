package net.littlemad.socialapp.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateBoardDTO {

    // FIXME - Board creation requires author-ID

    private String name;
    private boolean isPrivate;

}
