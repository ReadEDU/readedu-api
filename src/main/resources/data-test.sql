-- Datos para la tabla roles
INSERT INTO roles (id, name) VALUES
                                 (1, 'ADMIN'),
                                 (2, 'READER'),
                                 (3, 'AUTHOR')
    ON CONFLICT DO NOTHING;

-- Datos para la tabla users
INSERT INTO users (id, email, password, role_id) VALUES
                                                     (1, 'alice@example.com', 'password123', 2),  -- 2 = READER
                                                     (2, 'bob@example.com', 'password456', 2),
                                                     (3, 'charlie@example.com', 'password789', 2)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla readers
INSERT INTO readers (id, first_name, last_name, fullname, created_at, updated_at, user_id) VALUES
                                                                                               (1, 'Alice', 'Brown', 'Alice Brown', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
                                                                                               (2, 'Bob', 'Green', 'Bob Green', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
                                                                                               (3, 'Charlie', 'Black', 'Charlie Black', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla authors
INSERT INTO authors (id, first_name, last_name, profile, created_at, updated_at, user_id) VALUES
                                                                                              (1, 'John', 'Doe', 'https://example.com/johndoe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
                                                                                              (2, 'Jane', 'Smith', 'https://example.com/janesmith', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
                                                                                              (3, 'Emily', 'Johnson', 'https://example.com/emilyjohnson', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla categories
INSERT INTO categories (id, name, description, created_at, updated_at) VALUES
                                                                           (1, 'Technology', 'Articles related to technology.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (2, 'Health', 'Articles related to health and wellness.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (3, 'Travel', 'Articles about travel experiences.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla plans
INSERT INTO plans (id, type, price) VALUES
                                        (1, 'Basic', 9.99),
                                        (2, 'Premium', 29.99)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla subscriptions
INSERT INTO subscriptions (id, price, duration, status, subscription_date, end_date, payment_status, plan_id, reader_id) VALUES
                                                                                                                             (1, '9.99', '1 Month', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1 MONTH', 'PAID', 1, 1),
                                                                                                                             (2, '29.99', '3 Months', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '3 MONTHS', 'PAID', 2, 2)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla articles
INSERT INTO articles (title, content, publication_date, author_id, category_id) VALUES
                                                                                        ('The Rise of AI', 'An in-depth look at artificial intelligence and its impact.', CURRENT_TIMESTAMP, 1, 1),
                                                                                        ('Healthy Living Tips', 'Tips and tricks for a healthier lifestyle.', CURRENT_TIMESTAMP, 2, 2),
                                                                                        ('Top 10 Travel Destinations', 'Explore the best places to visit this year.', CURRENT_TIMESTAMP, 3, 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla detail_articles
INSERT INTO detail_articles (id, name, url, file, article_id) VALUES
                                                                  (1, 'Detail for AI Article', 'https://example.com/detail-ai', 'file1.pdf', 1),
                                                                  (2, 'Detail for Health Article', 'https://example.com/detail-health', 'file2.pdf', 2),
                                                                  (3, 'Detail for Travel Article', 'https://example.com/detail-travel', 'file3.pdf', 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla favorites
INSERT INTO favorites (id, name, reader_id) VALUES
                                                (1, 'Favorite Articles', 1),
                                                (2, 'My Reading List', 2)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla collection_articles
INSERT INTO collection_articles (favorite_id, article_id, added_date) VALUES
                                                                          (1, 1, CURRENT_TIMESTAMP),
                                                                          (1, 2, CURRENT_TIMESTAMP),
                                                                          (2, 3, CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla comments
INSERT INTO comments (id, content, date, author, reader_id, article_id) VALUES
                                                                            (1, 'Great article!', CURRENT_TIMESTAMP, 'Alice', 1, 1),
                                                                            (2, 'Very informative, thanks!', CURRENT_TIMESTAMP, 'Bob', 2, 2),
                                                                            (3, 'I love these suggestions!', CURRENT_TIMESTAMP, 'Charlie', 3, 3)
    ON CONFLICT DO NOTHING;

-- Datos para la tabla notifications
INSERT INTO notifications (id, type, message, publication_date, reader_id) VALUES
                                                                               (1, 'INFO', 'Welcome to the platform!', CURRENT_TIMESTAMP, 1),
                                                                               (2, 'REMINDER', 'Your subscription will expire soon.', CURRENT_TIMESTAMP, 2)
    ON CONFLICT DO NOTHING;