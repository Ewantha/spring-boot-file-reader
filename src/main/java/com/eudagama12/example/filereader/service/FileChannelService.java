package com.eudagama12.example.filereader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class FileChannelService {

    //@PostConstruct
    public void start()
            throws IOException {
        log.info("Starting FileChannelService");
        RandomAccessFile reader = null;
        FileChannel channel = null;
        try {
            String file = "src/main/resources/fileTest.txt";
            reader = new RandomAccessFile(file, "r");
            channel = reader.getChannel();
        } catch (FileNotFoundException e) {
            log.error("File Not Found!!!");
        }

        int bufferSize = 1024;
        if (bufferSize > channel.size()) {
            bufferSize = (int) channel.size();
        }
        ByteBuffer out = ByteBuffer.allocate(bufferSize);
        channel.read(out);
        out.flip();

        String fileContent = new String(out.array(), StandardCharsets.UTF_8);

        log.info("FileChannelService|Output:{}",fileContent);

        channel.close();
        reader.close();
    }
}
