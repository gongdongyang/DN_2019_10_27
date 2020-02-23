package com.gdy.bitmax.market.model;


import com.gdy.bitmax.base.BtmxBaseResponse;

import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */
public class MarketAllEntity extends BtmxBaseResponse {

    public List<MarketItemEntity> data;

    public class MarketItemEntity{
        public String m;
        public String s;
        public String ba;
        public String qa;
        public String i;
        public String t;
        public String o;
        public String c;
        public String h;
        public String l;
        public String v;
    }

}
