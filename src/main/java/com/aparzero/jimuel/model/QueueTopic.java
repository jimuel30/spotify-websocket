package com.aparzero.jimuel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("topic")
@Builder
public class QueueTopic implements Serializable {
    @Id
    private String topicId;
    private LocalDateTime entryDate;
    private String conversationKey;
}
