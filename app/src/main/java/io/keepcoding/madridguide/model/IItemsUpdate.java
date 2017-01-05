package io.keepcoding.madridguide.model;

public interface IItemsUpdate<T> {
    public void add(T item);

    public void delete(T item);

    public void edit(T item, long index);
}
