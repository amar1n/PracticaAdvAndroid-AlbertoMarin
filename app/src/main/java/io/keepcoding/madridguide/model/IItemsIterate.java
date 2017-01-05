package io.keepcoding.madridguide.model;

import java.util.List;

public interface IItemsIterate<T> {
    public long size();

    public T get(long index);

    public List<T> allItems();
}
