package io.keepcoding.madridguide.util;

// Listener interface

import android.support.annotation.NonNull;

public interface OnElementClick<T> {
    public void clickedOn(@NonNull T element, int position);
}