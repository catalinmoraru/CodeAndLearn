package com.catalin.springbootdata.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by catalin.moraru on 26.01.2017.
 */

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics(){
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);

    }

    public Topic getTopic(String id){
        return topicRepository.findOne(id);
    }

    public void updateTopic(String id, Topic topic) {
        topicRepository.save(topic);
    }

    public void delete(String id) {
        topicRepository.delete(id);
    }
}
