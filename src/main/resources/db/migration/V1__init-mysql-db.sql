drop table if exists ticket;
create table ticket (created_at datetime(6), updated_at datetime(6), ticket_id VARCHAR(36) not null, description varchar(255), title varchar(255) not null, primary key (ticket_id)) engine=InnoDB;
