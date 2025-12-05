package com.gigtasker.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class SearchServiceApplication {

    private SearchServiceApplication() {}

	static void main(String[] args) {
		SpringApplication.run(SearchServiceApplication.class, args);
	}

}
