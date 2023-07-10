CREATE TABLE slots (
  id SERIAL PRIMARY KEY,
  date DATE,
  start_time TIME,
  doctor_id uuid not null,
  end_time TIME,
  availability BOOLEAN DEFAULT true,
  expiry_time TIMESTAMP WITH TIME ZONE,
  booking_status text DEFAULT 'available',
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp
);
