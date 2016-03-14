// -*- Mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
//
// Copyright (C) 2015 Testin.  All rights reserved.
//
// This file is an original work developed by Testin

package com.easyapi.apm.demo;

import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedByteArray;


/**
 * Test for Retrofit
 */
public class RetrofitTest extends HttpTest {
    public static final int SC_OK = 200;

    private Response mResponse;
    private RestAdapter mAdapter;


    RetrofitTest() {
    }

    @Override
    void postData() throws Exception {
        super.postData();

        mAdapter = new RestAdapter.Builder().setEndpoint(mUrl).build();
        SimplePOST create = mAdapter.create(SimplePOST.class);
        mStartTime = System.currentTimeMillis();
        String json = "{\"foo\":\"kit\",\"bar\":\"kat\"}";
        TypedInput in = new TypedByteArray("application/json", json.getBytes("UTF-8"));
        mResponse = create.postRawJson(in);
        //mResponse = create.getResponse();
        mEndTime = System.currentTimeMillis();

        mStatusCode = mResponse.getStatus();
        if (SC_OK == mStatusCode) {
            mResponseDataSize = mResponse.getBody().in().available();
        }
    }

    @Override
    void getData() throws Exception {
        super.getData();

        mAdapter = new RestAdapter.Builder().setEndpoint(mUrl).build();
        SimpleGET create = mAdapter.create(SimpleGET.class);
        mStartTime = System.currentTimeMillis();
        mResponse = create.getResponse();
        mEndTime = System.currentTimeMillis();

        mStatusCode = mResponse.getStatus();
        if (SC_OK == mStatusCode) {
            mResponseDataSize = mResponse.getBody().in().available();
        }
    }

    interface SimpleGET {
        @GET("/")
        Response getResponse();
    }

    interface SimplePOST {
        @POST("/jayson")
        Response postRawJson(@Body TypedInput body);
    }
}
