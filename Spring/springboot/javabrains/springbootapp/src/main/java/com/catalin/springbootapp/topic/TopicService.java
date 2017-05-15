package com.catalin.springbootapp.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by catalin.moraru on 26.01.2017.
 */

@Service
public class TopicService {

    private List<Topic> topics = new ArrayList<>(Arrays.asList(
                new Topic("spring","spring-framework", "Spring Framework Description"),
                new Topic("java","hava-framework", "Java Framework Description"),
                new Topic("cata#","cata#-framework", "Cata# Framework Description")));


    public List<Topic> getAllTopics(){
        return topics;
    }

    public Topic getTopic(String id){
        return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void updateTopic(String id, Topic topic) {
        for (int i =0 ; i< topics.size(); i++){
            Topic t = topics.get(i);
            if ( t.getId().equals(id)){
                topics.set(i,topic);
                return;
            }
        }
    }

    public void delete(String id) {
        topics.removeIf(t -> t.getId().equals(id));
    }
}
