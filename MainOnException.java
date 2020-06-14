package camelPack;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class MainOnException {

	public static void main(String[] args) throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub

		        onException(CustomException.class).process(new Processor() {

		        		@Override
		            public void process(Exchange exchange) throws Exception {
		                System.out.println("handling ex");
		            }
		        }).log("Received body ").handled(true);

		        from("file:input?noop=true").process(new MyProcessor()).to("file:output");

		        from("file:source?noop=true").process(new MyProcessor()).to("file:output");
			}
			
		});
		context.start();
		Thread.sleep(10000);
		context.stop();
}
}
