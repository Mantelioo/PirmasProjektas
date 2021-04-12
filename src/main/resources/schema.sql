drop table if exists gunshopgun;
drop table if exists gunshop;
drop table if exists gun;

create table car
(
    car_id identity,
    description varchar(255),
);

create table shopcars
(
    id identity,
    shop_id int,
    car_id  int,
    foreign key (shop_id) references shop (shop_id),
    foreign key (car_id) references car (car_id)
);

insert into shop(name)
values ('parduotuve');

insert into car(name)
values ('automobilis');

insert into shopcars(shop_id, car_id)
values (1, 1);

select *
from PUBLIC.CAR