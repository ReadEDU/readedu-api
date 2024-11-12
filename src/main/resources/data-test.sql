-- Datos para la tabla roles
INSERT INTO roles (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'READER'),
                                 (3, 'CREATOR')
    ON CONFLICT DO NOTHING;

-- Datos para la tabla users
INSERT INTO users (email, password, role_id) VALUES
                                                 ('alice.brown@example.com', 'password123', 1),  -- role_id 1 para READER
                                                 ('bob.green@example.com', 'password456', 1),
                                                 ('charlie.black@example.com', 'password789', 1),
                                                 ('john.doe@example.com', 'passwordJohn', 2),    -- role_id 2 para CREATOR
                                                 ('jane.smith@example.com', 'passwordJane', 2),
                                                 ('emily.johnson@example.com', 'passwordEmily', 2)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla readers (asegúrate de que los user_id son únicos)
INSERT INTO readers (first_name, last_name, biography, created_at, updated_at, user_id) VALUES
                                                                                            ('Alice', 'Brown', 'Biography of Alice Brown', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
                                                                                            ('Bob', 'Green', 'Biography of Bob Green', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
                                                                                            ('Charlie', 'Black', 'Biography of Charlie Black', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla creators (user_id únicos y diferentes de los de readers)
INSERT INTO creators (first_name, last_name, biography, created_at, updated_at, user_id) VALUES
                                                                                             ('John', 'Doe', 'Biography of John Doe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
                                                                                             ('Jane', 'Smith', 'Biography of Jane Smith', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
                                                                                             ('Emily', 'Johnson', 'Biography of Emily Johnson', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla categories
INSERT INTO categories (name, description, created_at, updated_at) VALUES
                                                                       ('Technology', 'Articles related to technology.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Health', 'Articles related to health and wellness.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Travel', 'Articles about travel experiences.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla plans
INSERT INTO plans (type, price) VALUES
                                    ('Basic', 9.99),
                                    ('Premium', 29.99)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla articles
INSERT INTO articles (title, slug, content, file_path, creator_id, category_id, created_at, updated_at) VALUES
                                                                                                            ('The Rise of AI', 'the-rise-of-ai', 'An in-depth look at artificial intelligence and its impact.', '/files/the-rise-of-ai.pdf', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                            ('Healthy Living Tips', 'healthy-living-tips', 'Tips and tricks for a healthier lifestyle.', '/files/healthy-living-tips.pdf', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                            ('Top 10 Travel Destinations', 'top-10-travel-destinations', 'Explore the best places to visit this year.', '/files/top-10-travel-destinations.pdf', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;


-- Datos para la tabla favorites
INSERT INTO favorites (name, reader_id, created_at, updated_at) VALUES
                                                                    ('Favorite Articles', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                    ('My Reading List', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla collection_articles
INSERT INTO collection_articles (favorite_id, article_id, added_date) VALUES
                                                                          (1, 1, CURRENT_TIMESTAMP),
                                                                          (1, 2, CURRENT_TIMESTAMP),
                                                                          (2, 3, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla comments
INSERT INTO comments (content, created_at, article_id) VALUES
                                                           ('Great article!', CURRENT_TIMESTAMP, 1),
                                                           ('Very informative, thanks!', CURRENT_TIMESTAMP, 2),
                                                           ('I love these suggestions!', CURRENT_TIMESTAMP, 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla donations
INSERT INTO donations (amount, created_at, user_id) VALUES
                                                        (50.0, CURRENT_TIMESTAMP, 4),
                                                        (100.5, CURRENT_TIMESTAMP, 5),
                                                        (75.25, CURRENT_TIMESTAMP, 6),
                                                        (20.0, CURRENT_TIMESTAMP, 1),
                                                        (30.75, CURRENT_TIMESTAMP, 2),
                                                        (45.0, CURRENT_TIMESTAMP, 3)
    ON CONFLICT DO NOTHING;
-- Datos para la tabla purchases
INSERT INTO purchases (total, payment_status, created_at, updated_at, user_id, donation_id) VALUES
                                                                                                (150.0, 'PAID', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
                                                                                                (200.5, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 2),
                                                                                                (75.75, 'FAILED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 3),
                                                                                                (120.0, 'PAID', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 4),
                                                                                                (80.0, 'PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 5),
                                                                                                (50.0, 'PAID', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 6)
    ON CONFLICT DO NOTHING;