package com.aparzero.jimuel.service;

import com.aparzero.jimuel.dao.QueueTopicDao;
import com.aparzero.jimuel.domain.Response;
import com.aparzero.jimuel.model.QueueTopic;
import com.aparzero.jimuel.publisher.RabbitMQJSONProducer;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class QueueService {
    private final QueueTopicDao dao;
    private static final Logger LOG = LoggerFactory.getLogger(QueueService.class);
    public QueueService(QueueTopicDao dao) {
        this.dao = dao;
    }

    public ResponseEntity<Response> all(){
        return Response.success(dao.findAll());
    }


    public ResponseEntity<Response> saveToQueue(String artistId){
        final QueueTopic queueTopic = dao.findId(artistId);
        ResponseEntity<Response> response;
        if(Objects.nonNull(queueTopic)){
            LOG.info("TOPIC ALREADY EXIST!!");
             dao.deleteById(artistId);
             response = Response.success(queueTopic);
        }
        else{
            LOG.info("NEW ARTIST: ");
            final LocalDateTime dateTime = LocalDateTime.now();
            final String topicKey = artistId.concat(dateTime.toString().replaceAll("[^a-zA-Z0-9]", ""));

            LOG.info("TOPIC-KEY: {}",topicKey);


            final QueueTopic queTopic = QueueTopic.builder()
                    .topicId(artistId)
                    .entryDate(dateTime)
                    .conversationKey(topicKey)
                    .build();
            dao.save(queTopic);
            response = Response.success(queTopic);
        }
       return response;
    }
}
