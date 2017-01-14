package io.keepcoding.madridguide.interactors;

public interface GetAllItemsInteractorResponse<T> {
    void response(T items, boolean newItems);
}
