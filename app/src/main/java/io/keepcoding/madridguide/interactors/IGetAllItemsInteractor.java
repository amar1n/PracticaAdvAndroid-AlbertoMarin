package io.keepcoding.madridguide.interactors;

import android.content.Context;

public interface IGetAllItemsInteractor<T> {
    void execute(final Context context, final GetAllItemsInteractorResponse<T> response);
}