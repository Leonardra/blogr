create database blog_db;
create user 'blogger_user'@'localhost' identified by 'myBlogger';
grant all privileges on blog_db.* to 'blogger_user'@'localhost';

flush privileges