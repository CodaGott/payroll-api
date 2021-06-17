truncate table employee cascade;
insert into employee(id, first_name, last_name, role)
values(99, 'Bob', 'Dan', 'HR'),
      (89, 'John', 'Doe', 'Accountant'),
       (69, 'Test', 'delete', 'for delete');