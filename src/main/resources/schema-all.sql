CREATE TABLE IF NOT EXISTS nyt_stories (
    section varchar(200),
    subsection varchar(200),
    title varchar(500),
    abstract varchar(1000),
    story_url varchar(500),
    byline varchar(200),
    item_type varchar(100),
    source varchar(200),
    updated_date timestamp with time zone,
    created_date timestamp with time zone,
    published_date timestamp with time zone,
    first_published_date timestamp with time zone,
    PRIMARY KEY (title)
);