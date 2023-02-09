package com.test;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageService {
    private MessageDAO messageDAO = new MessageDAO();

    public void save(MessageModel msg){
        messageDAO.saveOrUpdate(msg);
    }
}
