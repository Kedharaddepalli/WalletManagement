package com.application.wallet.app;

public interface WalletRepository {
    WalletDto saveWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer walletId);
    WalletDto updateWallet(WalletDto walletDto);
    WalletDto deleteWalletById(Integer walletId);
}
