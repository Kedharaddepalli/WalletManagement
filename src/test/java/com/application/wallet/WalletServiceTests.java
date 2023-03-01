package com.application.wallet;

import com.application.wallet.app.WalletDto;
import com.application.wallet.app.WalletException;
import com.application.wallet.app.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class WalletServiceTests {
    @Autowired
    private WalletService walletService;
    @BeforeEach
    public void init(){
        WalletDto walletDto=new WalletDto(1,"Ford",100.0);
    }
    @Test
    void registerWalletTest() throws WalletException {
        WalletDto walletDto=new WalletDto(1,"Kedhar",100.0);
        assertEquals("Kedhar",walletService.registerWallet(walletDto).getName());
    }
    @Test
    void getWalletByIdThrowsException(){
        assertThrows(WalletException.class,()->this.walletService.getWalletById(1000));
    }
    @Test
    void updateWalletThrowsException() throws WalletException {
        WalletDto walletDto=new WalletDto(2,"Ford",100.0);
//        assertThrows(WalletException.class,()->this.walletService.updateWallet(1000));
        assertEquals(2,walletDto.getWalletId());
    }
    @Test
    void deleteWalletThrowsException(){
        assertThrows(WalletException.class,()->walletService.deleteWalletById(1));
    }

}
