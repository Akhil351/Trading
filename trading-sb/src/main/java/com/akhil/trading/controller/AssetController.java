package com.akhil.trading.controller;




import com.akhil.trading.model.Asset;
import com.akhil.trading.model.User;
import com.akhil.trading.response.Response;
import com.akhil.trading.service.AssetService;
import com.akhil.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;
    @Autowired
    private UserService userService;


    @GetMapping("/{assetId}")
    public ResponseEntity<Response> getAssetById(@PathVariable Long assetId) {
        Asset asset = assetService.getAssetById(assetId);
        return ResponseEntity.ok().body(Response.builder().data(asset).build());
    }

    @GetMapping("/coin/{coinId}/user")
    public ResponseEntity<Response> getAssetByUserIdAndCoinId(
            @PathVariable String coinId,
            @RequestHeader("Authorization") String jwt
    )  {

        User user=userService.findUserProfileByJwt(jwt);
        Asset asset = assetService.findAssetByUserIdAndCoinId(user.getId(), coinId);
        return ResponseEntity.ok().body(Response.builder().data(asset).build());
    }

    @GetMapping
    public ResponseEntity<Response> getAssetsForUser(
            @RequestHeader("Authorization") String jwt
    ) {
        User user=userService.findUserProfileByJwt(jwt);
        List<Asset> assets = assetService.getUsersAssets(user.getId());
        return ResponseEntity.ok().body(Response.builder().data(assets).build());
    }
}
