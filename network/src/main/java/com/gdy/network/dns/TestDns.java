package com.gdy.network.dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Dns;

/**
 * Created by gongdongyang
 * on 2019/12/15
 */
public class TestDns  implements Dns {

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {

        List<InetAddress> inetAddressList = new ArrayList<>();
        List<InetAddress> hostNameInetAddresses = null;

        try{
            hostNameInetAddresses = Dns.SYSTEM.lookup(hostname);
        }catch (Exception e){

        }

        if(hostNameInetAddresses != null && hostNameInetAddresses.size() > 0) {
            inetAddressList.addAll(hostNameInetAddresses);
        }


        try {
            inetAddressList.add(InetAddress.getByName("111.231.97.251"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return inetAddressList;
    }
}
