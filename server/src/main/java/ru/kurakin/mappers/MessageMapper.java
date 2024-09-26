package ru.kurakin.mappers;

import ru.kurakin.dto.message.MessageDto;
import ru.kurakin.dto.message.NewMessageDto;
import ru.kurakin.model.Message;
import ru.kurakin.model.User;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class MessageMapper {
    public static Message toMessage(NewMessageDto newMessageDto, Set<User> recipients, User sender) {
        return new Message(
                0,
                newMessageDto.getMessage(),
                recipients, sender, LocalDate.now()
        );
    }

    public static MessageDto toMessageDto(Message message) {
        return new MessageDto(
                message.getId(),
                message.getMessage(),
                message.getRecipients().stream()
                        .map(UserMapper::toShortUserDto)
                        .collect(Collectors.toList()),
                UserMapper.toShortUserDto(message.getSender()),
                message.getCreated()
        );
    }
}
