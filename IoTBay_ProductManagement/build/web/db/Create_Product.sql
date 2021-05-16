/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  yang
 * Created: 2021-5-16
 */
create table PRODUCT (
pname varchar(300) not null primary key, 
pbrand varchar(300) not null,
ptype varchar(300) not null,
pdescription varchar(800) not null,
pprice double not null,
pstock integer not null,
pState varchar(10) 
 )



