-- liquor 테이블 update_at 추가
ALTER TABLE liquor
ADD COLUMN updated_at DATETIME;

-- Trigger -> 업데이트 할 때 자동으로 현재시간으로 설정
DROP TRIGGER IF EXISTS before_update_liquor;

CREATE TRIGGER before_update_liquor
BEFORE UPDATE ON liquor
FOR EACH ROW
SET NEW.updated_at = NOW();
