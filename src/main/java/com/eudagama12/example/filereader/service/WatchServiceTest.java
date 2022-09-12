package com.eudagama12.example.filereader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

/**
 * <a href="https://www.baeldung.com/java-nio2-watchservice">OnlineResource</a>
 *
 * @author Ewantha_105550
 */
@Service
@Slf4j
public class WatchServiceTest {

    public void fileListener() throws IOException, InterruptedException {
        log.info("File Listener Started...");

        //*******************************************

        WatchService watchService = FileSystems.getDefault().newWatchService(); //Create Watch Service instance

        Path path = Paths.get("src/main/resources/");   //Path to directory we need to monitor

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);  //Register Path

        //*******************************************

        WatchKey key;
        while ((key = watchService.take()) != null) {   //Block until event occurs
            for (WatchEvent<?> event : key.pollEvents()) {
                log.info("Event kind:{}|File name:{}", event.kind(), event.context());
            }
            key.reset();    //Capture more events only after reset
        }
    }
}
