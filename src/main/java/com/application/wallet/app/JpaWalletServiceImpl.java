package com.application.wallet.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaWalletServiceImpl implements WalletService{
    @Autowired
    private JpaWalletRepository jpaWalletRepository;


    @Override
    public WalletDto registerWallet(WalletDto walletDto) throws WalletException {
        return this.jpaWalletRepository.save(walletDto);
    }

    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException {
        return null;
    }

    @Override
    public Double withdrawByWalletId(Integer walletId, Double amount) throws WalletException {
        return null;
    }

    @Override
    public Double fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {
        return null;
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.jpaWalletRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet could not be Deleted, not found id:"+walletId);
        WalletDto foundWallet = walletOptional.get();
        this.jpaWalletRepository.delete(foundWallet);
        return foundWallet;
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.jpaWalletRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("wallet could not be found id:"+walletId);

        return walletOptional.get();
    }

    @Override
    public WalletDto updateWallet(Integer walletId) throws WalletException {
        return null;
    }

//    @Override
//    public WalletDto updateWallet(Integer walletId) throws WalletException {
//        Optional<WalletDto> walletOptional = this.jpaWalletRepository.findById(walletId);
//        if(walletOptional.isEmpty())
//            throw new WalletException("wallet could not be found id:"+walletId);
//        return this.updateWallet(walletId);
//    }

    @Override
    public List<WalletDto> getAllWallets() {
        return this.jpaWalletRepository.findAll();
    }
}
