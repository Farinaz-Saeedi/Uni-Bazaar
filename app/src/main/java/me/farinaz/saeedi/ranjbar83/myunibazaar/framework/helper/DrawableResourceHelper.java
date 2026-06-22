package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.helper;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class DrawableResourceHelper {

    public static Drawable getDrawableByName(Context context, String name) {
        if (context == null || name == null || name.isEmpty()) {
            return null;
        }

        String resourceName = name.startsWith("p") ? name : "p" + name;
        int resourceId = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());

        if (resourceId != 0) {
            try {
                return context.getResources().getDrawable(resourceId, null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        System.out.println("This is a utility class and should be used within an Android application context.");
        System.out.println("Example usage in an Activity:");
        System.out.println("  Drawable drawableP0 = DrawableResourceHelper.getDrawableByName(this, \"p0\");");
        System.out.println("  int p0DrawableId = DrawableResourceHelper.getDrawableIdByName(this, \"p0\");");
    }
}
