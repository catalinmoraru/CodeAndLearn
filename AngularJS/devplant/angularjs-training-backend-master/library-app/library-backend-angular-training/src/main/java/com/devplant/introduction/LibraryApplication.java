package com.devplant.introduction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAspectJAutoProxy
@EnableElasticsearchRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
		classes = ElasticsearchRepository.class))
@EnableJpaRepositories(
		includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JpaRepository.class))
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
