package ma.enset.demospringcloudkafka.web;

import ma.enset.demospringcloudkafka.entities.PageEvent;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.Random;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class PageEventRestController {

    @Autowired
    private StreamBridge streamBridge;

    // publish method
    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publishEvent(@PathVariable String topic, @PathVariable String name) {
        PageEvent pageEvent = new PageEvent(name, "user_" + new Random().nextInt(100), new Date(),
                10 + new Random().nextInt(100));
        streamBridge.send(
                topic,
                pageEvent);
        return pageEvent;
    }

    }



