package com.grokthecode;

import lombok.extern.log4j.Log4j2;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;

@Log4j2
public class ConfigApp {

  public void loadConfig() {
    final HoconConfigurationLoader loader =
        HoconConfigurationLoader.builder().path(Path.of("src/main/resources/app.conf")).build();

    CommentedConfigurationNode root;
    try {
      root = loader.load();
    } catch (IOException ex) {
      log.error("An error occurred while loading this configuration: " + ex.getMessage());
      if (ex.getCause() != null) {
        ex.getCause().printStackTrace();
      }
      System.exit(1);
      return;
    }
    final ConfigurationNode countNode = root.node("messages", "count");

    final String name = root.node("name").getString();
    final int count = countNode.getInt(Integer.MIN_VALUE);

    if (name == null || count == Integer.MIN_VALUE) {
      log.error("Invalid configuration.");
      System.exit(2);
      return;
    }

    log.info(String.format("name: %s", name));
    log.info(String.format("message count: %d ", count));
  }
}
