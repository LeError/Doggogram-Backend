package com.doggogram.backendsvc.storage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties ("storage")
@Component
public class StorageProperties {

    private String location = "C:\\Users\\d073980\\IdeaProjects\\backend-svc\\data\\src\\main\\java\\com\\doggogram\\backendsvc\\storage\\storage";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
