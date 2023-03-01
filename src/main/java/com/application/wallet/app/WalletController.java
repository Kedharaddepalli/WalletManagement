package com.application.wallet.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping
public class WalletController {
    @Autowired
    private WalletService walletService;


    @GetMapping("/wallet/{id}")
    public WalletDto getWalletById(@PathVariable Integer id) throws WalletException {
        return walletService.getWalletById(id);
    }

    @PostMapping("/wallet")
    public WalletDto addResource(@RequestBody WalletDto walletDto) throws WalletException {
        return walletService.registerWallet(walletDto);
    }

    @PutMapping("/wallet")
    public WalletDto replaceResource(@RequestBody WalletDto walletDto) throws WalletException {
        return walletService.updateWallet(walletDto.getWalletId());
    }

    @DeleteMapping("/wallet/{id}")
    public WalletDto deleteResource(@PathVariable Integer id) throws WalletException {
        return walletService.deleteWalletById(id);
    }

    @PatchMapping("/wallet/balance/{id}/{addFunds}")
    public String updateResource(@PathVariable Integer id, @PathVariable Double addFunds) throws WalletException {
        WalletDto walletDto = new WalletDto(1, "Kedhar", 100.0);
        Double balance = walletService.addFundsToWalletById(id, addFunds);
        return "balance is " + balance;
    }

    @PatchMapping("/wallet/withDraw/{id}/{withDrawFunds}")
    public String updateResourceToWithDraw(@PathVariable Integer id, @PathVariable Double withDrawFunds) throws WalletException {
        Double balance = walletService.withdrawByWalletId(id, withDrawFunds);
        return "balance is " + balance;
    }

    @PatchMapping("/wallet/transfer/{id1}/{id2}/{transferFunds}")
    public String transferFunds(@PathVariable Integer id1, @PathVariable Integer id2, @PathVariable Double transferFunds) throws WalletException {
        Double balance = walletService.fundTransfer(id1, id2, transferFunds);
        return "balance is "+balance;

    }

    @Autowired
    private JpaWalletRepository jpaWalletRepository;
    @GetMapping("wallet/name/{name}")
    public List<WalletDto> getAllWalletsHavingName(@PathVariable("name") String name){
        return this.jpaWalletRepository.findByName(name);
    }
    @GetMapping("wallet/contain/{name}")
    public List<WalletDto> getAllWalletsContainingName(@PathVariable("name") String name){
        return this.jpaWalletRepository.findByNameContaining(name);
    }

    @GetMapping("wallet/balance/{minBalance}/{maxBalance}")
    public List<WalletDto> findAllWalletsHavingBalanceBetween(@PathVariable("minBalance") Double minBalance,
                                                                 @PathVariable("maxBalance")Double maxBalance){
        return this.jpaWalletRepository.findByBalanceBetweenOrderByBalanceDesc(minBalance,maxBalance);

    }

    @GetMapping("custom/wallets")
    public List<WalletDto> findAllWallets(){
        return this.jpaWalletRepository.getAllWallets();
    }

    @GetMapping("custom/wallets/{name}")
    public List<WalletDto> findAllWalletsHavingName(@PathVariable("name") String name){
        return this.jpaWalletRepository.getGetAllWalletsHavingNameLike("%"+name+"%");
    }

}
