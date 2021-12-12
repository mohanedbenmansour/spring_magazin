package tn.esprit.spring.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.service.*;

@RestController
@RequestMapping("/stocks")
public class StockRestController {

	@Autowired
	IStockService stockService ;
	
	@GetMapping("/get-one/{stock-id}")
	@ResponseBody
	public Stock getStock(@PathVariable("stock-id")Long stockId) {
		return stockService.retrieveStock(stockId);
	}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-all")
	@ResponseBody
	public List<Stock> getStocks() {
		return stockService.retrieveAllStocks();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	@ResponseBody
	public Stock addStock(@RequestBody Stock stock) {
		return stockService.addStock(stock);
	}
	
	@PutMapping("/modify")
	@ResponseBody
	public Stock updateStock(@RequestBody Stock stock) {
		return stockService.updateStock(stock);
	}
	
	@DeleteMapping("/remove/{stock-id}")
	@ResponseBody
	public void deleteStock(@PathVariable("stock-id")Long stockId)
	{
		stockService.deleteStock(stockId);
	}
	
	
}
