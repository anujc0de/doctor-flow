INSERT INTO users (id,name, contact_number)
SELECT
    generate_series,
  'User ' || generate_series AS name,
  LPAD(CAST(FLOOR(random() * 10000000000) AS TEXT), 10, '0') AS contact_number
FROM generate_series(1, 100);