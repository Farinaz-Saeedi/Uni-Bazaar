package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper;

import android.content.Context;

public class ResourceMapper {

    public static int getDrawableResourceByNameWithContext(Context context, String resourceName) {
        if (context == null || resourceName == null) {
            return 0;
        }
        int resourceId = context.getResources().getIdentifier(
                resourceName,
                "drawable",
                context.getPackageName());
        return resourceId;
    }
}
