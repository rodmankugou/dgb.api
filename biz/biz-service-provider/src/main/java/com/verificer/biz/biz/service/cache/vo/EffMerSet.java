package com.verificer.biz.biz.service.cache.vo;

import java.util.List;

public class EffMerSet {
    private List<CacShop> shops;
    private List<CacStage> stages;

    public EffMerSet(List<CacShop> shops, List<CacStage> stages) {
        this.shops = shops;
        this.stages = stages;
    }

    public List<CacShop> getShops() {
        return shops;
    }

    public List<CacStage> getStages() {
        return stages;
    }
}
