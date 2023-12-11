package com.example.optimisticlocktest.repository;

import com.example.optimisticlocktest.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class contains test cases for the UserRepository class.
 */
@SpringBootTest
public class UserRepositoryTest {

    private Long USER_ID;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        String randomName = UUID.randomUUID().toString();
        String randomEmail = UUID.randomUUID().toString();
        String randomLocation = UUID.randomUUID().toString();

        // Set up a sample UserEntity
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
     * Update the name of a UserEntity using the Spring Data Repository save method.
     * Asserts that the name of the UserEntity is successfully updated and the version is incremented by 1.
     */
    @Test
    @DisplayName("update user with Spring Data Repository method")
    public void updateUserWithSpringDataRepositoryMethod() {
        // given
        UserEntity userEntity = userRepository.findById(USER_ID).get();
        USER_ID = userEntity.getId();
        Long version = userEntity.getVersion();

        // when
        String randomName = UUID.randomUUID().toString();
        userEntity.updateName(randomName);
        userRepository.save(userEntity);

        // then
        UserEntity updatedUser = userRepository.findById(USER_ID).get();
        assertThat(updatedUser.getName()).isEqualTo(randomName);
        assertThat(updatedUser.getVersion()).isEqualTo(version+1L);
    }

    /**
     * Update the name of a UserEntity using @Query and query annotation with the version included.
     * Asserts that the name of the UserEntity is successfully updated and the version is incremented by 1.
     */
    @Test
    @DisplayName("update user with @Query with version check")
    public void updateUserWithQueryAnnotationAndVersionCheck() {
        // given
        UserEntity userEntity = userRepository.findById(USER_ID).get();
        String newName = UUID.randomUUID().toString();
        Long version = userEntity.getVersion();

        //when
        userRepository.updateName(USER_ID, newName, version);

        // then
        UserEntity updatedUser = userRepository.findById(USER_ID).get();
        assertThat(updatedUser.getName()).isEqualTo(newName);
        assertThat(updatedUser.getVersion()).isEqualTo(version+1L);
    }


    /**
     * Update the name of a UserEntity without checking the version using @Query.
     * Asserts that the name of the UserEntity is successfully updated and the version is not incremented.
     */
    @Test
    @DisplayName("update user with @Query and no version check")
    public void updateUserWithQueryAnnotationAndNoVersionCheck() {
        // given
        UserEntity userEntity = userRepository.findById(USER_ID).get();
        String newName = UUID.randomUUID().toString();
        Long version = userEntity.getVersion();

        //when
        userRepository.updateNameWithoutVersionCheck(USER_ID, newName);

        // then
        UserEntity updatedUser = userRepository.findById(USER_ID).get();
        assertThat(updatedUser.getName()).isEqualTo(newName);
        assertThat(updatedUser.getVersion()).isNotEqualTo(version+1L);
    }
}