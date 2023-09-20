package com.grokthecode.examples.zip4j;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.ec2.endpoints.internal.Value;

import java.io.File;

public class ZipExamples {


    @Test
    void zipFolder() throws ZipException {
        final String directory = "C:\\Users\\adria\\Backups\\2023-04-28\\repos";

        System.out.printf("Zipping directory: " + directory + "...." + "\n");
        new ZipFile("C:\\Users\\adria\\Backups\\2023-04-28\\repos-2.zip").addFolder(new File(directory));
        System.out.printf("Done.");
    }

}
