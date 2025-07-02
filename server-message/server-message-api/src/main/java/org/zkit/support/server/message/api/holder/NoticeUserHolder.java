package org.zkit.support.server.message.api.holder;

import java.util.List;

public class NoticeUserHolder {

    private static final ThreadLocal<List<Long>> holder = new ThreadLocal<>();

    public static void set(List<Long> ids) {
        holder.set(ids);
    }

    public static List<Long> get() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }

}
