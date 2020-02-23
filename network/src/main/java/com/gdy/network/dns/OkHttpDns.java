package com.gdy.network.dns;

import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.gdy.network.base.BaseApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

/**
 * Created by gongdongyang
 * on 2019/12/24
 */

public class OkHttpDns implements Dns {
    private static HttpDnsService httpDns;

    public OkHttpDns() {
        if (httpDns == null) {
            httpDns = HttpDns.getService(BaseApplication.getInstance(), "139541", "0aa7645566fee55e9e4d183c5aeeb303");
            httpDns.setCachedIPEnabled(true);
            httpDns.setLogEnabled(true);
            httpDns.setHTTPSRequestEnabled(true);
            httpDns.setExpiredIPEnabled(true);
        }
    }


    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (hostname.endsWith(".co")) {
            String str = httpDns.getIpByHostAsync(hostname);
            if (str != null)
                return Arrays.asList(InetAddress.getAllByName(str));
        }
        return Dns.SYSTEM.lookup(hostname);
    }
}
