create table task_data
(
	id SERIAL UNIQUE,
	name text NOT NULL,
	description text,
	finished TIMESTAMP DEFAULT NULL,
	deadline TIMESTAMP DEFAULT NULL,
	project_id INTEGER NOT NULL
	REFERENCES project_data(id)
	    ON DELETE CASCADE
);

create index task_data_idx on task_data(id, project_id);
