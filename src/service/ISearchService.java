package service;

import model.MenuItem;

import java.util.List;

public interface ISearchService {
    List<MenuItem> findByName(String name);
    List<MenuItem> findByPriceRange(double minPrice, double maxPrice);
}
