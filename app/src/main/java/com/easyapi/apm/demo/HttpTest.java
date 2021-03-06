// -*- Mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
//
// Copyright (C) 2015 Testin.  All rights reserved.
//
// This file is an original work developed by Testin

package com.easyapi.apm.demo;

import java.io.InputStream;
import java.net.URL;

/**
 * Base class for Http test
 */
public abstract class HttpTest {
    protected static int BUF_SIZE = 16384;
    protected String mUrl;
    protected String mProtocol;
    protected String mOpType;
    protected long mStartTime;
    protected long mEndTime;
    protected int mStatusCode;
    protected long mRequestDataSize;
    protected long mResponseDataSize;

    void resetData() {
        mStartTime = 0;
        mEndTime = 0;
        mStatusCode = 0;
        mRequestDataSize = 0;
        mResponseDataSize = 0;
    }

    void setUrl(String url) throws Exception {
        mUrl = url;
        URL l = new URL(url);
        mProtocol = l.getProtocol();
    }

    void postData() throws Exception {
        mOpType = "post";
        resetData();
    }

    void getData() throws Exception {
        mOpType = "get";
        resetData();
    }

    void readStream(InputStream in) throws Exception {
        byte[] buf = new byte[BUF_SIZE];
        int n;
        while ((n = in.read(buf)) != -1);
    }

    String getStatisticData() {
        return "URL: " + mUrl + "\r\n" +
                "PROTOCOL: " + mProtocol + "\r\n" +
                "TYPE: " + mOpType + "\r\n" +
                "LATENCY: " + (mEndTime - mStartTime) + "\r\n" +
                "STATUS_CODE: " + mStatusCode + "\r\n" +
                "REQUEST_SIZE: " + mRequestDataSize + "\r\n" +
                "RESPONSE_SIZE: " + mResponseDataSize;
    }
}
