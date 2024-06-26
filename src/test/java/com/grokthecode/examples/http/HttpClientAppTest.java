package com.grokthecode.examples.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class HttpClientAppTest {

    private static HttpClientApp httpClientApp;

    @BeforeAll
    static void setup() {
        httpClientApp = new HttpClientApp();
    }

    @Test
    void getIPFromHttpBin() {
        assertThat(httpClientApp.getIPFromHttpBin())
                .isNotNull();
    }

    @Test
    void getIPFromHttpBinToDto() {
        assertThat(httpClientApp.getIPFromHttpBinToDto())
                .isNotNull();
    }

    @Test
    void getIpFromAws(){
        final String myIp = httpClientApp.getIpFromAws();
        assertThat(myIp).isNotNull();
        System.out.printf(myIp);
    }

    @Test
    void getIMDBTitleInfo(){
        assertThat(httpClientApp.getIMDBTitleInfo())
                .isNotNull();
    }
}
