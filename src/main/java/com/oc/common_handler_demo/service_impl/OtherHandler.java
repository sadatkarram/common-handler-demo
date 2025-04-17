package com.oc.common_handler_demo.service_impl;

import com.oc.common_handler_demo.service.HandlerService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OtherHandler implements HandlerService {
    @Override
    public Map<String, Object> processActivity(Map<String, Object> contextData) {
        System.out.println("Other-Handler is been executed");
        Integer otherCounter = (Integer) contextData.getOrDefault("otherCounter",0);
        contextData.put("otherCounter",otherCounter+1);
        contextData.put("Handler","OtherHandler");
        return contextData;
    }
}
