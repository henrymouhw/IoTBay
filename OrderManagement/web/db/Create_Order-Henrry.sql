

create table OrderItem(
item_id int generated always as identity,
order_id int not null,
pName varchar(300) not null,
quantity int not null,
primary key(item_id),
foreign key(order_id) REFERENCES ORDERS(order_id),
foreign key(pname) REFERENCES PRODUCT(PNAme)
)

create table Orders(
order_id int generated always as identity,
OrderDt varchar(30) not null,
status varchar(10) not null,
email varchar(30) not null,
primary key(order_id),
foreign key(email) REFERENCES IOTUSER(email)
)
