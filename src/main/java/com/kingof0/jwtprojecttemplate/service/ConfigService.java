package com.kingof0.jwtprojecttemplate.service;

import com.kingof0.jwtprojecttemplate.model.entity.Config;
import com.kingof0.jwtprojecttemplate.repository.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final ConfigRepository configService;

    public Optional<Config> get(String key) {
        return configService.findById(key);
    }

    public Config set(String key, String value) {
        return configService.save(new Config(key, value));
    }

    public void delete(String config) {
        configService.deleteById(config);
    }

    public Iterable<Config> getAll() {
        return configService.findAll();
    }

    public Map<String, String> setBatch(Map<String, String> configs) {
        List<Config> configList = new ArrayList<>();
        configs.forEach((key, value) -> configList.add(new Config(key, value)));

        configService.saveAll(configList);
        return configs;
    }
}
