package com.akhil.trading.service;

import com.akhil.trading.model.Order;
import com.akhil.trading.model.User;
import com.akhil.trading.model.Wallet;

import java.math.BigDecimal;

public interface WalletService {
  Wallet getUserWallet(User user);
  Wallet addBalance(Wallet wallet, BigDecimal money);
  Wallet findWalletById(Long id);
  Wallet walletToWalletTransfer(User sender,Wallet recieverWallet,BigDecimal amount);
  Wallet payOrderPayment(Order order, User user);
}
