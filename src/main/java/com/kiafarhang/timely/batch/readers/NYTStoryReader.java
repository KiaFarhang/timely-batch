package com.kiafarhang.timely.batch.readers;

import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import com.kiafarhang.timely.batch.services.clients.NYTClient;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NYTStoryReader implements ItemReader<NYTStory> {

  private NYTClient client;

  private List<NYTStory> stories;
  private int storyPointer = 0;

  @Autowired
  public NYTStoryReader(NYTClient client) {
    this.client = client;
    this.stories = client.getNewswireStories();
  }

  @Override
  public NYTStory read() {
    if (stories.size() > storyPointer) {
      NYTStory toReturn = stories.get(storyPointer);
      storyPointer++;
      return toReturn;
    }

    return null;
  }
}
