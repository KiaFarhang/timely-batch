package com.kiafarhang.timely.batch.models.nyt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class NYTStory {

  private String section;
  private String subsection;
  private String title;

  @JsonProperty("abstract")
  private String storyAbstract;

  private String url;
  private String byline;

  @JsonProperty("item_type")
  private String itemType;

  private String source;

  @JsonProperty("updated_date")
  private ZonedDateTime updatedDate;

  @JsonProperty("created_date")
  private ZonedDateTime createdDate;

  @JsonProperty("published_date")
  private ZonedDateTime publishedDate;

  @JsonProperty("first_published_date")
  private ZonedDateTime firstPublishedDate;
}
