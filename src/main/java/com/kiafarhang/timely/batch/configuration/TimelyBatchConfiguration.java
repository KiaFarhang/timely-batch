package com.kiafarhang.timely.batch.configuration;

import javax.sql.DataSource;

import com.kiafarhang.timely.batch.listeners.JobCompletionNotificationListener;
import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import com.kiafarhang.timely.batch.readers.NYTStoryReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
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
  public JdbcBatchItemWriter<NYTStory> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<NYTStory>()
    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
    .sql("INSERT INTO nyt (title) VALUES (:title)")
    .dataSource(dataSource)
    .build();
  }

  @Bean
  public Job getNYTStoriesJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory
        .get("getNYTStoriesJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<NYTStory> writer) {
    return stepBuilderFactory
        .get("step1")
        .<NYTStory, NYTStory>chunk(1)
        .reader(reader())
        .writer(writer)
        .build();
  }
}
