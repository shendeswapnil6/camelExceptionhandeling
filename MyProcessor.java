package camelPack;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MyProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("\n in processor >> ");
		System.out.println("data "+ exchange.getIn());
		throw new CustomException();
	}
}
