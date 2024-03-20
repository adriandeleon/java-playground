package com.grokthecode.examples.zip4j;

import net.lingala.zip4j.ZipFile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ZipExamplesTest {


    @Test
    void zipFolder() {
        final String baseDir = "C:\\Users\\adria\\Backups\\2023-04-28\\";
            final String repoDirName = "repos";
            final String zippedFileName = "repos-2.zip";
            String directory = baseDir + repoDirName;

            System.out.printf("Zipping directory: %s....\n", directory);

            try (ZipFile zipFile = new ZipFile(baseDir + zippedFileName)) {
                zipFile.addFolder(new File(directory));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Done.");
        }
    }
