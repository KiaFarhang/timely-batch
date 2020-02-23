package com.kiafarhang.timely.batch.services.clients;

import static org.junit.Assert.assertEquals;

import com.kiafarhang.timely.batch.models.nyt.NYTPayload;
import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class NYTClientTests {

  @Mock private RestTemplate restTemplate;

  @InjectMocks @Spy private NYTClient client;

  @Test
  public void getNewswireStories_blankAPIKey_throwsIllegalStateException() {
    Mockito.doReturn("").when(client).getApiKey();
    Assertions.assertThrows(
        IllegalStateException.class,
        () -> {
          client.getNewswireStories();
        });
  }

  @Test
  public void getNewswireStories_happyPath_returnsListOfStories() {

    Mockito.doReturn("myCoolAPIKey").when(client).getApiKey();

    final NYTStory story = new NYTStory();

    final List<NYTStory> stories = List.of(story);

    final NYTPayload payload = new NYTPayload(stories, 1);

    Mockito.doReturn(payload)
        .when(restTemplate)
        .getForObject(Mockito.anyString(), Mockito.eq(NYTPayload.class));

    final List<NYTStory> receivedStories = client.getNewswireStories();

    assertEquals(1, receivedStories.size());
  }
}
