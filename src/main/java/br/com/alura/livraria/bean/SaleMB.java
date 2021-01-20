package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.alura.livraria.model.Book;
import br.com.alura.livraria.model.Sale;
import br.com.alura.livraria.service.BookService;
import br.com.alura.livraria.service.SaleService;
import br.com.alura.livraria.util.Messages;

@Named("saleMB")
@SessionScoped
public class SaleMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SaleService saleService;
	
	@Inject
	private BookService bookService;
	
	@Inject
	private Sale sale;
	
	public String persist() {
		if(sale.getBook() == null || sale.getQuantity() == null || sale.getQuantity() < 1) {
			Messages.addError(null, "É necessário escolher um livro para realizar a compra.", null);
			return "";
		}
		saleService.insert(sale);
		this.clear();
		return new Redirection("/livro.xhtml").toString();
	}
	
	private void clear() {
		this.sale = new Sale();
	}

	public List<Sale> getSales(){
		List<Sale> sales = saleService.getAll();
		return sales;
	}
	
	public BarChartModel getSalesModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		
		ChartSeries salesSerie = new ChartSeries();
		List<Sale> sales = getSales();
		Map<Object, Number> booksQuantity = new HashMap<>();
		sales.forEach(s -> {
			String bookTitle = s.getBook().getTitle();
			if(booksQuantity.containsKey(bookTitle)) {
				booksQuantity.put(bookTitle, booksQuantity.get(bookTitle).intValue() + s.getQuantity());
			} else {
				booksQuantity.put(bookTitle, s.getQuantity());
			}
		});
		salesSerie.setData(booksQuantity);
		
		ChartSeries salesValueSerie = new ChartSeries();
		
		Map<Object, Number> booksTotalValueSold = new HashMap<>();
		sales.forEach(s -> {
			String bookTitle = s.getBook().getTitle();
			if(booksTotalValueSold.containsKey(bookTitle)) {
				booksTotalValueSold.put(bookTitle, ((BigDecimal)booksTotalValueSold.get(bookTitle)).add(s.getTotalValue()));
			} else {
				booksTotalValueSold.put(bookTitle, s.getTotalValue());
			}
		});
		salesValueSerie.setData(booksTotalValueSold);
		
		model.addSeries(salesSerie);
		model.addSeries(salesValueSerie);
		
	    Axis xAxis = model.getAxis(AxisType.X);
	    xAxis.setLabel("Título");

	    Axis yAxis = model.getAxis(AxisType.Y);
	    yAxis.setLabel("Quantidade/Valor total de vendas");
		
		return model;
	}

	public Book getBook() {
		System.out.println("SaleMB getting book: " + getSale().getBook());
		return getSale().getBook();
	}

	public String setBook(Book book) {
		System.out.println("SaleMB setting book: " + book.getTitle());
		
		this.getSale().setBook(bookService.findByIdWithAuthors(book.getId()));
		System.out.println("SaleMB setted book: " + this.getSale().getBook().getTitle());
		return new Redirection("/comprar.xhtml").toString();
	}

	public Sale getSale() {
		return sale;
	}
	

}
