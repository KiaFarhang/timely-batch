package com.kiafarhang.timely.batch.services.clients;

import com.kiafarhang.timely.batch.models.nyt.NYTPayload;
import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class NYTClient {

  @Autowired private RestTemplate restTemplate;

  private static final String NEWSWIRE_PATH =
      "https://api.nytimes.com/svc/news/v3/content/all/all.json";
  private static final String API_KEY = "api-key";

  /**
   * Returns a list of stories from the New York Times Newswire API:
   * https://developer.nytimes.com/docs/timeswire-product/1/overview
   */
  public List<NYTStory> getNewswireStories() {
    final String apiKey = getApiKey();
    if (StringUtils.isBlank(apiKey)) {
      throw new IllegalStateException(
          "Missing NYT_API_KEY environment variable; cannot call New York Times API.");
    }

    final String requestUrl = NEWSWIRE_PATH + "?" + API_KEY + "=" + apiKey;

    final NYTPayload payload = restTemplate.getForObject(requestUrl, NYTPayload.class);

    return payload.getResults();
  }

  String getApiKey() {
    return System.getenv("NYT_API_KEY");
  }
}
