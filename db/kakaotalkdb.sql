drop schema if exists kakaotalkdb;
create schema kakaotalkdb;

use kakaotalkdb;

ALTER DATABASE kakaotalkdb DEFAULT CHARACTER SET UTF8;

create table user (
    id int not null auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    status_message varchar(255) null
);

create table chatroom (
    id int not null auto_increment primary key,
    name varchar(255) not null,
    type varchar(255) not null,
    created_at datetime not null default now()
);

create table chatroom_user (
    id int not null auto_increment primary key,
    chatroom_id int not null,
    user_id int not null,
    created_at datetime not null default now(),
    foreign key (chatroom_id) references chatroom(id),
    foreign key (user_id) references user(id)
);

create table chat (
    id int not null auto_increment primary key,
    chatroom_id int not null,
    user_id int not null,
    message varchar(255) not null,
    created_at datetime not null default now(),
    foreign key (chatroom_id) references chatroom(id),
    foreign key (user_id) references user(id)
);

create table block (
    id int not null auto_increment primary key,
    user_id int not null,
    block_user_id int not null,
    created_at datetime not null default now(),
    foreign key (user_id) references user(id),
    foreign key (block_user_id) references user(id)
);

create table hide (
    id int not null auto_increment primary key,
    user_id int not null,
    hide_user_id int not null,
    created_at datetime not null default now(),
    foreign key (user_id) references user(id),
    foreign key (hide_user_id) references user(id)
);

# 친구 목록 테이블
create table friend (
    id int not null auto_increment primary key,
    user_id int not null,
    friend_user_id int not null,
    created_at datetime not null default now(),
    foreign key (user_id) references user(id),
    foreign key (friend_user_id) references user(id)
);
# 유니크 인덱스로 user_id에 대해서 중복되는 값이 들어가지 않도록 설정
create unique index friend_unique on friend(user_id, friend_user_id);



insert into user (name, email, password, status_message)
    values
        ('김철수', 'cjftn@gamil.com', '1234', null),
        ('김영희', 'dudgml@naver.com', '1234', null),
        ('최지은', 'wlsdms@kakao.com', '1234', '집가고 싶다'),
        ('김민지', 'alswl@gmail.com', '1234', '우리 법정에서 만나요~'),
        ('김영수', 'dudtn@naver.com', '1234', '인생이란 무엇일까'),
        ('김영민', 'dudals@zeromin.com', '1234', null),
        ('김민수', 'minsu@naver.com', '1234', '도깨비 재밌다 ㅎ'),
        ('민웅기', 'kali@gmail.com', '1234', null),
        ('안철수', 'cjftn@naver.com', '1234', '기호 3번 안철수입니다.'),
        ('이영희', 'dudgml@gmail.com', '1234', '자카르타~');


insert into chatroom (name, type)
    values
        ('철수와 영희', 'private'),
        ('해킹 3-1 반톡', 'group'),
        ('야구 좋아하는 사람들', 'group'),
        ('지은, 민지, 영민', 'group'),
        ('민수와 철수', 'private'),
        ('영수와, 영민', 'private'),
        ('지은과 민지', 'private'),
        ('영희와 영수', 'private'),
        ('민수와 웅기', 'private'),
        ('개발톡방', 'group'),
        ('철수와 웅기', 'private'),
        ('영수, 영민, 철수', 'group'),
        ('민지, 민수, 철수', 'group'),
        ('해킹 3-2 반톡', 'group'),
        ('한세사이버보안고 학생회', 'group');


insert into chatroom_user (chatroom_id, user_id)
    values
        (1, 1), (1, 2),
        (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10),
        (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10),
        (4, 3), (4, 4), (4, 6),
        (5, 1), (5, 7),
        (6, 5), (6, 6),
        (7, 3), (7, 4), (7, 6),
        (8, 2), (8, 5), (9, 1), (9, 8),
        (10, 1), (10, 9),
        (11, 1), (11, 8),
        (12, 1), (12, 9),
        (13, 1), (13, 8),
        (14, 1), (14, 9);

