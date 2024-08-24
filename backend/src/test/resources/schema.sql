-- Before
DROP TABLE IF EXISTS test CASCADE;
DROP TABLE IF EXISTS brand CASCADE;
DROP TABLE IF EXISTS card_news CASCADE;
DROP TABLE IF EXISTS card_news_images CASCADE;
DROP TABLE IF EXISTS classifications CASCADE;
DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS liquor CASCADE;
DROP TABLE IF EXISTS producer CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Test
CREATE TABLE test
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

-- Classifications 테이블 생성
CREATE TABLE classifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parent_id INT,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES classifications(id) ON DELETE SET NULL
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Producer 테이블 생성
CREATE TABLE producer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Brand 테이블 생성
CREATE TABLE brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producer_id INT,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(255),
    classification_id INT,
    FOREIGN KEY (classification_id) REFERENCES classifications(id),
    FOREIGN KEY (producer_id) REFERENCES producer(id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Liquor 테이블 생성
CREATE TABLE liquor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    producer_id INT,
    brand_id BIGINT,
    classification_id INT,
    korean_name VARCHAR(255),
    english_name VARCHAR(255),
    country VARCHAR(100),
    alcohol FLOAT,
    aged INT,
    price VARCHAR(255),
    ibu INT,
    is_domestic_sale BOOLEAN,
    description TEXT,
    adv INT DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (producer_id) REFERENCES producer(id),
    FOREIGN KEY (brand_id) REFERENCES brand(id),
    FOREIGN KEY (classification_id) REFERENCES classifications(id)
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Images 테이블 생성
CREATE TABLE images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entity_id BIGINT NOT NULL,
    entity_type int NOT NULL,
    image_path VARCHAR(255) NOT NULL
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Users 테이블 생성
-- user는 SQL 예약어라서 대괄호로 묶어주거나 users로 변경
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_superuser BOOLEAN DEFAULT FALSE,
    is_staff BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- add card_news
-- card_news_images 가 변경되면 update_at 함께 변경
CREATE TABLE card_news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classification_id INT,
    title VARCHAR(255) NOT NULL,
    first_image_path VARCHAR(255) NOT NULL,
    image_count INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (classification_id) REFERENCES classifications(id)
);

-- add card_news_images
CREATE TABLE card_news_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    card_news_id INT,
    image_path VARCHAR(255) NOT NULL,
    display_order INT NOT NULL,
    FOREIGN KEY (card_news_id) REFERENCES card_news(id)
);