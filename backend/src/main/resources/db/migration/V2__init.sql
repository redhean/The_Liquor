-- Classifications 테이블 생성
CREATE TABLE classifications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parent_id INT,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES classifications(id) ON DELETE SET NULL
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- primary_class
INSERT INTO classifications (id, parent_id, name) VALUES (1, NULL, '와인');
INSERT INTO classifications (id, parent_id, name) VALUES (2, NULL, '맥주');
INSERT INTO classifications (id, parent_id, name) VALUES (3, NULL, '위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (4, NULL, '보드카');
INSERT INTO classifications (id, parent_id, name) VALUES (5, NULL, '진');
INSERT INTO classifications (id, parent_id, name) VALUES (6, NULL, '럼');
INSERT INTO classifications (id, parent_id, name) VALUES (7, NULL, '소주');
INSERT INTO classifications (id, parent_id, name) VALUES (8, NULL, '리큐르');
INSERT INTO classifications (id, parent_id, name) VALUES (9, NULL, '브랜디');
INSERT INTO classifications (id, parent_id, name) VALUES (10, NULL, '과일주');
INSERT INTO classifications (id, parent_id, name) VALUES (11, NULL, '사케');
INSERT INTO classifications (id, parent_id, name) VALUES (12, NULL, '청주');
INSERT INTO classifications (id, parent_id, name) VALUES (13, NULL, '약주');
INSERT INTO classifications (id, parent_id, name) VALUES (14, NULL, '막걸리');
INSERT INTO classifications (id, parent_id, name) VALUES (15, NULL, '메즈칼');
INSERT INTO classifications (id, parent_id, name) VALUES (16, NULL, '백주');
INSERT INTO classifications (id, parent_id, name) VALUES (17, NULL, '황주');
INSERT INTO classifications (id, parent_id, name) VALUES (18, NULL, 'RTD');

-- secondary_class
-- 와인
INSERT INTO classifications (id, parent_id, name) VALUES (19, 1, '레드 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (20, 1, '로제 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (21, 1, '화이트 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (22, 1, '주정강화 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (23, 1, '스파클링 와인');

-- 맥주
INSERT INTO classifications (id, parent_id, name) VALUES (24, 2, '에일');
INSERT INTO classifications (id, parent_id, name) VALUES (25, 2, '라거');
INSERT INTO classifications (id, parent_id, name) VALUES (26, 2, '발포주');
INSERT INTO classifications (id, parent_id, name) VALUES (27, 2, '플레이버드 비어');
INSERT INTO classifications (id, parent_id, name) VALUES (28, 2, '무알콜 맥주');

-- 위스키
INSERT INTO classifications (id, parent_id, name) VALUES (29, 3, '스카치 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (30, 3, '아메리칸 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (31, 3, '재패니즈 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (32, 3, '아이리시 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (33, 3, '캐나디안 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (34, 3, '블랜디드 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (35, 3, '싱글 몰트 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (36, 3, '블랜디드 몰트 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (37, 3, '싱글 그레인 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (38, 3, '블랜디드 그레인 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (39, 3, '라이 위스키');
INSERT INTO classifications (id, parent_id, name) VALUES (40, 3, '플레이버드 위스키');

-- 진
INSERT INTO classifications (id, parent_id, name) VALUES (41, 5, '런던 드라이 진');
INSERT INTO classifications (id, parent_id, name) VALUES (42, 5, '쥬네바');
INSERT INTO classifications (id, parent_id, name) VALUES (43, 5, '올드 톰 진');
INSERT INTO classifications (id, parent_id, name) VALUES (44, 5, '플레이버드 진');

-- 럼
INSERT INTO classifications (id, parent_id, name) VALUES (45, 6, '화이트 럼');
INSERT INTO classifications (id, parent_id, name) VALUES (46, 6, '골드 럼');
INSERT INTO classifications (id, parent_id, name) VALUES (47, 6, '다크 럼');
INSERT INTO classifications (id, parent_id, name) VALUES (48, 6, '플레이버드 럼');

-- 소주
INSERT INTO classifications (id, parent_id, name) VALUES (49, 7, '증류식 소주');
INSERT INTO classifications (id, parent_id, name) VALUES (50, 7, '희석식 소주');
INSERT INTO classifications (id, parent_id, name) VALUES (51, 7, '리큐르 소주');

-- 리큐르
INSERT INTO classifications (id, parent_id, name) VALUES (52, 8, '베르쿠트');
INSERT INTO classifications (id, parent_id, name) VALUES (53, 8, '커피 리큐르');
INSERT INTO classifications (id, parent_id, name) VALUES (54, 8, '크림 리큐르');
INSERT INTO classifications (id, parent_id, name) VALUES (55, 8, '허브 리큐르');
INSERT INTO classifications (id, parent_id, name) VALUES (56, 8, '과일 리큐르');

-- 브랜디
INSERT INTO classifications (id, parent_id, name) VALUES (57, 9, '포도 브랜디');
INSERT INTO classifications (id, parent_id, name) VALUES (58, 9, '사과 브랜디');
INSERT INTO classifications (id, parent_id, name) VALUES (59, 9, '기타 브랜디');

-- 과일주
INSERT INTO classifications (id, parent_id, name) VALUES (60, 10, '매실주');
INSERT INTO classifications (id, parent_id, name) VALUES (61, 10, '사과주');
INSERT INTO classifications (id, parent_id, name) VALUES (62, 10, '복분자주');
INSERT INTO classifications (id, parent_id, name) VALUES (63, 10, '기타 과실주');

-- 메즈칼
INSERT INTO classifications (id, parent_id, name) VALUES (64, 15, '데킬라');

-- RTD
INSERT INTO classifications (id, parent_id, name) VALUES (65, 18, '하이볼');
INSERT INTO classifications (id, parent_id, name) VALUES (66, 18, '하드셀쳐');

-- third_class
-- 주정강화 와인
INSERT INTO classifications (id, parent_id, name) VALUES (67, 22, '포트 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (68, 22, '셰리 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (69, 22, '베르무트');

-- 스파클링 와인
INSERT INTO classifications (id, parent_id, name) VALUES (70, 23, '샴페인');

-- 에일
INSERT INTO classifications (id, parent_id, name) VALUES (71, 24, '페일 에일');
INSERT INTO classifications (id, parent_id, name) VALUES (72, 24, '사워 에일');
INSERT INTO classifications (id, parent_id, name) VALUES (73, 24, '세종');
INSERT INTO classifications (id, parent_id, name) VALUES (74, 24, '고제');
INSERT INTO classifications (id, parent_id, name) VALUES (75, 24, '쾰쉬');
INSERT INTO classifications (id, parent_id, name) VALUES (76, 24, '앰버 에일');
INSERT INTO classifications (id, parent_id, name) VALUES (77, 24, '발리 와인');
INSERT INTO classifications (id, parent_id, name) VALUES (78, 24, '브라운 에일');
INSERT INTO classifications (id, parent_id, name) VALUES (79, 24, '애비 에일');
INSERT INTO classifications (id, parent_id, name) VALUES (80, 24, '스타우트');
INSERT INTO classifications (id, parent_id, name) VALUES (81, 24, '포터');
INSERT INTO classifications (id, parent_id, name) VALUES (82, 24, '밀맥주');
INSERT INTO classifications (id, parent_id, name) VALUES (83, 24, '벨지안 에일');

-- 라거
INSERT INTO classifications (id, parent_id, name) VALUES (84, 25, '페일 라거');
INSERT INTO classifications (id, parent_id, name) VALUES (85, 25, '필스너');
INSERT INTO classifications (id, parent_id, name) VALUES (86, 25, '헬레스 라거');
INSERT INTO classifications (id, parent_id, name) VALUES (87, 25, '다크 라거');
INSERT INTO classifications (id, parent_id, name) VALUES (88, 25, '듄켈');
INSERT INTO classifications (id, parent_id, name) VALUES (89, 25, '복');
INSERT INTO classifications (id, parent_id, name) VALUES (90, 25, '비엔나 라거');
INSERT INTO classifications (id, parent_id, name) VALUES (91, 25, '발틱 포터');
INSERT INTO classifications (id, parent_id, name) VALUES (92, 25, '미국식 부가물 라거');
INSERT INTO classifications (id, parent_id, name) VALUES (93, 25, '슈바르츠 비어');
INSERT INTO classifications (id, parent_id, name) VALUES (94, 25, '메르첸');
INSERT INTO classifications (id, parent_id, name) VALUES (95, 25, '라우흐 비어');
INSERT INTO classifications (id, parent_id, name) VALUES (96, 25, '드라이 비어');

-- 아메리칸 위스키
INSERT INTO classifications (id, parent_id, name) VALUES (97, 30, '버번 위스키');

-- 포도 브랜디
INSERT INTO classifications (id, parent_id, name) VALUES (98, 57, '꼬냑');
INSERT INTO classifications (id, parent_id, name) VALUES (99, 57, '아르마냑');

-- 사과 브랜디
INSERT INTO classifications (id, parent_id, name) VALUES (100, 58, '깔바도스');

-- 데킬라
INSERT INTO classifications (id, parent_id, name) VALUES (101, 64, '블랑코');
INSERT INTO classifications (id, parent_id, name) VALUES (102, 64, '레포사도');
INSERT INTO classifications (id, parent_id, name) VALUES (103, 64, '아네호');

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
