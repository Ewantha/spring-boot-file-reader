package com.eudagama12.example.filereader.config;


import com.eudagama12.example.filereader.service.WatchServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig implements ApplicationRunner {   //Application Runner to execute code

    @Autowired
    private WatchServiceTest watchServiceTest;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        watchServiceTest.fileListener();
    }
}
