package com.akhil.trading.service;

import com.akhil.trading.model.Asset;
import com.akhil.trading.model.Coin;

import java.util.List;

public interface AssetService {
    void createAsset(Long userId, Coin coin, double quantity);

    Asset getAssetById(Long assetId);

    Asset getAssetByUserIdAndId(Long userId,Long assetId);

    List<Asset> getUsersAssets(Long userId);

    Asset updateAsset(Long assetId,double quantity);

    Asset findAssetByUserIdAndCoinId(Long userId,String coinId);

    void deleteAsset(Long assetId);
}
