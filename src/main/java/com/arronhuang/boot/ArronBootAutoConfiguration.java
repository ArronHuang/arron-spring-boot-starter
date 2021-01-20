package com.arronhuang.boot;

import com.arronhuang.boot.component.ArronBootConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ArronHuang
 * @date 2021/01/20
 */
@Configuration
@EnableConfigurationProperties(ArronBootConfig.class)
@ComponentScan("com.arronhuang.boot")
public class ArronBootAutoConfiguration {
}
