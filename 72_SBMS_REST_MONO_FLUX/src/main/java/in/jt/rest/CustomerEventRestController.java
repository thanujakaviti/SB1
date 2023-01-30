package in.jt.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jt.binding.CustomerEvent;
import in.jt.service.CustomerEventService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerEventRestController {
	@Autowired
	private CustomerEventService customerEventService;


	@GetMapping("/getEvent")
	public ResponseEntity<Mono<CustomerEvent>> loadCustomerEvent(){
		Mono<CustomerEvent> customerEvent = customerEventService.getCustomerEvent();
		ResponseEntity<Mono<CustomerEvent>> entity =
				new ResponseEntity<Mono<CustomerEvent>>(customerEvent,HttpStatus.OK);
		return entity;
	}

	@GetMapping(value="/getEvents",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<CustomerEvent>> loadCustomerEvents(){
		Flux<CustomerEvent> customerEvents = customerEventService.getCustomerEvents();
		ResponseEntity<Flux<CustomerEvent>> entity =
				new ResponseEntity<Flux<CustomerEvent>>(customerEvents,HttpStatus.OK);
		return entity;
	}
}
