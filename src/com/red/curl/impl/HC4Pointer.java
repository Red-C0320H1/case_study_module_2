package com.red.curl.impl;

import com.red.curl.Pointer;
import org.apache.http.impl.client.CloseableHttpClient;


/**
 * Created by ninghao on 2019/6/24.
 */
public class HC4Pointer extends Pointer {
    private CloseableHttpClient httpClient;

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
