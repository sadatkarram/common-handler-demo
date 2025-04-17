package com.oc.common_handler_demo.controller;

import com.oc.common_handler_demo.registry.HandlerRegistryUtils;
import com.oc.common_handler_demo.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommonHandlerController {

    @Value("${input.context.map.setting.true}")
    private String fetchContextMapFromDb;

    @Autowired
    HandlerRegistryUtils handlerRegistryUtils;

    @PostMapping("/handle")
    public Map<String,Object> incrementCounter(@RequestBody Map<String, Object> contextData){
        String activityName = contextData.get("activityName").toString();
        HandlerService handlerService = handlerRegistryUtils.getHandler(activityName);
        // ContextMap fetching condition
        if(fetchContextMapFromDb.contains(activityName)){
            contextData.put("fetchFromDB","TRUE");
        }else{
            contextData.put("fetchFromDB","FALSE");
        }
        handlerService.processActivity(contextData);
        return contextData;
    }

}
