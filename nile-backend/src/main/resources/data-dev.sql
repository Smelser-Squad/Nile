INSERT INTO "role"
    SELECT r.id, r.role FROM (VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN')) as r(id, role)
    WHERE NOT EXISTS(
        SELECT "name" FROM "role" WHERE "name" = 'ROLE_USER'
            OR "name" = 'ROLE_ADMIN'
    );
