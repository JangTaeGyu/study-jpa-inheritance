

insert into categories (id, type, name, description, created_at, updated_at) values (1, 'MAIN', 'MAIN NAME', 'MAIN DESCRIPTION', now(), now());
insert into categories (id, type, name, description, created_at, updated_at, parent_main_id) values (2, 'SUB', 'SUB NAME', 'SUB DESCRIPTION', now(), now(), 1);
insert into categories (id, type, name, description, created_at, updated_at, parent_sub_id) values (3, 'SUBSUB', 'SUB SUB NAME', 'SUB SUB DESCRIPTION', now(), now(), 2);