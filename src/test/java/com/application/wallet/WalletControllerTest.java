package com.application.wallet;

import com.application.wallet.app.WalletDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletControllerTest {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init() {
        this.restTemplate.postForObject("http://localhost:" + port + "/wallet", new WalletDto(1, "Kedhar", 100.0), WalletDto.class);

    }
    @Test
    public void getEmployeeByIdTest() throws Exception {
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/wallet/1", WalletDto.class);
        assertEquals("Kedhar",foundWallet.getName());
    }

}

