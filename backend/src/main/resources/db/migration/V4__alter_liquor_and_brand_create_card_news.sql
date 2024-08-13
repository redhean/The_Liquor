-- update liquor
ALTER TABLE liquor
ADD COLUMN is_domestic_sale BOOLEAN,
ADD COLUMN description TEXT,
ADD COLUMN adv TEXT;

-- update brand
ALTER TABLE brand
ADD COLUMN classification_id INT,
ADD FOREIGN KEY (classification_id) REFERENCES classifications(id);

-- add card_news
CREATE TABLE card_news (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classification_id INT,
    title VARCHAR(255) NOT NULL,
    image_count INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (classification_id) REFERENCES classifications(id)
);

-- add card_news_images
CREATE TABLE card_news_images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_news_id INT,
    image_path VARCHAR(255) NOT NULL,
    display_order INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (card_news_id) REFERENCES card_news(id)
);
