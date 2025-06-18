CREATE TABLE new_user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50)  NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE,
    address    VARCHAR(50)  NOT NULL,
    status     ENUM ('admin', 'user') DEFAULT 'user',
    created_at DATETIME               DEFAULT CURRENT_TIMESTAMP
);