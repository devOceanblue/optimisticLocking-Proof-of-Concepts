package com.example.optimisticlocktest.repository;

import com.example.optimisticlocktest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update UserEntity e set e.name = :name, e.version = :version+1L where e.id = :id AND e.version = :version")
    void updateName(@Param("id") Long id, @Param("name") String name, @Param("version") Long version);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update UserEntity e set e.name = :name where e.id = :id")
    void updateNameWithoutVersionCheck(@Param("id") Long id, @Param("name") String name);
}
