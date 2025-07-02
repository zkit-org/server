package org.zkit.support.server.message.api.holder;

import com.alibaba.fastjson2.JSONObject;

public class MetadataHolder {

    private static final ThreadLocal<JSONObject> holder = new ThreadLocal<>();

    public static void set(JSONObject metadata) {
        holder.set(metadata);
    }

    public static JSONObject get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
