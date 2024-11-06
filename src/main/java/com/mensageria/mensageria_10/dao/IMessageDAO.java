package com.mensageria.mensageria_10.dao;

import com.mensageria.mensageria_10.model.Message;

import java.util.Optional;

public interface IMessageDAO {
    Message create(Message message);
    Optional<Message> read(Long id);
}