insert into chat (chatroom_id, user_id, message)
    values
        (1, 1, '안녕'),
        (1, 2, '안녕하세요'),
        (1, 1, '오랜만이네요'),
        (1, 2, '네 오랜만이네요'),
        (1, 1, '잘 지내셨어요?'),
        (1, 2, '네 잘 지냈어요'),
        (1, 1, '그럼 좋네요'),
        (1, 2, '네'),
        (1, 1, '그럼 이만'),
        (1, 2, '네 이만'),

        (2, 1, '안녕하세요'),
        (2, 2, '안녕하세요'),
        (2, 3, '안녕하세요'),
        (2, 4, '안녕하세요'),
        (2, 5, '안녕하세요'),
        (2, 6, '안녕하세요'),
        (2, 7, '안녕하세요'),
        (2, 8, '안녕하세요'),
        (2, 9, '안녕하세요'),
        (2, 10, '안녕하세요'),

        (3, 1, '안녕하세요'),
        (3, 2, '안녕하세요'),
        (3, 3, '안녕하세요'),
        (3, 4, '안녕하세요'),
        (3, 5, '안녕하세요'),
        (3, 6, '안녕하세요'),
        (3, 7, '안녕하세요'),
        (3, 8, '안녕하세요'),
        (3, 9, '안녕하세요'),
        (3, 10, '안녕하세요'),

        (4, 3, '안녕하세요'),
        (4, 4, '안녕하세요'),
        (4, 6, '안녕하세요'),

        (5, 1, '반가워요');


insert into block (user_id, block_user_id)
    values (1, 2), (1, 3), (1, 4), (1, 5), (2, 3), (2, 4), (2, 5), (4, 5), (4, 6);

insert into hide (user_id, hide_user_id)
    values (1, 2), (1, 3), (5, 3), (5, 4), (5, 6), (5, 7), (9, 3), (9, 4), (9, 6), (9, 7), (9, 8), (9, 10);

insert into friend (user_id, friend_user_id)
    values (1, 2), (1, 3), (1, 4), (1, 5), (1, 9),
           (2, 3), (2, 4), (2, 5),
           (4, 5), (4, 6);


insert into friend (user_id, friend_user_id)
    values (1, 8);

# 대화방 별로 참가되있는 사용자, 참가된 사용자 수를 출력
select chatroom.name, count(chatroom_user.user_id) as user_count
    from chatroom
        join chatroom_user on chatroom.id = chatroom_user.chatroom_id
    group by chatroom.name
    order by user_count desc;

# 사용자 별로 참가되있는 대화방 수를 출력
select user.name, count(chatroom_user.chatroom_id) as chatroom_count
    from user
        join chatroom_user on user.id = chatroom_user.user_id
    group by user.name
    order by chatroom_count desc;


# 대화가 가장 많은 대화방의 채팅 내용, 보낸 사람, 보낸 시간을 출력
select chatroom.name as chatroom_name, chat.message, user.name as sender, chat.created_at
    from chatroom
        join chat on chatroom.id = chat.chatroom_id
        join user on chat.user_id = user.id
    where chatroom.id = (
        select chatroom_id
            from chat
            group by chatroom_id
            order by count(chat.message) desc
            limit 1
    )
    order by chat.id;



# 카카오톡 톡방 별로 가장 많이 대화한 사용자의 이름, 대화 수를 출력
select chatroom.name, user.name, count(chat.message) as count
    from chatroom
        join chat on chatroom.id = chat.chatroom_id
        join user on chat.user_id = user.id
    group by chatroom.name, user.name
    order by count desc
    limit 1;


# 차단한 유저가 가장 많은 사용자의 이름, 차단한 유저 수를 출력
select user.name, count(block.block_user_id) as count
    from user
        join block on user.id = block.user_id
    group by user.name
    order by count desc
    limit 1;
# 숨긴 유저가 가장 많은 사용자의 이름, 숨긴 유저 수를 출력
select user.name, count(hide.hide_user_id) as count
    from user
        join hide on user.id = hide.user_id
    group by user.name
    order by count desc
    limit 1;


# 사용자 별로 차단한 유저 수를 출력
select user.name, count(block.block_user_id) as count
    from user
        join block on user.id = block.user_id
    group by user.name
    order by count desc;

# 사용자 별로 숨긴 유저 수를 출력
select user.name, count(hide.hide_user_id) as count
    from user
        join hide on user.id = hide.user_id
    group by user.name
    order by count desc;

# 김철수 사용자의 친구 목록을 출력
select user.name, user.status_message
    from user
        join friend on user.id = friend.friend_user_id
    where friend.user_id = (
        select id
            from user
            where name = '김철수'
    );
