package com.example.sosikcommunityservice.repository.querycustom;

import com.example.sosikcommunityservice.model.entity.PostEntity;
import com.example.sosikcommunityservice.model.entity.QPostEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QPostEntity postEntity = QPostEntity.postEntity;

    @Override
    public Slice<PostEntity> findByContentOrderByIdDesc(String content, Pageable pageable) {

        List<PostEntity> entity = jpaQueryFactory
                .selectFrom(postEntity)
                .where(postEntity.title.like("%" + content + "%"))
                .orderBy(postEntity.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(postEntity.count())
                .from(postEntity)
                .where(postEntity.title.like("%" + content + "%"))
                .fetchFirst();

        return new SliceImpl<>(entity, pageable, total > pageable.getOffset() + entity.size());
    }
}
