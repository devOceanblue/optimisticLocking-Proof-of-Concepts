package com.example.optimisticlocktest;

import com.example.optimisticlocktest.repository.UserQueryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    private final UserQueryImpl userQueryImpl;

    @Transactional
    public void updateEntity(Long id, String newName, String newValue) {
        userQueryImpl.updateEntity(id, newName, newValue);
    }
}
