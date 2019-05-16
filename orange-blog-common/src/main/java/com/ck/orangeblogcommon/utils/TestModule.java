package com.ck.orangeblogcommon.utils;

import com.alibaba.fastjson.JSONObject;

public class TestModule {

    public static JSONObject testModule(){
        JSONObject resObject = new JSONObject();
        resObject.put("id", "123456789");
        resObject.put("value", "正在测试");
        return resObject;
    }
}
