package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Message;

public interface MessageRepository  {

	public List<Message> findAll();
	public Message save(Message message);
	public Message update(Message message);
	public Message updateText(Message message);
	public Message findMessage(Long id);
	public void deleteMessage(Long id);
}
