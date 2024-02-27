package com.mziuri.services;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mziuri.Product.StorageConfig;

import java.io.File;
import java.io.IOException;

public class StorageReader {
    private static final String STORAGE_FILE_PATH = "src/main/resources/storage.json";
    private static StorageReader instance;
    private final ObjectMapper objectMapper;

    private StorageReader() {
        objectMapper = new ObjectMapper();
    }

    public static synchronized StorageReader getInstance() {
        if (instance == null) {
            instance = new StorageReader();
        }
        return instance;
    }

    public StorageConfig readStorage() throws IOException {
        return objectMapper.readValue(new File(STORAGE_FILE_PATH), StorageConfig.class);
    }
}


