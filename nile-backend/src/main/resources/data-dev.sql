INSERT INTO "role"("name")
    SELECT r.role FROM (VALUES ('ROLE_USER'), ('ROLE_ADMIN')) as r(role)
    WHERE NOT EXISTS(
        SELECT "name" FROM "role" WHERE "name" = 'ROLE_USER'
            OR "name" = 'ROLE_ADMIN'
    );
