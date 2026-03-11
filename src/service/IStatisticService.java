package service;

import model.MenuItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IStatisticService {
    double calculatedTotalRevenue(int month);
    Map<MenuItem,Integer> getTopSellingItems();
}
