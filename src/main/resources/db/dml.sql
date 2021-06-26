insert into user(name) values('Petras'), ('Kazys'), ('Antanas'), ('Aloyzas'), ('Martynas');

insert into book(name, author, category, language, publication_date, isbn, user_id) values
('A Game of Thrones', 'George R. R. Martin', 'Epic fantasy', 'English', '1996-08-01', '0-00-224584-1', 1),
('A Clash of Kings', 'George R. R. Martin', 'Epic fantasy', 'English', '1998-11-16', '0-00-224585-X', 1),
('A Storm of Swords', 'George R. R. Martin', 'Epic fantasy', 'English', '2000-08-08', '0-00-224586-8', 2),
('A Feast for Crows', 'George R. R. Martin', 'Epic fantasy', 'English', '2005-10-17', '0-00-224743-7', 3),
('A Dance with Dragons', 'George R. R. Martin', 'Epic fantasy', 'English', '2011-07-12', '9780007456376', null),
('The Last Wish', 'Andrzej Sapkowski', 'Fantasy', 'English', '2007-06-07', '978-0-575-07782-9', null),
('Sword of Destiny', 'Andrzej Sapkowski', 'Fantasy', 'English', '2015-05-21', '978-1-4732-1153-7', null),
('Blood of Elves', 'Andrzej Sapkowski', 'Fantasy', 'English', '2008-10-16', '978-0-575-08318-9', null),
('Time of Contempt', 'Andrzej Sapkowski', 'Fantasy', 'English', '2013-06-27', '978-0575085084', null);
