-- Classifications
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


-- Producer
INSERT INTO producer (id, name) VALUES (1, 'A Company');
INSERT INTO producer (id, name) VALUES (2, 'B Company');
INSERT INTO producer (id, name) VALUES (3, 'C Company');
INSERT INTO producer (id, name) VALUES (4, 'D Company');
INSERT INTO producer (id, name) VALUES (5, 'E Company');
INSERT INTO producer (id, name) VALUES (6, 'F Company');

-- Brand
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (1, 1, 'A Brand', 'FFFFFF', 1);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (2, 1, 'B Brand', '123456', 2);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (3, 1, 'C Brand', 'CD12GD', 3);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (4, 2, 'D Brand', 'FFFFFF', 4);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (5, 2, 'E Brand', 'CD12GD', 5);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (6, 2, 'F Brand', 'FFFFFF', 6);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (7, 3, 'G Brand', 'FFFFFF', 7);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (8, 3, 'H Brand', '123456', 8);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (9, 3, 'I Brand', 'FFFFFF', 9);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (10, 4, 'J Brand', 'CD12GD', 10);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (11, 4, 'K Brand', '000000', 11);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (12, 4, 'L Brand', 'FFFFFF', 12);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (13, 5, 'M Brand', '123456', 13);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (14, 5, 'N Brand', 'FFFFFF', 14);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (15, 5, 'O Brand', '000000', 15);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (16, 6, 'P Brand', 'CD12GD', 16);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (17, 6, 'Q Brand', 'FFFFFF', 17);
INSERT INTO brand (id, producer_id, name, color, classification_id) VALUES (18, 6, 'R Brand', '123456', 18);

-- Liquor
INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 1, 1, 'A 레드 와인', 'A red wine', '대한민국', 4.7, 13, '대략 3만원', 8, TRUE, '바디감이 훌륭함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 2, 2, 'A 로제 와인', 'A rose wine', '대한민국', 4.5, 5, '대략 6만원', 9, TRUE, '생각보다 드라이함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 3, 3, 'A 화이트 와인', 'A white wine', '대한민국', 3.8, 8, '대략 8만원', 3, TRUE, '달달함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 1, 4, 'A 주정강화 와인', 'A fortified wine', '대한민국', 12, 17, '대략 12만원', 4, TRUE, '도수가 쎔');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 2, 5, 'A 스파클링 와인', 'A sparkling wine', '대한민국', 3.7, 1, '대략 2만원', 6, TRUE, '와인 초보들이 즐기기 좋음');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 4, 6, 'B 에일', 'B Ale', '미국', 4.7, 13, '대략 3만원', 8, TRUE, '바디감이 훌륭함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 5, 7, 'B 라거', 'B Lager', '미국', 4.5, 5, '대략 6만원', 9, TRUE, '생각보다 드라이함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 6, 8, 'B 발포주', 'B Feelight', '미국', 3.8, 8, '대략 8만원', 3, TRUE, '달달함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 4, 9, 'B 플레이버드 비어', 'B PlayBird', '미국', 12, 17, '대략 12만원', 4, TRUE, '도수가 쎔');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 5, 10, 'B 무알콜 맥주', 'B Non-Alc', '미국', 3.7, 1, '대략 2만원', 6, TRUE, '와인 초보들이 즐기기 좋음');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 1, 11, 'C 레드 와인', 'C red wine', '대한민국', 4.7, 13, '대략 3만원', 8, TRUE, '바디감이 훌륭함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 2, 12, 'C 로제 와인', 'C rose wine', '대한민국', 4.5, 5, '대략 6만원', 9, TRUE, '생각보다 드라이함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 3, 13, 'C 화이트 와인', 'C white wine', '대한민국', 3.8, 8, '대략 8만원', 3, TRUE, '달달함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 1, 14, 'C 주정강화 와인', 'C fortified wine', '대한민국', 12, 17, '대략 12만원', 4, TRUE, '도수가 쎔');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 2, 15, 'C 스파클링 와인', 'C sparkling wine', '대한민국', 3.7, 1, '대략 2만원', 6, TRUE, '와인 초보들이 즐기기 좋음');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 4, 16, 'D 에일', 'D Ale', '미국', 4.7, 13, '대략 3만원', 8, TRUE, '바디감이 훌륭함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 5, 17, 'D 라거', 'D Lager', '미국', 4.5, 5, '대략 6만원', 9, TRUE, '생각보다 드라이함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 6, 18, 'D 발포주', 'D Feelight', '미국', 3.8, 8, '대략 8만원', 3, TRUE, '달달함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 4, 19, 'D 플레이버드 비어', 'D PlayBird', '미국', 12, 17, '대략 12만원', 4, TRUE, '도수가 쎔');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 5, 20, 'D 무알콜 맥주', 'D Non-Alc', '미국', 3.7, 1, '대략 2만원', 6, TRUE, '와인 초보들이 즐기기 좋음');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 1, 21, 'E 레드 와인', 'E red wine', '대한민국', 4.7, 13, '대략 3만원', 8, TRUE, '바디감이 훌륭함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 2, 22, 'E 로제 와인', 'E rose wine', '대한민국', 4.5, 5, '대략 6만원', 9, TRUE, '생각보다 드라이함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 3, 23, 'E 화이트 와인', 'E white wine', '대한민국', 3.8, 8, '대략 8만원', 3, TRUE, '달달함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 1, 24, 'E 주정강화 와인', 'E fortified wine', '대한민국', 12, 17, '대략 12만원', 4, TRUE, '도수가 쎔');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (1, 2, 25, 'E 스파클링 와인', 'E sparkling wine', '대한민국', 3.7, 1, '대략 2만원', 6, TRUE, '와인 초보들이 즐기기 좋음');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 4, 26, 'F 에일', 'F Ale', '미국', 4.7, 13, '대략 3만원', 8, TRUE, '바디감이 훌륭함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 5, 27, 'F 라거', 'F Lager', '미국', 4.5, 5, '대략 6만원', 9, TRUE, '생각보다 드라이함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 6, 28, 'F 발포주', 'F Feelight', '미국', 3.8, 8, '대략 8만원', 3, TRUE, '달달함');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 4, 29, 'F 플레이버드 비어', 'F PlayBird', '미국', 12, 17, '대략 12만원', 4, TRUE, '도수가 쎔');

INSERT INTO liquor (producer_id, brand_id, classification_id, korean_name, english_name, country, alcohol, aged, price, ibu, is_domestic_sale, description)
VALUES (2, 5, 30, 'F 무알콜 맥주', 'F Non-Alc', '미국', 3.7, 1, '대략 2만원', 6, TRUE, '와인 초보들이 즐기기 좋음');
