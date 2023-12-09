package com.example.optimisticlocktest.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.optimisticlocktest.entity.QUserEntity.userEntity;

@RequiredArgsConstructor
@Component
public class UserQueryImpl {

    private final JPAQueryFactory queryFactory;

    public void updateEntity(Long id, String newName, String newValue) {
        queryFactory.update(userEntity)
                .where(userEntity.id.eq(id))
                .set(userEntity.name, newName)
                .set(userEntity.email, newValue)
                .execute();
    }
}
