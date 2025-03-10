package com.akhil.trading.controller;

import com.akhil.trading.config.JwtConstant;
import com.akhil.trading.model.Order;
import com.akhil.trading.model.User;
import com.akhil.trading.model.Wallet;
import com.akhil.trading.model.WalletTransaction;
import com.akhil.trading.response.Response;
import com.akhil.trading.service.OrderService;
import com.akhil.trading.service.UserService;
import com.akhil.trading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/wallet")
    public ResponseEntity<Response>  getUserWallet(@RequestHeader(JwtConstant.JWT_HEADER) String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        Wallet wallet=walletService.getUserWallet(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.builder().data(wallet).build());
    }

    @PutMapping("/wallet/{walletId}/transfer")
    public ResponseEntity<Response> walletToWalletTransfer(@RequestHeader(JwtConstant.JWT_HEADER) String jwt
    , @PathVariable Long walletId, @RequestBody WalletTransaction request){
        User senderUser=userService.findUserProfileByJwt(jwt);
        Wallet receiverWallet=walletService.findWalletById(walletId);
        Wallet wallet=walletService.walletToWalletTransfer(senderUser,receiverWallet,request.getAmount());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.builder().data(wallet).build());
    }

    @PutMapping("/wallet/order/{orderId}/pay")
    public ResponseEntity<Response> payOrderPayment(@RequestHeader(JwtConstant.JWT_HEADER) String jwt
            , @PathVariable Long orderId){
        User user=userService.findUserProfileByJwt(jwt);
        Order order=orderService.getOrderById(orderId);
        Wallet wallet=walletService.payOrderPayment(order,user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Response.builder().data(wallet).build());

    }
}
