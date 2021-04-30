INSERT INTO "role" ("name")
SELECT 'ROLE_USER'
WHERE
    NOT EXISTS (
        SELECT "name"
            FROM "role"
            WHERE "name" = 'ROLE_USER'
    );
