package service;

import repository.MenuRepository;

import model.MenuItem;
import java.util.stream.Collectors;
import java.util.List;

public class SearchService implements ISearchService {



    @Override
    public List<MenuItem> findByName(String name) {
        return MenuRepository.menuItems.stream()
                .filter(element -> element.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItem> findByPriceRange(double minPrice, double maxPrice) {

        return MenuRepository.menuItems.stream()
                .filter(element -> element.getBasePrice() >= minPrice
                        && element.getBasePrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
