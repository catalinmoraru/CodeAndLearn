package com.apress.prospring4.ch41.highschool;

import com.apress.prospring4.ch41.Food;
import com.apress.prospring4.ch41.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
        @Override
        public List<Food> provideLunchSet() {
            List<Food> lunchSet = new ArrayList<Food>();
            lunchSet.add(new Food("Coke"));
            lunchSet.add(new Food("Hamburger"));
            lunchSet.add(new Food("French Fries"));

            return lunchSet;
        }
    }

}
