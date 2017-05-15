package com.catalin.springbootdata.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by catalin.moraru on 26.01.2017.
 */

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    public Topic getTopic(@PathVariable String id){
        return topicService.getTopic(id);
    }

    @RequestMapping(method= RequestMethod.POST, value = "/topics")
    public void addTopic(@RequestBody Topic topic){
        topicService.addTopic(topic);
    }

    @RequestMapping(method= RequestMethod.PUT, value = "/topics/{id}")
    public void addTopic(@RequestBody Topic topic, @PathVariable String id){
        topicService.updateTopic(id,topic);
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/topics/{id}")
    public void deleteTopic(@PathVariable String id){
        topicService.delete(id);
    }


}
