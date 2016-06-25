--DROP TABLE users IF EXISTS;

CREATE TABLE products_price (
  product_id INTEGER PRIMARY KEY,
  product_name varchar(50),
  product_price varchar(10)
);
