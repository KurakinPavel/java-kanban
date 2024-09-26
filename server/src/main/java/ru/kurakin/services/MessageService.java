package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kurakin.dto.message.MessageDto;
import ru.kurakin.dto.message.NewMessageDto;
import ru.kurakin.mappers.MessageMapper;
import ru.kurakin.model.User;
import ru.kurakin.repositories.MessageRepository;
import ru.kurakin.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageDto sendMessage(int senderId, NewMessageDto newMessageDto) {
        User sender = userRepository.getReferenceById(senderId);
        sender.getId();
        Set<User> recipients = new HashSet<>();
        if (newMessageDto.getRecipientIds() != null && !newMessageDto.getRecipientIds().isEmpty()) {
            recipients.addAll(userRepository.findAllById(newMessageDto.getRecipientIds()));
            if (recipients.size() != newMessageDto.getRecipientIds().size()) {
                throw new IllegalArgumentException("При формировании сообщения указаны некорректные получатели");
            }
        } else {
            throw new IllegalArgumentException("При формировании сообщения не указаны получатели");
        }
        return MessageMapper.toMessageDto(messageRepository.save(MessageMapper.toMessage(newMessageDto, recipients, sender)));
    }
}
