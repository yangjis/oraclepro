create table person(
    person_id   number(5),
    name        varchar2(30) not null,
    hp          varchar2(20),
    company     varchar2(20),
    PRIMARY key (person_id)
);

create SEQUENCE seq_person_id increment by 1 start with 1;

insert into person values(SEQ_PERSON_ID.nextval, '��ȿ��', '010-1111-1111', '02-1111-1111');
insert into person values(SEQ_PERSON_ID.nextval, '���켺', '010-2222-2222', '02-2222-2222');
insert into person values(SEQ_PERSON_ID.nextval, '���缮', '010-3333-3333', '02-3333-3333');
insert into person values(SEQ_PERSON_ID.nextval, '������', '010-4444-4444', '02-4444-4444');
insert into person values(SEQ_PERSON_ID.nextval, '������', '010-5555-5555', '02-5555-5555');

select person_id,
        name,
        hp,
        company
from person;

update person set hp = '010-9999-9999',
                  company = '02-9999-9999'
where person_id = 4;

delete from person where person_id = 5;

commit;