create table record(
recordid int  primary key generated always as identity,
email varchar(30) not null,
loginDT varchar(30) not null,
logoutDT varchar(30)
)


create table iotuser(
userid int generated always as identity,
email varchar(30) primary key not null,
password varchar(30) not null,
usertype varchar(30) not null,
username varchar(30) not null,
gender varchar(30) not null,
dob varchar(30) not null,
status varchar(30) not null,
phone varchar(30) not null,
address varchar(30),
occupation varchar(30),
code varchar(30)
)
