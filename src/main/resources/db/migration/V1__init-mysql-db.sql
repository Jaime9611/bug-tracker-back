CREATE DATABASE IF NOT EXISTS bug_tracker;
USE bug_tracker;

-- Team table
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`
(
    id VARCHAR(36) NOT NULL,
    title VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
) ENGINE = InnoDB;

-- Project table
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`
(
    project_id VARCHAR(36) NOT NULL,
    title VARCHAR(50) NOT NULL,
    team_id VARCHAR(36) DEFAULT NULL,
    starts_at DATETIME(6),
    status_id VARCHAR(36) DEFAULT NULL,
    ends_at DATETIME(6),
    PRIMARY KEY(project_id)
) ENGINE = InnoDB;

-- Ticket Type table
DROP TABLE IF EXISTS `ticket_type`;
CREATE TABLE `ticket_type`
(
    id VARCHAR(36) NOT NULL,
    title VARCHAR(30) NOT NULL,
    PRIMARY KEY(id)
) ENGINE = InnoDB;

-- Priority table
DROP TABLE IF EXISTS `priority`;
CREATE TABLE `priority`
(
    id VARCHAR(36) NOT NULL,
    title VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
) ENGINE = InnoDB;

-- Status table
DROP TABLE IF EXISTS `status` ;
CREATE TABLE `status`
(
    id VARCHAR(36) NOT NULL,
    title VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
) ENGINE = InnoDB;

-- Ticket table
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`
(
    ticket_id VARCHAR(36) NOT NULL,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    ticket_type_id VARCHAR(36) DEFAULT NULL,
    priority_id VARCHAR(36) DEFAULT NULL,
    status_id VARCHAR(36) DEFAULT NULL,
    created_at DATETIME(6) DEFAULT NULL,
    updated_at DATETIME(6) DEFAULT NULL,
    project_id VARCHAR(36) DEFAULT NULL,
    PRIMARY KEY(ticket_id)
) ENGINE = InnoDB;

-- UK CONSTRAINTS
ALTER TABLE `project`
    ADD CONSTRAINT uk_project_team UNIQUE (team_id);

-- FK CONSTRAINTS
ALTER TABLE `project`
    ADD CONSTRAINT fk_project_team FOREIGN KEY (team_id)
        REFERENCES `team` (id);
ALTER TABLE `project`
    ADD CONSTRAINT fk_project_status FOREIGN KEY (status_id)
        REFERENCES `status` (id);
ALTER TABLE `ticket`
    ADD CONSTRAINT fk_ticket_priority FOREIGN KEY (priority_id)
        REFERENCES `priority` (id);
ALTER TABLE `ticket`
    ADD CONSTRAINT fk_ticket_project FOREIGN KEY (project_id)
        REFERENCES `project` (project_id);
ALTER TABLE `ticket`
    ADD CONSTRAINT fk_ticket_status FOREIGN KEY (status_id)
        REFERENCES `status` (id);
ALTER TABLE `ticket`
    ADD CONSTRAINT fk_ticket_ticket_type FOREIGN KEY (ticket_type_id)
        REFERENCES `ticket_type` (id);
