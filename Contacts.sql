
-- 
-- Create the contacts table
-- The table will store contact details including
-- a unique ID, names, company, and email address.
-- 
create or replace table contacts (
    contactId int primary key,            -- Unique id for each contact
    lastName varchar(30),                 -- Last name of the contact
    firstName varchar(30),                -- First name of the contact
    company varchar(50),                  -- Company where the contact works
    email varchar(30)                     -- Contact's email address
)
;

-- 
-- Insert sample data into the contacts table
-- Each row represents a contact with an ID, name, company, and email.
-- 
insert into contacts( contactId, lastName, firstName, company, email )
    values
    ( 1, 'Munster', 'Herman', 'Monsters, Inc.', 'hmunster@monsters.com' ),
    ( 2, 'Rubble', 'Barney', 'Bedrock Construction', 'br3434@bedrock.com' ),
    ( 3, 'Simpson', 'Homer', 'Springfield Nuclear', 'simpson@spnuke.com' ),
    ( 4, 'Cartman', 'Eric', 'South Park Elementary', 'ecartman@southpark.edu' ),
    ( 5, 'McGarrett', 'Steve', 'Hawaii 5-O', 'mcgarritt@five0.hawaii.gov' )
;
