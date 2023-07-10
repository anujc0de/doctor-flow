CREATE TABLE doctors (
  id UUID  NOT NULL     PRIMARY KEY,
  name text not null,
  specialization text not null,
  department_id int not null,
  email  text not null,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT current_timestamp,
  FOREIGN KEY (department_id) REFERENCES departments(id)
);
