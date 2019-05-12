package com.chen.myui.views;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactTextView;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MyButton extends SimpleViewManager<Button> {
    private final static String EVENT_NAME_ONCLICK_NATIVE = "topChange";
    private final static String EVENT_NAME_ONCLICK_JS = "onChange";

    private Button myButton;
    @Nonnull
    @Override
    public String getName() {
        return "MyButton";
    }

    @Override
    protected Button createViewInstance(ThemedReactContext reactContext) {
        myButton = new Button(reactContext);
        return myButton;
    }

    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext,final @Nonnull Button view) {
        super.addEventEmitters(reactContext, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritableMap data = Arguments.createMap();
                data.putString("msg", "点击按钮2");
                reactContext
                        .getJSModule(RCTEventEmitter.class)
                        .receiveEvent(
                                v.getId(),
                                EVENT_NAME_ONCLICK_NATIVE,
                                data
                        );
            }
        });
    }

    @ReactProp(name = "text")
    public void setText(Button button, String text) {
        button.setText(text);
    }

    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        EVENT_NAME_ONCLICK_NATIVE,
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                EVENT_NAME_ONCLICK_JS
//                                MapBuilder.of("bubbled", EVENT_NAME_ONCLICK_JS)
                        )
                ).build();
    }
}
