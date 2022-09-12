package com.eudagama12.example.filereader.service;

import com.eudagama12.example.filereader.model.SampleModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://www.baeldung.com/java-buffered-reader">...</a>
 *
 * @author Ewantha_105550
 */
@Service
@Slf4j
public class BufferReaderService {

    private static final int SZ = 16384;    //Input buffer size (set to 16KB)

    List<SampleModel> sampleModelList;    //v2

    @PostConstruct
    public void start() {
        log.info("Starting BufferReaderService");
        readFile();
    }

    private void readKeyboard() {
        log.info("Starting BufferReaderService|readKeyboard");
        String out = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while ((out = reader.readLine()) != null) {
                log.info("BufferReaderService|readKeyboard|Output:{}", out);
            }
        } catch (Exception exc) {
            log.error(exc.toString());
        }
    }

    private void readFile() {
        log.info("Starting BufferReaderService|readFile");
        String out = null;
        sampleModelList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"), SZ)) {
            while ((out = reader.readLine()) != null) {
                SampleModel sampleModel = getSampleModel(out);  //Map line to POJO
                sampleModelList.add(sampleModel);
                log.info("BufferReaderService|readLine|Output:{}", sampleModel);
            }
        } catch (Exception exc) {
            log.error(exc.toString());
        }

        log.info("BufferReaderService|readFile|Output:{}", out);
    }

    private SampleModel getSampleModel(String out) {
        SampleModel sampleModel = new SampleModel();
        String[] parts = out.split("\\|");
        sampleModel.setField1(parts[0]);
        sampleModel.setField2(parts[1]);
        sampleModel.setField3(parts[2]);
        return sampleModel;
    }
}
