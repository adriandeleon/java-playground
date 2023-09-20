package com.grokthecode.examples.zt_zip;

import org.junit.jupiter.api.Test;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;

public class ZipExamples {

    @Test
    void zipDirectory(){
        ZipUtil.pack(new File("C:\\Users\\adria\\Backups\\2023-04-28\\repos"), new File("C:\\Users\\adria\\Backups\\2023-04-28\\repos.zip"));
    }
}
