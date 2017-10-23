package com.apress.prospring4.ch3.annotation;

import com.apress.prospring4.ch3.Oracle;
import org.springframework.stereotype.Service;

@Service("oracle2")
public class BookwormOracleService implements Oracle {
    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - use the Internet";
    }
}

