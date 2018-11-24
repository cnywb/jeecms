package com.jeecms.common.sms;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;


import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

/**
 * 避免HttpClient的”SSLPeerUnverifiedException: peer not authenticated”异常
 * 不用导入SSL证书
 * 
 *
 */
public class HttpsClientWrapper {

    public static org.apache.http.client.HttpClient wrapClient(org.apache.http.client.HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", ssf, 8443));
            ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(registry);
            return new DefaultHttpClient(mgr, base.getParams());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}