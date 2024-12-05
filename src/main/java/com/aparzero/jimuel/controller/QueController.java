package com.aparzero.jimuel.controller;

import com.aparzero.jimuel.domain.Response;
import com.aparzero.jimuel.service.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat/api/v1/que")
public class QueController {

    private final QueueService queueService;


    public QueController(final QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping()
    ResponseEntity<Response> queUp(@RequestParam String artistId){
        return queueService.saveToQueue(artistId);
    }
    @GetMapping("/all")
    ResponseEntity<Response> all(){
        return queueService.all();
    }
}
