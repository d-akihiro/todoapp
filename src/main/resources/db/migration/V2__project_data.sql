create table project_data
(
	id SERIAL UNIQUE,
	name text NOT NULL,
	description text,
	user_id Integer NOT NULL
	REFERENCES user_data(id)
	    ON DELETE CASCADE
);

create index project_data_idx on project_data(id, user_id);