DROP TABLE IF EXISTS followers;

CREATE TABLE followers (
  id bigserial PRIMARY KEY,
  user_id bigint NOT NULL,
  follower_id bigint NOT NULL,
  pending boolean NOT NULL,
  accepted boolean NOT NUll
);