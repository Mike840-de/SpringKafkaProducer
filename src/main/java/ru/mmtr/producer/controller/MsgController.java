package ru.mmtr.producer.controller;

import ru.mmtr.producer.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/edi")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    @PostMapping("/sendDocuments")
    public void sendOrder(@RequestParam Long msgId, @RequestBody UserDto userDto){
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("baeldung", msgId, userDto);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
