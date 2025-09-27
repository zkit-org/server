package org.zkit.support.server.ai.api.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InitPromptRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("InitPromptRunner run");
    }
    
}
