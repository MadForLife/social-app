package net.littlemad.socialapp.dto;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateBoardDTO {

    private String id;
    private String name;
    private boolean isPrivate;

}
