package com.oc.common_handler_demo.registry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.common_handler_demo.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HandlerRegistryUtils {

    private final Map<String, HandlerService> handlerRegistry;

    @Autowired
    public HandlerRegistryUtils(List<HandlerService> handlerServices,Environment env,ObjectMapper objectMapper) throws JsonProcessingException {
        this.handlerRegistry = handlerServices
                .stream()
                .collect(Collectors.toMap(handlerService -> handlerService.getClass().getSimpleName(),
                        handlerService -> handlerService));

        String raw = env.getProperty("common.handler");
        Map<String, String> commonHandlerMap = objectMapper.readValue(raw, new TypeReference<Map<String, String>>() {});
        commonHandlerMap.forEach((key, value) -> {
            String[] activityNames = value.split(",");
            for(String activityName: activityNames){
                this.handlerRegistry.put(activityName,handlerRegistry.get(key));
            }
        });
    }
    public HandlerService getHandler(String activityName) {
        return handlerRegistry.get(activityName);
    }
}
