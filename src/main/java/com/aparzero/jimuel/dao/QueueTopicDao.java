package com.aparzero.jimuel.dao;

import com.aparzero.jimuel.model.QueueTopic;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueueTopicDao {
    public static final String HASH_KEY = "Product";

    private  RedisTemplate template;

    public QueueTopicDao(final RedisTemplate redisTemplate) {
        this.template = redisTemplate;
    }

    public QueueTopic save(QueueTopic queueTopic){
        template.opsForHash().put(HASH_KEY,queueTopic.getTopicId(),queueTopic);
        return queueTopic;
    }

    public List<QueueTopic> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public QueueTopic findId(String id){
        return (QueueTopic) template.opsForHash().get(HASH_KEY,id);
    }


    public void deleteById(String id){
        template.opsForHash().delete(HASH_KEY,id);
    }
}
