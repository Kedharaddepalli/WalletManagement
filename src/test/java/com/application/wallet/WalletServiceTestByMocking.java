package com.application.wallet;

import com.application.wallet.app.WalletDto;
import com.application.wallet.app.WalletException;
import com.application.wallet.app.WalletRepository;
import com.application.wallet.app.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@SpringBootTest
public class WalletServiceTestByMocking {
    @Autowired
    private WalletService walletService;
    @MockBean
    private WalletRepository walletRepository;

    @Test
    void testRegisterByMocking(){
        when(this.walletRepository.getWalletById(100)).thenReturn(new WalletDto(100,"Kedhar",100.0));
        assertEquals("Kedhar",this.walletRepository.getWalletById(100).getName());
    }
    @Test
    public void testGetEmployeeThrowsExceptionTest() throws WalletException{

        given(this.walletRepository.getWalletById(200))
                .willReturn(null);
        assertThrows(WalletException.class,()->walletService.getWalletById(200));
    }
}
