package com.example.intern;

import java.time.Duration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RequestMapping("/api/v1")
@Tag(name="intern")
@RestController

public class InternController {
	
	@Autowired
	public InternRepository internRepository;
	
	@GetMapping("/interns")
	public Flux<Intern> getAllInterns() {
		return internRepository.findAll();
	}
	

	@GetMapping(value="/streaminterns", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
     public Flux<Intern> getAllStreamIntern() {
             return internRepository.findAll().delayElements(Duration.ofSeconds(1));
     }
	
	@GetMapping("/intern/{id}")
	public Mono<Intern> getByInternId(@PathVariable String id) {
		return internRepository.findById(id);
	}
	
	@PostMapping("/intern")
	public Mono<Intern> createIntern(@Valid @RequestBody Intern intern) {
		return internRepository.save(intern);
	}
	
	@PutMapping("/intern/{id}")
	public Mono<Intern> updateIntern(@PathVariable String id, @Validated @RequestBody Intern intern) {
		intern.setId(id);
		return internRepository.save(intern);
	}
	
	@DeleteMapping("/intern/{id}")
	public Mono<Void> deleteIntern(@PathVariable String id) {
		return internRepository.deleteById(id);
	}
	
	
}
