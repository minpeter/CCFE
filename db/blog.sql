drop schema if exists blogdb;
create schema blogdb;

use blogdb;

ALTER DATABASE blogdb DEFAULT CHARACTER SET UTF8;

create table users (
    id int not null auto_increment,
    username varchar(255) not null,
    email varchar(255) not null,
    create_at timestamp default current_timestamp,
    primary key (id)
);

create table posts (
    id int not null auto_increment,
    title varchar(255) not null,
    content text not null,
    create_at timestamp default current_timestamp,
    user_id int not null,
    primary key (id),
    foreign key (user_id) references users(id)
);

create table comments (
    id int not null auto_increment,
    content text not null,
    create_at timestamp default current_timestamp,
    user_id int not null,
    post_id int not null,

#     post_id에 대한 index를 만들어 빠르게 찾을 수 있게 도와줌
    INDEX comment_idx01(post_id),
    primary key (id),
    foreign key (user_id) references users(id),
    foreign key (post_id) references posts(id)
);


select * from users;
select * from posts;
select * from comments;


insert into users (username, email)
values ('user1', 'user1@example.com'),
         ('user2', 'user2@example.com'),
            ('user3', 'user3@example.com'),
            ('user4', 'user4@example.com');


insert into posts (title, content, user_id)
    values ('title1', 'content1', 1),
            ('title2', 'content2', 2),
            ('title3', 'content3', 3),
            ('title4', 'content4', 4);



insert into comments (content, user_id, post_id)
    values ('comment1', 1, 1),
            ('comment2', 2, 2),
            ('comment3', 3, 3),
            ('comment4', 4, 4);
