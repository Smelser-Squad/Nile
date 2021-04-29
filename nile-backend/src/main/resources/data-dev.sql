INSERT INTO "role" ("name")
SELECT names FROM (VALUES ('ROLE_USER'), ('ROLE_ADMIN')) as names
WHERE NOT EXISTS (SELECT * FROM "role");