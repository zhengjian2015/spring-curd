package com.zj.curd.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThreadPoolExcuteController {
    
    Logger LOG = LoggerFactory.getLogger(ThreadPoolExcuteController.class);
    
    @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @RequestMapping("/execute")
    @ResponseBody
    public void execute(){
        taskExecutor.execute(new Runnable(){

            public void run() {
                try {
                    LOG.info("执行线程任务开始前");
                    Thread.currentThread().sleep(10000);
                    if (LOG.isDebugEnabled()) {
                        LOG.info("执行线程任务结束");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        });
    }

}
