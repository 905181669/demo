package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;
import com.example.demo.service.MessageRepository;

@RestController
@RequestMapping("/")
public class MessageController {

    @Autowired
    private  MessageRepository messageRepository;

    // 获取所有消息体
    @GetMapping(value = "messages")
    public List<Message> list() {
        List<Message> messages = this.messageRepository.findAll();
        return messages;
    }

    // 创建一个消息体
    @PostMapping(value = "message")
    public Message create(Message message) {
        message = this.messageRepository.save(message);
        return message;
    }

    // 使用 put 请求进行修改
    @PutMapping(value = "message")
    public Message modify(Message message) {
        Message messageResult=this.messageRepository.update(message);
        return messageResult;
    }

    // 更新消息的 text 字段
    @PatchMapping(value="/message/text")
    public Message patch(Message message) {
        Message messageResult=this.messageRepository.updateText(message);
        return messageResult;
    }

    @GetMapping(value = "message/{id}")
    public Message get(@PathVariable Long id) {
        Message message = this.messageRepository.findMessage(id);
        return message;
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.messageRepository.deleteMessage(id);
    }
}
