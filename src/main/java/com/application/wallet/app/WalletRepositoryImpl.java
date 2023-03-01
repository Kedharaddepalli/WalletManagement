package com.application.wallet.app;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class WalletRepositoryImpl implements WalletRepository{

    private Map<Integer,WalletDto> myWalletMap=new HashMap<>();
    @Override
    public WalletDto saveWallet(WalletDto newWallet) {
        myWalletMap.put(newWallet.getWalletId(),newWallet);
        return newWallet;
    }

    @Override
    public WalletDto getWalletById(Integer walletId) {
        return myWalletMap.get(walletId);
    }

    @Override
    public WalletDto updateWallet(WalletDto walletDto) {
        return myWalletMap.replace(walletDto.getWalletId(),walletDto);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) {
        return myWalletMap.remove(walletId);
    }
}
