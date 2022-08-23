package com.grokthecode.examples.http;

import com.grokthecode.dtos.IpDto;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HttpClientApp {

    public String getIPFromHttpBin() {
        final HttpResponse<JsonNode> response = Unirest.get("https://httpbin.org/ip")
                .header("accept", "application/json")
                .asJson();

        log.info(response.getBody().toPrettyString());
        return response.getBody().toPrettyString();
    }

    public IpDto getIPFromHttpBinToDto() {
        final IpDto ipDto = Unirest.get("https://httpbin.org/ip")
                .header("accept", "application/json")
                .asObject(IpDto.class)
                .getBody();

        log.info(ipDto.toString());
        return ipDto;
    }
}
