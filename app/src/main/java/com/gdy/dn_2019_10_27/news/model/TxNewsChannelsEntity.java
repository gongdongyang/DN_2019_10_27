package com.gdy.dn_2019_10_27.news.model;

import com.gdy.dn_2019_10_27.api.NewsChannelsBean;
import com.gdy.network.bean.TecentBaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public class TxNewsChannelsEntity extends TecentBaseResponse {

    @SerializedName("showapi_res_body")
    @Expose
    public ShowapiResBody showapiResBody;

    public class ShowapiResBody {
        @SerializedName("ret_code")
        @Expose
        public Integer retCode;

        @SerializedName("pagebean")
        @Expose
        public PageBean pageBean;


    }

    public class PageBean{

        @SerializedName("allPages")
        @Expose
        public Integer allPages;


        @SerializedName("contentlist")
        @Expose
        public List<NewsContentEntitiy> contentlist = null;
    }



}
