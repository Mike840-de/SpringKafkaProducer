package com.example.kafka.controller;

import com.example.kafka.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    @PostMapping
    public void sendOrder(@RequestParam Long msgId, @RequestBody UserDto userDto){
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, userDto);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
