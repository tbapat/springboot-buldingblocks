insert into users values (101, 'rpatil@altimetrik.com', 'RAM','PATIL','ADMIN','SSN101','rpatil');
insert into users values (102, 'schavan@altimetrik.com', 'SAMEER','CHAVAN','ADMIN','SSN102','schavan');
insert into users values (103, 'skhot@altimetrik.com', 'SACHIN','KHOT','ADMIN','SSN103','skhot');

insert into orders (orderid, orderdescription, user_id) values (2001, 'order11', 101);
insert into orders (orderid, orderdescription, user_id) values (2002, 'order12', 101);
insert into orders (orderid, orderdescription, user_id) values (2003, 'order13', 101);

insert into orders (orderid, orderdescription, user_id) values (2004, 'order21', 102);
insert into orders (orderid, orderdescription, user_id) values (2005, 'order22', 102);

insert into orders (orderid, orderdescription, user_id) values (2006, 'order31', 103);