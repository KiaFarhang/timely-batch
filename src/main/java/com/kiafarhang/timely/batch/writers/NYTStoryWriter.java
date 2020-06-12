package com.kiafarhang.timely.batch.writers;

import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class NYTStoryWriter implements ItemWriter<NYTStory> {
  @Override
  public void write(List<? extends NYTStory> items) {
    items.forEach(item -> log.info(item.toString()));
  }
}
