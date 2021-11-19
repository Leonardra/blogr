set foreign_key_checks=0;
truncate table post;

insert into post(`post_id`, `title`, `post_body`, `author`)
    values(110, 'The heir of africa', 'Lorem Ipsum is slechts een proeftekst', 'Oluwatobi Jolayemi'),
          (111, 'The lion', 'My first article is good', 'Oluwatobi Jolayemi'),
          (112, 'Epiphany', 'The epiphany of francesca', 'Oluwatobi Jolayemi');

set foreign_key_checks=1;