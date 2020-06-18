package com.kiafarhang.timely.batch.rowmappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import java.sql.ResultSet;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
/**
 * Without lenient strictness, Mockito freaks out that we're mocking the return of the getString()
 * and getObject() methods on the mock result set multiple times. But this is intended; the code
 * under test calls those methods multiple times with different arguments.
 */
@MockitoSettings(strictness = Strictness.LENIENT)
public class NYTStoryRowMapperTests {

  @Mock private ResultSet resultSet;

  private static final int ROW_NUMBER = 0;

  private NYTStoryRowMapper mapper = new NYTStoryRowMapper();

  @Test
  public void mapRow_mapsSectionColumn_toSectionProperty() throws Exception {
    final String expected = "abc";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("section"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getSection());
  }

  @Test
  public void mapRow_mapsSubsectionColumn_toSubsectionProperty() throws Exception {
    final String expected = "123";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("subsection"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getSubsection());
  }

  @Test
  public void mapRow_mapsTitleColumn_toTitleProperty() throws Exception {
    final String expected = "a title";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("title"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getTitle());
  }

  @Test
  public void mapRow_mapsAbstractColumn_toStoryAbstractProperty() throws Exception {
    final String expected = "abstract";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("abstract"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getStoryAbstract());
  }

  @Test
  public void mapRow_mapsStoryUrlColumn_toUrlProperty() throws Exception {
    final String expected = "https://foo.com";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("story_url"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getUrl());
  }

  @Test
  public void mapRow_mapsBylineColumn_toBylineProperty() throws Exception {
    final String expected = "By Haruki Murakami";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("byline"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getByline());
  }

  @Test
  public void mapRow_mapsItemTypeColumn_toItemTypeProperty() throws Exception {
    final String expected = "News";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("item_type"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getItemType());
  }

  @Test
  public void mapRow_mapsSourceColumn_toSourceProperty() throws Exception {
    final String expected = "New York Times";
    doReturn(expected).when(resultSet).getString(ArgumentMatchers.eq("source"));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getSource());
  }

  @Test
  public void mapRow_mapsUpdatedDateColumn_toUpdatedDateProperty() throws Exception {
    final ZonedDateTime expected = ZonedDateTime.now();
    doReturn(expected)
        .when(resultSet)
        .getObject(ArgumentMatchers.eq("updated_date"), ArgumentMatchers.eq(ZonedDateTime.class));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getUpdatedDate());
  }

  @Test
  public void mapRow_mapsCreatedDateColumn_toCreatedDateProperty() throws Exception {
    final ZonedDateTime expected = ZonedDateTime.now();
    doReturn(expected)
        .when(resultSet)
        .getObject(ArgumentMatchers.eq("created_date"), ArgumentMatchers.eq(ZonedDateTime.class));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getCreatedDate());
  }

  @Test
  public void mapRow_mapsPublishedDateColumn_toPublishedDateProperty() throws Exception {
    final ZonedDateTime expected = ZonedDateTime.now();
    doReturn(expected)
        .when(resultSet)
        .getObject(ArgumentMatchers.eq("published_date"), ArgumentMatchers.eq(ZonedDateTime.class));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getPublishedDate());
  }

  @Test
  public void mapRow_mapsFirstPublishedDateColumn_toFirstPublishedDateProperty() throws Exception {
    final ZonedDateTime expected = ZonedDateTime.now();
    doReturn(expected)
        .when(resultSet)
        .getObject(
            ArgumentMatchers.eq("first_published_date"), ArgumentMatchers.eq(ZonedDateTime.class));
    final NYTStory result = mapper.mapRow(resultSet, ROW_NUMBER);
    assertEquals(expected, result.getFirstPublishedDate());
  }
}
