package com.kiafarhang.timely.batch.configuration;

import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import com.kiafarhang.timely.batch.readers.NYTStoryReader;
import com.kiafarhang.timely.batch.writers.NYTStoryWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableBatchProcessing
public class TimelyBatchConfiguration {

  @Autowired private JobBuilderFactory jobBuilderFactory;

  @Autowired private StepBuilderFactory stepBuilderFactory;

  @Autowired private NYTStoryReader reader;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public NYTStoryReader reader() {
    return reader;
  }

  @Bean
  public NYTStoryWriter writer() {
    return new NYTStoryWriter();
  }

  @Bean
  public Job getNYTStoriesJob(Step step1) {
    return jobBuilderFactory
        .get("getNYTStoriesJob")
        .incrementer(new RunIdIncrementer())
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory
        .get("step1")
        .<NYTStory, NYTStory>chunk(1)
        .reader(reader())
        .writer(writer())
        .build();
  }
}
