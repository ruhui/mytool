package com.ruhui.network;

import android.text.TextUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Dns;

/**
*  描述：如果服务的有配置ipv6，默认会先调用ipv6的DNS解析，这里把ipv4的DNS解析排到第一位，后会自动选择第一位的解析
*  创建作者：androidrh
*  创建时间：2019/3/13
*/
public class HandleDns implements Dns {

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (TextUtils.isEmpty(hostname)){
            throw new UnknownHostException("hostname == null");
        }else{
            try{
                List<InetAddress> inetAddressesList = new ArrayList<>();
                InetAddress[] inetAddresses = InetAddress.getAllByName(hostname);
                for (InetAddress inetAddress : inetAddresses){
                    if (inetAddress instanceof Inet4Address){
                        inetAddressesList.add(0, inetAddress);
                    }else{
                        inetAddressesList.add(inetAddress);
                    }
                }
                return inetAddressesList;
            }catch (NullPointerException var4){
                UnknownHostException unknownHostException = new UnknownHostException("Brokeen system behaviour for...");
                unknownHostException.initCause(var4);
                throw unknownHostException;
            }

        }
    }
}
