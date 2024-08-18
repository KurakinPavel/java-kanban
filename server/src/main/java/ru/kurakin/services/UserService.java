package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.FullUserDto;
import ru.kurakin.dto.NewUserDto;
import ru.kurakin.mappers.UserMapper;
import ru.kurakin.repositories.UserRepository;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public FullUserDto saveUser(NewUserDto newUserDto) {
        return UserMapper.toFullUserDto(userRepository.save(UserMapper.toUser(newUserDto)));
    }
}
