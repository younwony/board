create table if not exists member
(
    id bigint not null comment '사용자 ID',
    name varchar2(255) comment '이름',
    createdAt timestamp default current_timestamp comment '생성일시',
    updateAt timestamp default current_timestamp comment '수정일시',
    primary key (id)
);

create table if not exists board
(
    id bigint not null comment '게시판 ID',
    title varchar2(500) comment '제목',
    content varchar2(4000) comment '내용',
    createdAt timestamp default current_timestamp comment '생성일시',
    updateAt timestamp default current_timestamp comment '수정일시',
    primary key (id)
);