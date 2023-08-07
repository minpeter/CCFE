drop schema if exists testdb3;
create schema testdb3;

use testdb3;

ALTER DATABASE testdb3 DEFAULT CHARACTER SET UTF8;

create table board (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(255) NOT NULL,
    view_count INT DEFAULT 0,
    isPrivate BOOLEAN DEFAULT FALSE,
#     첨부파일
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


insert into board (title, content, writer)
values ('제목1', '내용1', '홍길동'),
       ('제목2', '내용2', '관리자'),
       ('제목3', '내용3', '고길동'),
       ('제목4', '내용4', '둘리');


select * from board;