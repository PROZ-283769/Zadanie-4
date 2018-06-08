package proz;

import java.io.StringReader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

@Path("/exchangerates/rates")
public class AverageExchangeRate {

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_HTML)
	public String getHTMLAnswer(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		return "<html><body><h1>" + getExchangeRateSeriesAverage(table, code, topCount) + "</h1></body></html>";
	}

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTXTAnswer(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		return Double.toString(getExchangeRateSeriesAverage(table, code, topCount));
	}

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getJSONAnswer(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		JsonObjectBuilder jsBuilder = Json.createObjectBuilder();
		jsBuilder.add("average rate", getExchangeRateSeriesAverage(table, code, topCount));
		JsonObject js = jsBuilder.build();
		return js.toString();

	}

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_XML)
	public String getXMLAnswer(@PathParam("table") String table, @PathParam("code") String code,
			@PathParam("topCount") String topCount) {
		return "<?xml version=\"1.0\"?>" + "<average rate>" + getExchangeRateSeriesAverage(table, code, topCount)
				+ "</average rate>";
	}

	private double getExchangeRateSeriesAverage(String table, String code, String topCount) {
		NBPConnector nbpConnector = new NBPConnector();
		String xml = nbpConnector.getAverageRates(table, code, topCount);
		try {
			JAXBContext context = JAXBContext.newInstance(ExchangeRatesSeries.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader stringReader = new StringReader(xml);
			ExchangeRatesSeries series = (ExchangeRatesSeries) unmarshaller.unmarshal(stringReader);
			return series.getAverage(table);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
}
