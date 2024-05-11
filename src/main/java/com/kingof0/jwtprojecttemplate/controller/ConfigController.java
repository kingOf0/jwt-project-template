package com.kingof0.jwtprojecttemplate.controller;

import com.kingof0.jwtprojecttemplate.model.entity.Config;
import com.kingof0.jwtprojecttemplate.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/config")
public class ConfigController {

    private final ConfigService configService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW_CONFIG') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Config> get(@RequestParam String key) {
        return ResponseEntity.of(configService.get(key));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('VIEW_CONFIG') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Iterable<Config>> getAll() {
        return ResponseEntity.ok(configService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_CONFIG') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Config> set(@RequestParam String key, @RequestParam String value) {
        return ResponseEntity.ok(configService.set(key, value));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('MODIFY_CONFIG') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Config> update(@RequestParam String key, @RequestParam String value) {
        return ResponseEntity.ok(configService.set(key, value));
    }

    @PutMapping("/batch")
    @PreAuthorize("hasAuthority('MODIFY_CONFIG') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Map<String, String>> update(@RequestBody Map<String, String> configs) {
        return ResponseEntity.ok(configService.setBatch(configs));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('DELETE_CONFIG') or hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Void> delete(@RequestParam String key) {
        configService.delete(key);
        return ResponseEntity.ok().build();
    }


}
