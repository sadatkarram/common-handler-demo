package com.oc.common_handler_demo.service;


import java.util.Map;

public interface HandlerService {

    Map<String, Object> processActivity(Map<String, Object> contextData);
}
