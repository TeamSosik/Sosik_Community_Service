package com.example.sosikcommunityservice.model.entity;

import com.example.sosikcommunityservice.dto.request.RequestUpdatePost;
import com.example.sosikcommunityservice.dto.response.ResponseCreateComment;
import com.example.sosikcommunityservice.dto.response.ResponseGetComment;
import com.example.sosikcommunityservice.dto.response.ResponseGetPost;
import com.example.sosikcommunityservice.dto.response.ResponseGetPostList;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "community",
        indexes = {
                @Index(
                        name = "idx_community_title", columnList = "title", unique = false
                ),
                @Index(
                        name = "idx_community_content", columnList = "content", unique = false
                ),
                @Index(
                        name = "idx_community_writer", columnList = "memberId", unique = false
                )
        }
)
// 작성자,내용,제목
public class PostEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 3000, nullable = false)
    @Lob
    private String content;

    private Integer hits;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;

    @Builder
    public PostEntity(
            final Long id,
            final Long memberId,
            final String title,
            final String content,
            final Integer hits,
            final List<CommentEntity> comments
    ) {
        this.id = id;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.comments = comments;
    }

    public void updatePost(RequestUpdatePost requestUpdatePost) {
        this.title = requestUpdatePost.title();
        this.content = requestUpdatePost.content();
    }

    public Integer plushit(Integer hits) {
        this.hits = hits + 1;
        return hits;
    }

    public static ResponseGetPost responseGetPost(PostEntity postEntity) {
        return new ResponseGetPost(
                postEntity.getMemberId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.plushit(postEntity.getHits()),
                postEntity.getCreatedAt(),
                postEntity.getComments().stream()
                        .map(commentEntity -> {
                            return ResponseGetComment.builder()
                                    .id(commentEntity.getId())
                                    .communityId(postEntity.getId())
                                    .memberId(commentEntity.getMemberId())
                                    .content(commentEntity.getContent())
                                    .createdAt(commentEntity.getCreatedAt())
                                    .build();
                        })
                        .collect(Collectors.toList())
        );
    }
}
