package com.asi1.GameCard.trading.repository;

import com.asi1.GameCard.trading.model.Trading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingRepository extends CrudRepository<Trading, Long> {
}
