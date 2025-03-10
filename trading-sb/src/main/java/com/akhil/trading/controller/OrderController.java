package com.akhil.trading.controller;







import com.akhil.trading.config.JwtConstant;
import com.akhil.trading.exception.ApiException;
import com.akhil.trading.model.Coin;
import com.akhil.trading.model.Order;
import com.akhil.trading.model.User;
import com.akhil.trading.request.CreateOrderRequest;
import com.akhil.trading.response.Response;
import com.akhil.trading.service.CoinService;
import com.akhil.trading.service.OrderService;
import com.akhil.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CoinService coinService;




    @PostMapping("/pay")
    public ResponseEntity<Response> payOrderPayment(
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt,
            @RequestBody CreateOrderRequest req

    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Coin coin =coinService.findById(req.getCoinId());


        Order order = orderService.processOrder(coin,req.getQuantity(),req.getOrderType(),user);

        return ResponseEntity.ok(Response.builder().data(order).build());

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Response> getOrderById(
            @RequestHeader(JwtConstant.JWT_HEADER) String jwtToken,
            @PathVariable Long orderId
    )  {
        if (jwtToken == null) {
            throw new ApiException("token missing...");
        }

        User user = userService.findUserProfileByJwt(jwtToken);

        Order order = orderService.getOrderById(orderId);
        if (order.getUserId().equals(user.getId())) {
            return ResponseEntity.ok(Response.builder().data(order).build());
        } else {
           throw new ApiException("you don't have access");
        }
    }

    @GetMapping
    public ResponseEntity<Response> getAllOrdersForUser(
            @RequestHeader(JwtConstant.JWT_HEADER) String jwtToken,
            @RequestParam(required = false) String order_type,
            @RequestParam(required = false) String asset_symbol
    )  {
        if (jwtToken == null) {
            throw new ApiException("token missing...");
        }

        Long userId = userService.findUserProfileByJwt(jwtToken).getId();

        List<Order> userOrders = orderService.getAllOrderOfUser(userId,order_type,asset_symbol);
        return ResponseEntity.ok(Response.builder().data(userOrders).build());
    }




}

