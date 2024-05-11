package com.kingof0.jwtprojecttemplate.repository;

import com.kingof0.jwtprojecttemplate.model.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, String> {

}
