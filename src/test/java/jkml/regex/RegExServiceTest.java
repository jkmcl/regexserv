package jkml.regex;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RegExServiceTest {

	@Test
	public void testNormal() {
		RegExService regexServ = new RegExService();

		Request req = new Request();
		req.regex = "in(ter)e(haha)?(st)";
		req.input = "This is interesting";

		Response rsp = regexServ.evaluate(req);

        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(rsp, Response.class);

        System.out.println(json);
	}

	@Test
	public void testNotFound() {
		RegExService regexServ = new RegExService();

		Request req = new Request();
		req.regex = "in(ter)e(haha)?(st)";
		req.input = "No such thing";

		Response rsp = regexServ.evaluate(req);

        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(rsp, Response.class);

        System.out.println(json);
	}

}
