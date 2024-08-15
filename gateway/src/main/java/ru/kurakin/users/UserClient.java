package ru.kurakin.users;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.kurakin.users.dto.UserDto;

@Service
@AllArgsConstructor
public class UserClient {
    private final WebClient webClient;

    public Mono<ResponseEntity<UserDto>> saveUser(UserDto userDto) {
        return webClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .retrieve()
                .toEntity(UserDto.class);
    }
}
