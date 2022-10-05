package com.grokthecode.examples.http;

import com.grokthecode.examples.dtos.IpDto;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;
import lombok.val;

@Log4j2
public class HttpClientApp {

    public String getIPFromHttpBin() {
        val response = Unirest.get("https://httpbin.org/ip")
                .header("accept", "application/json")
                .asJson();

        log.info(response.getBody().toPrettyString());
        return response.getBody().toPrettyString();
    }

    public IpDto getIPFromHttpBinToDto() {
        val ipDto = Unirest.get("https://httpbin.org/ip")
                .header("accept", "application/json")
                .asObject(IpDto.class)
                .getBody();

        log.info(ipDto.toString());
        return ipDto;
    }

    public String getIMDBTitleInfo(){
        val response = Unirest.get("https://imdb-api.com/en/API/Title/k_8m9lb314/tt1832382")
                .asJson();

        log.info(response.getBody().toPrettyString());
        return response.getBody().toPrettyString();
    }
}
