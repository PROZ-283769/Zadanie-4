package proz;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class NBPConnector {

	public String getAverageRates(String table, String code, String topCount) {
		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder
				.fromUri("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount)
				.build();
		WebTarget webTarget = client.target(uri);
		return webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
	}
}
