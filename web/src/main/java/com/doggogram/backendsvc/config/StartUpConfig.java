package com.doggogram.backendsvc.config;

import com.doggogram.backendsvc.services.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

public class StartUpConfig implements CommandLineRunner {

    private final StorageService storageService;

    @Value ("storage.delete.all")
    private boolean deleteStorage;

    public StartUpConfig (StorageService storageService, StorageService storageService1) {
        this.storageService = storageService1;
    }

    @Override public void run (String... args) throws Exception {
        if(deleteStorage) {
            storageService.deleteAll();
        }
        storageService.init();
    }
}
