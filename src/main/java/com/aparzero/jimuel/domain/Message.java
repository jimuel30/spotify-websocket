package com.aparzero.jimuel.domain;

import lombok.Data;

@Data
public class Message {
    private String topic;
    private String sender;
    private String content;
}
