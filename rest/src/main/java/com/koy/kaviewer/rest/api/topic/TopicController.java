package com.koy.kaviewer.rest.api.topic;

import com.koy.kaviewer.kafka.core.remote.TopicService;
import com.koy.kaviewer.rest.KaViewerRestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {

    @GetMapping
    public ResponseEntity<Set<String>> list(@RequestHeader(name = "k-cluster") String cluster) {
        final TopicService topicService = KaViewerRestApplication.getBean(TopicService.class);
        final Set<String> topics = topicService.list(cluster);
        return ResponseEntity.ok(topics);

    }

}
