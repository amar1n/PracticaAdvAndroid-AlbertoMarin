package io.keepcoding.madridguide.model;

import java.util.List;

public interface IShopsIterate {
    public long size();
    public Shop get(long index);
    public List<Shop> allShops();
}
