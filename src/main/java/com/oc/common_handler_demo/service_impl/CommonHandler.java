package com.oc.common_handler_demo.service_impl;

import com.oc.common_handler_demo.service.HandlerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class CommonHandler implements HandlerService {

    @Value("#{${counter.map}}")
    private Map<String,String> counterMap;

    @Override
    public Map<String, Object> processActivity(Map<String, Object> contextData) {
        System.out.println("Common-Handler is been executed");
        String counterKey = counterMap.get(contextData.get("activityName").toString());
        Integer counterValue = (Integer) contextData.getOrDefault(counterKey,0);
        contextData.put(counterKey,counterValue+1);
        return contextData;
    }
}
