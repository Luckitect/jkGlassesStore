-- ===== BRANDS =====
INSERT INTO brands (id, name) VALUES
(1, 'Ray-Ban'),
(2, 'Gucci'),
(3, 'Oakley');


-- ===== CATEGORIES =====
INSERT INTO categories (id, name, type) VALUES
(1, 'Sport', 'Glasses'),
(2, 'Optical', 'Frame'),
(3, 'Blue Light', 'Glasses');

SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));

-- ===== PRODUCTS =====
INSERT INTO product (id, name, price, size, color, material, polarized, stock, sale, category_id, brand_id)
VALUES
(1, 'Aviator Glasses', 150.0, 'M', 'Black', 'Metal', true, 10, false, 1, 1),
(2, 'Round Glasses', 120.0, 'L', 'Brown', 'Plastic', false, 5, true, 2, 2),
(3, 'Aviator', 150.0, 'M', 'Black', 'Metal', true, 10, false, 1, 1);




----{
--    "name": "Wayfarer Glasses",
--    "price": 130.0,
--    "size": "M",
--    "color": "Black",
--    "material": "Plastic",
--    "polarized": true,
--    "stock": 15,
--    "sale": false,
--    "brandId": 1,
--    "categoryId": 1
--  }  ესაა JSON ის ფორმატი დამატებისას
--// ეს შველის, აიდი 1 არის ავტომატურად, როცა ეშვება
SELECT setval('product_id_seq', (SELECT MAX(id) FROM product));
-- ===== USERS =====
INSERT INTO users (id, first_name, last_name, email, address, password, phone) VALUES
(1, 'Jokola', 'Kistauri', 'j.kistauri17@gmail.com', 'saxli', 'password', '555555555'),
(2, 'Mariam', 'Giorgadze', 'mg@gmail.com', 'msaxli', 'password', '555663322'),
(3, 'Giorgi', 'Giorgadze', 'gg@gmail.com', 'gsaxli', 'password', '123123333');


SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
-- ===== ORDERS =====
INSERT INTO orders (order_id, user_id, order_date, status) VALUES
(1, 1, CURRENT_DATE, 'PENDING'),
(2, 2, CURRENT_DATE, 'COMPLETED');




-- ===== ORDER ITEMS =====
INSERT INTO order_items (quantity, price, product_id, order_id) VALUES
(1, 150.0, 1, 1),
(2, 120.0, 2, 1);


-- ===== CARTS =====
INSERT INTO carts (id, user_id, created_at) VALUES
(1, 1, '2025-07-09'),
(2, 2, '2025-08-09');

-- ===== CART ITEMS =====
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(1, 1, 2),
(2, 2, 1);

-- ===== RATINGS =====
INSERT INTO ratings (user_id, product_id, stars_num, created_at) VALUES
(1, 1, 5, '2025-09-09'),
(2, 3, 4, '2025-08-08');
SELECT setval('ratings_id_seq', (SELECT MAX(id) FROM ratings));

-- ===== BLOG POSTS =====
INSERT INTO blog_posts (id, title, content, author_id, created_at) VALUES
(1, 'Summer Sunglasses Trends 2025', 'This year wayfarers are back...', 1, CURRENT_DATE),
(2, 'Protecting Your Eyes from Blue Light', 'Tips for healthy screen time...', 2, CURRENT_DATE);

-- ===== FAQS =====
INSERT INTO faqs (id, question, answer, author_id, created_at) VALUES
(1, 'What is polarized glass?', 'Polarized glass reduces glare...', 1, CURRENT_DATE);

-- ===== CONTACT MESSAGES =====
INSERT INTO contact_messages (user_id, email, message, created_at) VALUES
( 1, 'john.doe@example.com', 'When will new products arrive?', CURRENT_DATE);
