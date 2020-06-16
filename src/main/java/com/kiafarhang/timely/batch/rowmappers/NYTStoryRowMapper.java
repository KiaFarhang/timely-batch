package com.kiafarhang.timely.batch.rowmappers;

import com.kiafarhang.timely.batch.models.nyt.NYTStory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import org.springframework.jdbc.core.RowMapper;

public class NYTStoryRowMapper implements RowMapper<NYTStory> {

  @Override
  public NYTStory mapRow(final ResultSet resultSet, final int rowNumber) throws SQLException {
    final String section = resultSet.getString("section");
    final String subsection = resultSet.getString("subsection");
    final String title = resultSet.getString("title");
    final String storyAbstract = resultSet.getString("abstract");
    final String url = resultSet.getString("story_url");
    final String byline = resultSet.getString("byline");
    final String itemType = resultSet.getString("item_type");
    final String source = resultSet.getString("source");
    final ZonedDateTime updatedDate = resultSet.getObject("updated_date", ZonedDateTime.class);
    return new NYTStory(
        section,
        subsection,
        title,
        storyAbstract,
        url,
        byline,
        itemType,
        source,
        updatedDate,
        null,
        null,
        null);
  }
}