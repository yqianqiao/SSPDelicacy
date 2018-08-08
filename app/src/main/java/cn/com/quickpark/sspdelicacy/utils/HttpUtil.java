package cn.com.quickpark.sspdelicacy.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by y on 2018/7/16.
 */

public class HttpUtil {

    /**
     * 对象转化成map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject;
        String jsonString = JSON.toJSONString(obj);

//        Log.d(TAG, "objectToMap:" + jsonString);
        try {
            jsonObject = new JSONObject(jsonString);
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value;
                try {
                    value = jsonObject.get(key).toString();
                    map.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
}
