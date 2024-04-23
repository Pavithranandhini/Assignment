package org.example.preRequisite;

import org.example.CoinMapClient.CoinMapClientTest;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

public class APIBaseTest {


public CoinMapClientTest coinMapClientTest;
    public static Map<String, Object> getHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("content-Type", "application/json");

        return headers;
    }
    @BeforeClass
    public void initiation() {
        coinMapClientTest = new CoinMapClientTest();
    }


}
