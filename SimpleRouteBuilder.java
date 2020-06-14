package camelPack;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("in Simple Route builder");
		from("drect:send").doTry().process(new MyProcessor()).to("file:dest").
		doCatch(CustomException.class).process(new Processor() {

            public void process(Exchange exchange) throws Exception {
                System.out.println("handling ex");
            }
        }).log("Received body ");

	}
	
}
