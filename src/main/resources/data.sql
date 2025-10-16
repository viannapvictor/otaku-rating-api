INSERT INTO users (id, user_name, name, email, password, role, active, created_at, updated_at)
VALUES (
    1,
    'admin',
    'Administrator',
    'admin@otaku.com.br',
    '$2a$10$iZxot5DeNjNJbm6nHgNjgun1s3NGDZitVB3bsezXntbORUQ2lF5Xi',
    'ADMIN',
    TRUE,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
