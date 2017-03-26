create table user_data
(
	id SERIAL UNIQUE,
	username VARCHAR(20) NOT NULL UNIQUE ,
	password VARCHAR(60) NOT NULL
);

create index user_data_idx on user_data(id);


insert into user_data
(
	username,
	password
)
values(
	'admin',
	crypt('admin', gen_salt('bf'))
);
