package com.application.wallet.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//@Service
public class WalletServiceImpl implements WalletService{
    //@Autowired
     private WalletRepository walletRepository;
    WalletDto walletDto;
    @Override
    public WalletDto registerWallet(WalletDto walletDto) throws WalletException {
        return walletRepository.saveWallet(walletDto);
    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        WalletDto foundWallet=walletRepository.getWalletById(walletId);
        Double balance=foundWallet.getBalance();
        balance  =balance+amount;
        foundWallet.setBalance(balance);
        walletRepository.updateWallet(foundWallet);
        return balance;
    }

    @Override
    public Double withdrawByWalletId(Integer walletId, Double amount) throws WalletException {
        WalletDto foundWallet=walletRepository.getWalletById(walletId);
        Double balance=foundWallet.getBalance();
        balance=balance-amount;
        foundWallet.setBalance(balance);
        walletRepository.updateWallet(foundWallet);
        return balance;
    }

    @Override
    public Double fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
        WalletDto foundWallet=walletRepository.getWalletById(fromWalletId);
        WalletDto foundToWallet=walletRepository.getWalletById(toWalletId);
        Double fromBalance=foundWallet.getBalance();
        fromBalance=fromBalance-amount;
        Double toBalance=foundToWallet.getBalance();
        toBalance=toBalance+amount;
        walletRepository.updateWallet(foundWallet);
        walletRepository.updateWallet(foundToWallet);

        return fromBalance;
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        WalletDto foundWallet=walletRepository.getWalletById(walletId);
        if(foundWallet==null){
            throw new WalletException("wallet not found with given id");
        }

        return walletRepository.deleteWalletById(walletId);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        WalletDto foundWallet=walletRepository.getWalletById(walletId);
        if(foundWallet==null){
            throw new WalletException("wallet not found with given id");
        }
        return walletRepository.getWalletById(walletId);
    }

    @Override
    public WalletDto updateWallet(Integer walletId) throws WalletException {
        WalletDto foundWallet=walletRepository.getWalletById(walletId);
        if(foundWallet==null){
            throw new WalletException("wallet not found with given id");
        }
        return walletRepository.updateWallet(walletDto);
    }

    @Override
    public List<WalletDto> getAllWallets() {
        return null;
    }
}
