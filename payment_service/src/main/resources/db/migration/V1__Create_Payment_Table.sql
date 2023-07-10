CREATE TABLE payments (
  id SERIAL  PRIMARY KEY,
    appointment_id INT,
   payment_status TEXT default 'started',
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
