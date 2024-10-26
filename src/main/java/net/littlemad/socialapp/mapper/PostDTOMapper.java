package net.littlemad.socialapp.mapper;

import net.littlemad.socialapp.data.entity.Post;
import net.littlemad.socialapp.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PostDTOMapper {

    public Function<Post, PostDTO> toPostDTO() {
        return post -> new PostDTO(
                post.getTitle(),
                post.getDescription(),
                post.getMediaUrl());
    }

}
