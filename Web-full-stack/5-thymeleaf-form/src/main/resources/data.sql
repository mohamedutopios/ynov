-- Create Category table
CREATE TABLE IF NOT EXISTS category (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL
);

-- Create Product table
CREATE TABLE IF NOT EXISTS product (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       price DECIMAL(10, 2) NOT NULL,
                                       category_id BIGINT,
                                       FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Insert data into Category table
INSERT INTO category (name) VALUES ('Electronics');
INSERT INTO category (name) VALUES ('Books');
INSERT INTO category (name) VALUES ('Clothing');
INSERT INTO category (name) VALUES ('Home & Kitchen');

-- Insert data into Product table
INSERT INTO product (name, price, category_id) VALUES ('Smartphone', 599.99, 1);
INSERT INTO product (name, price, category_id) VALUES ('Laptop', 899.99, 1);
INSERT INTO product (name, price, category_id) VALUES ('Novel', 19.99, 2);
INSERT INTO product (name, price, category_id) VALUES ('T-shirt', 9.99, 3);
INSERT INTO product (name, price, category_id) VALUES ('Blender', 49.99, 4);