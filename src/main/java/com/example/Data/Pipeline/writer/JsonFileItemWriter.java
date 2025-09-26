package com.example.Data.Pipeline.writer;

import com.example.Data.Pipeline.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonFileItemWriter implements ItemWriter<Student> {

    private final ObjectWriter objectWriter;
    private final File file;

    public JsonFileItemWriter(String filePath) {
        this.objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        this.file = new File(filePath);
    }

    @Override
    public void write(Chunk<? extends Student> chunk) throws Exception {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (Student student : chunk) {
                String json = objectWriter.writeValueAsString(student);
                fileWriter.write(json + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo JSON", e);
        }
    }
}
