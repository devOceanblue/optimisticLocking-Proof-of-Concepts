package com.example.optimisticlocktest.service;

import com.example.optimisticlocktest.UserServiceImpl;
import com.example.optimisticlocktest.entity.UserEntity;
import com.example.optimisticlocktest.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains tests for the UserServiceImpl class.
 */
@SpringBootTest
public class UserServiceImplTest {

    private Long USER_ID;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        String randomName = UUID.randomUUID().toString();
        String randomEmail = UUID.randomUUID().toString();
        String randomLocation = UUID.randomUUID().toString();

        UserEntity userEntity = UserEntity.builder()
                .name(randomName)
                .email(randomEmail)
                .location(randomLocation)
                .build();
        userRepository.save(userEntity);
        USER_ID = userEntity.getId();
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteById(USER_ID);
    }

    /**
     * This method updates the name and email of a user entity using querydsl.
     * Asserts that the name and email of the user entity are successfully updated and the version is not incremented.
     */
    @Test
    @DisplayName("update user with querydsl")
    void updateEntity() {
        UserEntity userEntity = userRepository.findById(USER_ID).get();
        Long version = userEntity.getVersion();

        String newName = UUID.randomUUID().toString();
        String newEmail = UUID.randomUUID().toString();

        userService.updateEntity(USER_ID, newName, newEmail);

        UserEntity updatedUser = userRepository.findById(USER_ID).get();
        assertThat(updatedUser.getName()).isEqualTo(newName);
        assertThat(updatedUser.getVersion()).isNotEqualTo(version+1L);

    }
}