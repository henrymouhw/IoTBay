/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lin
 * Created: 2021年5月14日
 */


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
