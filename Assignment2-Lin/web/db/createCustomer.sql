/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  lin
 * Created: 2021年5月8日
 */
create table CRecord(
record_id int generated always as identity,
email varchar(30) not null primary key,
Cname varchar(30) not null,
C_type varchar(30) not null,
address varchar(50) not null,
status varchar(10) not null
 )