package io.keepcoding.madridguide.util;

// Listener interface

import android.support.annotation.NonNull;

public interface OnElementClick<T> {
    void clickedOn(@NonNull T element, int position);
}