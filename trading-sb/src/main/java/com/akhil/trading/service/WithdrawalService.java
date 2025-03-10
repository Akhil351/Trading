package com.akhil.trading.service;

import com.akhil.trading.model.User;
import com.akhil.trading.model.Withdrawal;

import java.math.BigDecimal;
import java.util.List;

public interface WithdrawalService {
    Withdrawal requestWithdrawal(BigDecimal amount, User user);

    Withdrawal procedWithwithdrawal(Long withdrawalId,boolean accept);

    List<Withdrawal> getUsersWithdrawalHistory(User user);

    List<Withdrawal> getAllWithdrawalRequest();
}
