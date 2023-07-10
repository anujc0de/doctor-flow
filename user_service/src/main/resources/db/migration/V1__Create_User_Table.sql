CREATE TABLE users (
  id SERIAL  PRIMARY KEY,
  name text not null,
  contact_number text not null,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
