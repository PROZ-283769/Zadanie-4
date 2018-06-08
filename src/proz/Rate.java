package proz;

import javax.xml.bind.annotation.XmlElement;

public class Rate {
	@XmlElement(name = "Mid")
	private double Mid;
	@XmlElement(name = "Ask")
	private double Ask;

	public double getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = Double.parseDouble(mid);
	}

	public double getAsk() {
		return Ask;
	}

	public void setAsk(String ask) {
		Ask = Double.parseDouble(ask);
	}

}
