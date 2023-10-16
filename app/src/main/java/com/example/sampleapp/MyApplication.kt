package com.example.sampleapp

import android.app.Application
import android.util.Log
import zendesk.android.Zendesk
import zendesk.messaging.android.DefaultMessagingFactory

class MyApplication: Application() {

    companion object {
        private const val ZENDESK_CHANNEL_ID = "channel-id"
    }

    override fun onCreate() {
        super.onCreate()

        // This method is the one logging the SocketTimeoutException
        Zendesk.initialize(
            context = this,
            channelKey = ZENDESK_CHANNEL_ID,
            successCallback = {
                Log.v("MyApplication", "Init zendesk successful")
            },
            failureCallback = { error ->

                Log.e("MyApplication", "MessagingManager::setup::initialize ${error.message}")
            },
            messagingFactory = DefaultMessagingFactory()
        )

        /***
         * Non-fatal Exception: ww.d$b: Zendesk failed to initialize.
            at zendesk.android.settings.internal.SettingsRepository.fetchSettings$zendesk_zendesk_android(SettingsRepository.kt:50)
            at zendesk.android.settings.internal.SettingsRepository$fetchSettings$1.invokeSuspend(SettingsRepository.kt:11)
            at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
            at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:104)
            at android.os.Handler.handleCallback(Handler.java:883)
            at android.os.Handler.dispatchMessage(Handler.java:100)
            at android.os.Looper.loop(Looper.java:214)
            at android.app.ActivityThread.main(ActivityThread.java:7356)
            at java.lang.reflect.Method.invoke(Method.java)
            at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
            at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
         *
         * Caused by java.net.SocketTimeoutException: failed to connect to ****.zendesk.com/104.16.51.111 (port 443) from /10.132.83.154 (port 37468) after 10000ms
            at libcore.io.IoBridge.connectErrno(IoBridge.java:191)
            at libcore.io.IoBridge.connect(IoBridge.java:135)
            at java.net.PlainSocketImpl.socketConnect(PlainSocketImpl.java:142)
            at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:390)
            at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:230)
            at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:212)
            at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:436)
            at java.net.Socket.connect(Socket.java:621)
            at okhttp3.internal.platform.Platform.connectSocket(Platform.kt:128)
            at okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.kt:295)
            at okhttp3.internal.connection.RealConnection.connect(RealConnection.kt:207)
            at okhttp3.internal.connection.ExchangeFinder.findConnection(ExchangeFinder.kt:226)
            at okhttp3.internal.connection.ExchangeFinder.findHealthyConnection(ExchangeFinder.kt:106)
            at okhttp3.internal.connection.ExchangeFinder.find(ExchangeFinder.kt:74)
            at okhttp3.internal.connection.RealCall.initExchange$okhttp(RealCall.kt:255)
            at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.kt:32)
            at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109)
            at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.kt:95)
            at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109)
            at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.kt:83)
            at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109)
            at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.kt:76)
            at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109)
            at zendesk.android.internal.ZendeskLoggingInterceptor.intercept(ZendeskLoggingInterceptor.kt:14)
            at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109)
            at zendesk.okhttp.HeaderInterceptor.intercept(HeaderInterceptor.kt:36)
            at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.kt:109)
            at okhttp3.internal.connection.RealCall.getResponseWithInterceptorChain$okhttp(RealCall.kt:201)
            at okhttp3.internal.connection.RealCall$AsyncCall.run(RealCall.kt:517)
            at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
            at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
            at java.lang.Thread.run(Thread.java:919)
             */
    }
}