package com.arronhuang.boot.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ArronHuang
 * @date 2021/01/20
 */
@ConfigurationProperties(prefix = "arron.boot")
@Data
public class ArronBootConfig {

    private boolean printRequestArchiveLog = false;

}
