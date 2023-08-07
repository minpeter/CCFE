drop schema if exists testdb2;
create schema testdb2;

use testdb2;

ALTER DATABASE testdb2 DEFAULT CHARACTER SET UTF8;

create table users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create index orders_id_idx ON users(id);

create table orders (
    order_id INT primary key auto_increment,
    user_id Int,
    product VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    order_date DATE
);

insert into users (username, email)
    values ('user1', 'user1@example.com'),
           ('user2', 'user2@example.com'),
           ('user3', 'user3@example.com'),
           ('user4', 'user4@example.com'),
           ('user5', 'user5@example.com');

insert into orders (user_id, product, quantity, order_date)
    values (1, 'Product A', 3, '2023-08-01'),
           (1, 'Product B', 1, '2023-08-01'),
           (2, 'Product A', 2, '2023-08-02'),
           (3, 'Product C', 5, '2023-08-02'),
           (4, 'Product B', 2, '2023-08-03'),
           (5, 'Product D', 4, '2023-08-03'),
           (1, 'Product A', 1, '2023-08-04'),
           (3, 'Product B', 3, '2023-08-04'),
           (2, 'Product C', 2, '2023-08-05'),
           (3, 'Product D', 1, '2023-08-05');


# 각 제품별로 주문된 총 수량과 평균 수량을 조회
select product, sum(quantity), avg(quantity)
    from orders
    group by product;


# 각 사용자 이름 별로 주문한 총 제품 종류, 총 주문 수, 가장 최근 주문 날짜 조회
SELECT
    users.username,
    COUNT(DISTINCT orders.product) AS total_products,
    COUNT(orders.order_id) AS total_orders,
    MAX(orders.order_date) AS last_order_date
FROM users
JOIN orders ON users.id = orders.user_id
GROUP BY users.username;


# USERNAME이 'user3'인 사용자가 이메일을 'test@naver.com'으로 수정
UPDATE users
SET email = 'test@naver.com'
WHERE username = 'user3';

select * from users;


# 2023년 8월 1일 주문한 이력을 삭제
DELETE FROM orders
WHERE order_date = '2023-08-01';

select * from orders;


# username이 user3인 사용자가 주문한 이력을 삭제
DELETE FROM orders
WHERE user_id = (SELECT id FROM users WHERE username = 'user3');

select * from orders;


# 가장 많은 수량을 주문한 사용자의 이메일을 'vip@naver.com' 으로 수정
update users
set email = 'vip@naver.com'
where id = (
    select user_id
    from orders
    group by user_id
    order by sum(quantity) desc
    limit 1
    );

select * from users;