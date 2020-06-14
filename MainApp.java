package camelPack;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class MainApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		CamelContext context = new DefaultCamelContext();
		
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
				from("direct:start").doTry().process(new MyProcessor()).to("seda:end")
				.doCatch(CustomException.class).process(new Processor() {

		            public void process(Exchange exchange) throws Exception {
		                System.out.println("handling ex");
		                throw new CustomException();
		            }
		        }).log("Received body ");
			}
		});
		context.start();
	
		

		ProducerTemplate pt = context.createProducerTemplate();
		pt.sendBody("direct:start", "helll00123");
		System.out.println("\n\nsent message as >> helll00123" );
		
		ConsumerTemplate ct = context.createConsumerTemplate();
		String recvdString = ct.receiveBody("seda:end",  String.class);
		
		System.out.println("\nrecieved message as >> "+ recvdString);
		
	}
}