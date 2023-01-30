package in.jt.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import in.jt.binding.CustomerEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class CustomerEventService {
	
	public Mono<CustomerEvent> getCustomerEvent() {
		CustomerEvent event = new CustomerEvent(12345,"Ramchandra",LocalDateTime.now());
		return Mono.just(event);
	}
	
	
	public Flux<CustomerEvent> getCustomerEvents() {
		
		//Stream object used to generate responses continuously
		Stream<CustomerEvent> stream = Stream.generate(() -> new CustomerEvent(12345,"Ramchandra",LocalDateTime.now()));
		
		//Fetch CustomerEvent object from Stream object
		Flux<CustomerEvent> flux = Flux.fromStream(stream);
		
		//perform operation for every 2 seconds
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
		
		Flux<Tuple2<Long, CustomerEvent>> tuple = Flux.zip(interval, flux);
		
		Flux<CustomerEvent> finalFlux = tuple.map(Tuple2 :: getT2);
		
		System.out.println(finalFlux);
		return finalFlux;
	}
}
