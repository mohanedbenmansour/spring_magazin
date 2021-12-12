package tn.esprit.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.repository.*;
@Service
public class StockService implements IStockService{

	
	@Autowired
	StockRepository stockRepository;
	
	@Override
	public List<Stock> retrieveAllStocks() {	
		return stockRepository.findAll();
	}

	@Override
	public Stock addStock(Stock s) {
		stockRepository.save(s);
		return s ;
	}

	@Override
	public Stock updateStock(Stock s) {
		stockRepository.save(s);
		return s;
	}

	@Override
	public Stock retrieveStock(Long id) {
		return stockRepository.findById(id).get()	;
		}


	@Override
	public void deleteStock(Long id) {
		stockRepository.deleteById(id);
		
	}

	@Scheduled(cron = "5/60 * * * * *")
	@Override
	public void getStocksWithWarnings() {
		List<Stock> list =stockRepository.getStocksWithWarnings();
		for (Stock stock : list) {
			if(stock.getQte()<=stock.getQteMin()) {
				System.out.println(stock.getLibelleStock() + "  is out of stock");
			}
		}
	}
	
	

}
