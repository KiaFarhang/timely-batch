package com.kiafarhang.timely.batch.models.nyt

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Builder
import java.time.ZonedDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
data class NYTStory(
        val section: String? = "",
        val subsection: String? = "",
        val title: String? = "",
        @JsonProperty("abstract") val storyAbstract: String? = "",
        val url: String? = "",
        val byline: String? = "",
        @JsonProperty("item_type") val itemType: String? = "",
        val source: String? = "",
        @JsonProperty("updated_date") val updatedDate: ZonedDateTime? = null,
        @JsonProperty("created_date") val createdDate: ZonedDateTime? =  null,
        @JsonProperty("published_date") val publishedDate: ZonedDateTime? = null,
        @JsonProperty("first_published_date") val firstPublishedDate: ZonedDateTime? = null
)