package io.keepcoding.madridguide.model;

public interface IShopsUpdate {
    public void add(Shop shop);
    public void delete(Shop shop);
    public void edit(Shop shop, long index);
}
