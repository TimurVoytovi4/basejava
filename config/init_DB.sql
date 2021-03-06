create table resume
(
  uuid      char(36) not null
    constraint resume_pkey
    primary key,
  full_name text     not null
);

create table contact
(
  id          serial   not null
    constraint contact_pkey
    primary key,
  resume_uuid CHAR(36) not null references resume (uuid) on delete cascade,
  type        text     not null,
  value       text     not null
);

create unique index contact_uuid_type_index
  on contact (resume_uuid, type);

