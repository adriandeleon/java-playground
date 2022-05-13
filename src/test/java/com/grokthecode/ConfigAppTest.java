package com.grokthecode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConfigAppTest {
    private static ConfigApp configApp;

    @BeforeAll
    static void setup() {
        configApp = new ConfigApp();
    }

    @Test
    void readConfigFile() {
        configApp.loadConfig();
    }
}
