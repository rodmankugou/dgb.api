package com.verificer.biz.biz.service.core.goods;

import com.verificer.biz.beans.enums.MerType;
import com.verificer.biz.biz.entity.AdjustItem;
import com.verificer.biz.biz.entity.AdjustOrder;
import com.verificer.biz.biz.service.adj.AdjOrderService;
import com.verificer.biz.biz.service.adj.notify.IAdjustListener;
import com.verificer.biz.biz.service.adj.notify.events.AdjEvent;
import com.verificer.biz.biz.service.adj.notify.events.AdjSucEvent;
import com.verificer.biz.biz.service.core.goods.notify.IGoodsPublishListener;
import com.verificer.biz.biz.service.core.goods.notify.PublishGoodsEvent;
import com.verificer.designpatterns.listener.ConcurrentNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Component
public class GoodsPublishService {
    @Autowired
    AdjOrderService adjOrderService;

    private ConcurrentNotifier<IGoodsPublishListener, PublishGoodsEvent> notifier = new ConcurrentNotifier<IGoodsPublishListener, PublishGoodsEvent>() {
        @Override
        protected void trigger(IGoodsPublishListener listener, PublishGoodsEvent event) {
            listener.onEvent(event);
        }
    };

    public void addListener(IGoodsPublishListener listener){
        notifier.addListener(listener);
    }

    @PostConstruct
    void init(){
        adjOrderService.addListener(new IAdjustListener() {
            @Override
            public void onEvent(AdjEvent event) {

            }
        });
    }


    private void handleAdjEvent(AdjEvent event){
        if(!(event instanceof AdjSucEvent))
            return;

        AdjSucEvent e = (AdjSucEvent) event;
        AdjustOrder o = e.getOrder();
        List<AdjustItem> items = e.getItems();

        MerType merType = null;
        if(adjOrderService.isToShop(o)){
            merType = MerType.SHOP;
        }else if(adjOrderService.isToStage(o)) {
            merType = MerType.STAGE;
        }else{
            return;
        }

        for(AdjustItem item : items){
            if(item.getRealCount().compareTo(BigDecimal.ZERO) > 0)
                notifier.triggerAll(new PublishGoodsEvent(item.getGoodsId(),item.getSpecId(),merType,o.getToId()));
        }
    }


}
