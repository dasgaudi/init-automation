--liquibase formatted sql
--changeset ewalter:seed-data splitStatements:true
INSERT INTO region (id, name) VALUES
    (UNHEX(REPLACE(UUID(),'-','')), 'NFC North'),
    (UNHEX(REPLACE(UUID(),'-','')), 'NFC East'),
    (UNHEX(REPLACE(UUID(),'-','')), 'AFC West'),
    (UNHEX(REPLACE(UUID(),'-','')), 'AFC East');

INSERT INTO facility (id, region_id, name) SELECT UNHEX(REPLACE(UUID(),'-','')), id, 'Minnesota Vikings' FROM region WHERE name = 'NFC North';
INSERT INTO facility (id, region_id, name) SELECT UNHEX(REPLACE(UUID(),'-','')), id, 'Chicago Bears' FROM region WHERE name = 'NFC North';
INSERT INTO facility (id, region_id, name) SELECT UNHEX(REPLACE(UUID(),'-','')), id, 'Dallas Cowboys' FROM region WHERE name = 'NFC East';
INSERT INTO facility (id, region_id, name) SELECT UNHEX(REPLACE(UUID(),'-','')), id, 'Kansas City Chiefs' FROM region WHERE name = 'AFC West';
INSERT INTO facility (id, region_id, name) SELECT UNHEX(REPLACE(UUID(),'-','')), id, 'Denver Broncos' FROM region WHERE name = 'AFC West';

INSERT INTO customer (id, name, address, city, state, postal_code) VALUES
    (UNHEX(REPLACE(UUID(),'-','')), 'name-111', 'John Doe Street', 'Washington', 'WA', '55555'),
    (UNHEX(REPLACE(UUID(),'-','')), 'name-222', 'John Dane Street', 'Washington', 'DC', '77777'),
    (UNHEX(REPLACE(UUID(),'-','')), 'name-333', 'Job Doe Street', 'Seattle', 'WA', '57778'),
    (UNHEX(REPLACE(UUID(),'-','')), 'name-444', 'John Doe Avenue', 'Fargo', 'ND', '78899');

INSERT INTO seed (id, name, company, maturity, is_active, is_discontinued, has_seed_co_letter) VALUES
    (UNHEX(REPLACE(UUID(),'-','')), 'Seed A', 'Company A', '90 Days', 1, 0, 0),
    (UNHEX(REPLACE(UUID(),'-','')), 'Seed B', 'Company B', '75 Days', 0, 1, 0),
    (UNHEX(REPLACE(UUID(),'-','')), 'Seed C', 'Company C', '60 Days', 1, 0, 0);

INSERT INTO seed_trait (id, trait, is_active) VALUES
    (UNHEX(REPLACE(UUID(),'-','')), 'Trait A', 1),
    (UNHEX(REPLACE(UUID(),'-','')), 'Trait B', 1),
    (UNHEX(REPLACE(UUID(),'-','')), 'Trait C', 1),
    (UNHEX(REPLACE(UUID(),'-','')), 'Trait D', 0);

INSERT INTO seed_seed_trait_xref (seed_id, seed_trait_id) SELECT seed.id, seed_trait.id FROM seed, seed_trait WHERE seed.name = 'Seed A' AND seed_trait.trait = 'Trait A';
INSERT INTO seed_seed_trait_xref (seed_id, seed_trait_id) SELECT seed.id, seed_trait.id FROM seed, seed_trait WHERE seed.name = 'Seed A' AND seed_trait.trait = 'Trait B';
INSERT INTO seed_seed_trait_xref (seed_id, seed_trait_id) SELECT seed.id, seed_trait.id FROM seed, seed_trait WHERE seed.name = 'Seed A' AND seed_trait.trait = 'Trait D';
INSERT INTO seed_seed_trait_xref (seed_id, seed_trait_id) SELECT seed.id, seed_trait.id FROM seed, seed_trait WHERE seed.name = 'Seed B' AND seed_trait.trait = 'Trait A';
INSERT INTO seed_seed_trait_xref (seed_id, seed_trait_id) SELECT seed.id, seed_trait.id FROM seed, seed_trait WHERE seed.name = 'Seed B' AND seed_trait.trait = 'Trait C';
INSERT INTO seed_seed_trait_xref (seed_id, seed_trait_id) SELECT seed.id, seed_trait.id FROM seed, seed_trait WHERE seed.name = 'Seed C' AND seed_trait.trait = 'Trait C';