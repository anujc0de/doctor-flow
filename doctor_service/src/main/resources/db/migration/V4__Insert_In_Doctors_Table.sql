INSERT INTO doctors (id, name, specialization, department_id,email,created_at, updated_at)
VALUES
  (gen_random_uuid(), 'Dr. Smith', 'Cardiologist', 1,'smith@gmail.com',now(),now()),
  (gen_random_uuid(), 'Dr. Johnson', 'Cardiac Surgeon', 1,'john@gmail.com',now(),now()),
  (gen_random_uuid(), 'Dr. Davis', 'Dermatologist', 3,'devis@gmail.com',now(),now()),
  (gen_random_uuid(), 'Dr. Wilson', 'Pediatrician', 4,'wilson@gmail.com',now(),now()),
  (gen_random_uuid(), 'Dr. Anderson', 'Pediatric Cardiologist', 4,'anderson@gmail.com',now(),now());