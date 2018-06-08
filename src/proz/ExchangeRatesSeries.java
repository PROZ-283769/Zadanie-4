package proz;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRatesSeries {

	private String table;

	private String currency;

	private String code;


	private List<Rate> Rates = new ArrayList<Rate>();

	@XmlElement(name = "Table")
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	@XmlElement(name = "Currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@XmlElement(name = "Code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElementWrapper(name = "Rates")
	@XmlElement(name = "Rate")
	public List<Rate> getRates() {
		return Rates;
	}

	public void setRates(List<Rate> rates) {
		Rates = rates;
	}

	public Double getAverage(String table) {
		double sum = 0;
		if (!Rates.isEmpty())
			for (Rate r : Rates) {
				if(table.toString().equals("C"))
					sum += r.getAsk();
				else 
					sum += r.getMid();
				
			}

		return (double) Math.round(sum / Rates.size() * 1000) / 1000;

	}

}
