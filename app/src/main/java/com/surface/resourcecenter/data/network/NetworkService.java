package com.surface.resourcecenter.data.network;

import android.app.Application;
import android.text.TextUtils;

import com.luck.picture.lib.tools.StringUtils;
import com.yanzhenjie.nohttp.BasicBinary;
import com.yanzhenjie.nohttp.Binary;
import com.yanzhenjie.nohttp.FileBinary;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public class NetworkService {

    public NetworkService(){
    }
    public static void initialize(Application application){
        NoHttp.initialize(application);
        Logger.setTag("NoHttp");
        Logger.setDebug(true);// 开始NoHttp的调试模式, 这样就能看到请求过程和日志
    }

    public void setRequestForJson(int what , String jsonBody, String url, CacheMode cacheMode,
                                  HttpListener<String> listener){
        if (TextUtils.isEmpty(jsonBody)) {
            Logger.i("提交的数据为空" + jsonBody);
        } else {
            Logger.i("提交的数据：" + jsonBody);
            Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
            request.setCacheMode(cacheMode);
            request.setDefineRequestBodyForJson(jsonBody);
            SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
            if (sslContext != null)
                request.setSSLSocketFactory(sslContext.getSocketFactory());
            request.setHostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
            CallServer.getInstance().request(what, request, listener);
        }
    }

    public void setRequestForFormData(int what , String jsonBody, String url, CacheMode cacheMode,
                                  HttpListener<String> listener){
        if (TextUtils.isEmpty(jsonBody)) {
            Logger.i("提交的数据为空" + jsonBody);
        } else {
//            Logger.i("提交的数据：" + jsonBody);
            Request<String> request = new StringRequest(url, RequestMethod.POST);
            request.add("jsonStr", jsonBody)
                    .setMultipartFormEnable(true); // 就多了这一句。
            SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
            if (sslContext != null)
                request.setSSLSocketFactory(sslContext.getSocketFactory());
            request.setHostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
            CallServer.getInstance().request(what, request, listener);
        }
    }

    public void setGetRequestForData(int what , Map<String, String> map, String url, CacheMode cacheMode,
                                      HttpListener<String> listener){

        url = getAppendUrl(url,map);
        Request<String> request = new StringRequest(url, RequestMethod.GET);
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null)
            request.setSSLSocketFactory(sslContext.getSocketFactory());
        request.setHostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        CallServer.getInstance().request(what, request, listener);
    }

    public void download(int what, DownloadRequest request, DownloadListener listener) {
        CallServer.getInstance().download(what, request, listener);
    }

    public void upload(int what,String url, File file,
                       HttpListener<String> listener){
        Request<String> request = new StringRequest(url, RequestMethod.POST);
        List<Binary> binaries = new ArrayList<>();
        BasicBinary binary1 = new FileBinary(file);
        binaries.add(binary1);
        SSLContext sslContext = SSLContextUtil.getDefaultSLLContext();
        if (sslContext != null)
            request.setSSLSocketFactory(sslContext.getSocketFactory());
        request.setHostnameVerifier(SSLContextUtil.HOSTNAME_VERIFIER);
        request.add("file", binaries);
        CallServer.getInstance().request(what, request, listener);

    }


    /**
     * @Description get请求URL拼接参数
     * @param url
     *            接口地址(无参数)
     * @param map
     *            拼接参数集合
     */
    private String getAppendUrl(String url, Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (TextUtils.isEmpty(buffer.toString())) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue());
            }
            url += buffer.toString();
        }
        return url;
    }

}
