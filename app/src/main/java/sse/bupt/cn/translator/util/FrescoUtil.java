package sse.bupt.cn.translator.util;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.net.URI;

public class FrescoUtil {
    private static void updateViewSize(SimpleDraweeView draweeView, @Nullable Object info) {
        ImageInfo imageInfo = (ImageInfo) info;
        if (imageInfo != null) {
            draweeView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            draweeView.getLayoutParams().width = imageInfo.getWidth();
            draweeView.setAspectRatio((float) (imageInfo.getWidth() / imageInfo.getHeight()));
        }
    }

    public static DraweeController getController(final SimpleDraweeView draweeView, Uri uri) {
        ControllerListener listener = new BaseControllerListener() {
            @Override
            public void onIntermediateImageSet(String id, @Nullable Object imageInfo) {
                updateViewSize(draweeView, imageInfo);
            }

            @Override
            public void onFinalImageSet(String id, @Nullable Object imageInfo, @Nullable Animatable animatable) {
                updateViewSize(draweeView, imageInfo);
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(listener)
                .build();
        return controller;
    }

}
