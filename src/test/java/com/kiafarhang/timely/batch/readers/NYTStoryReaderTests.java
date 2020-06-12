package com.kiafarhang.timely.batch.readers;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import com.kiafarhang.timely.batch.services.clients.NYTClient;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NYTStoryReaderTests {

  @Mock private NYTClient mockClient;

  @Test
  public void read_storyNotYetRead_returnsNextStory() {
    final List<NYTStory> stories = List.of(new NYTStory());
    doReturn(stories).when(mockClient).getNewswireStories();
    final NYTStoryReader reader = new NYTStoryReader(mockClient);
    final NYTStory story = reader.read();
    assertNotNull(story);
  }

  @Test
  public void read_allStoriesRead_returnsNull() {
    final List<NYTStory> stories = List.of(new NYTStory());
    doReturn(stories).when(mockClient).getNewswireStories();
    final NYTStoryReader reader = new NYTStoryReader(mockClient);
    // read the only story in the list
    reader.read();
    final NYTStory story = reader.read();
    assertNull(story);
  }

  @Test
  public void read_clientReturnsNoStories_returnsNull() {
    final List<NYTStory> stories = List.of();
    doReturn(stories).when(mockClient).getNewswireStories();
    final NYTStoryReader reader = new NYTStoryReader(mockClient);
    final NYTStory story = reader.read();
    assertNull(story);
  }
}
