
CREATE TABLE appointments (
  id SERIAL PRIMARY KEY,
  slot_id INT ,
  user_id INT not null,
  status TEXT  DEFAULT 'started',
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp

);
