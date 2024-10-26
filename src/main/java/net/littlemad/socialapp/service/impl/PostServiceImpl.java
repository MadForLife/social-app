package net.littlemad.socialapp.service.impl;

import lombok.AllArgsConstructor;
import net.littlemad.socialapp.data.entity.Post;
import net.littlemad.socialapp.data.entity.User;
import net.littlemad.socialapp.data.repository.PostRepository;
import net.littlemad.socialapp.data.repository.UserRepository;
import net.littlemad.socialapp.dto.PostDTO;
import net.littlemad.socialapp.mapper.PostDTOMapper;
import net.littlemad.socialapp.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static net.littlemad.socialapp.config.Constant.MEDIA_DIRECTORY;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostDTOMapper postDTOMapper;

    @Override
    public List<PostDTO> getPosts() {
        return postRepository.findAll().stream()
                .map(post -> postDTOMapper.toPostDTO()
                        .apply(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(String id) {
        return postDTOMapper.toPostDTO().apply(postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post with id [%s] not found".formatted(id))));
    }

    @Override
    public void deletePostById(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, MultipartFile media) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());

        // TODO Post Mapper
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setAuthor(user);

        post.setMediaUrl(mediaFunction.apply(user.getId(), media));

        post = postRepository.save(post);

        return new PostDTO(post.getTitle(), post.getDescription(), post.getMediaUrl());

    }

    private final Function<String, String> fileExtension = fileName -> Optional.of(fileName).filter(name -> name.contains("."))
            .map(name -> "." + name.substring(fileName.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<String, MultipartFile, String> mediaFunction = (userId, media) -> {

        String originalFileName = media.getOriginalFilename().substring(0, media.getOriginalFilename().lastIndexOf("."));
        String fileName = userId + originalFileName + fileExtension.apply(media.getOriginalFilename());

        try {
            Path fileStorageLocation = Paths.get(MEDIA_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(media.getInputStream(), fileStorageLocation.resolve(fileName), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/v1/posts/image/" + fileName).toUriString();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to save image");
        }
    };
}