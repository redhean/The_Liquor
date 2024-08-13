-- update liquor
ALTER TABLE liquor
ADD COLUMN is_domestic_sale BOOLEAN,
ADD COLUMN description TEXT,
ADD COLUMN adv INT DEFAULT 0;

-- update brand
ALTER TABLE brand
ADD COLUMN classification_id INT,
ADD FOREIGN KEY (classification_id) REFERENCES classifications(id);

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

