INSERT INTO hash_tag values (1,'AGE','10대-20대')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (2,'AGE','30대-40대')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (3,'AGE','50대-60대')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (4,'CATEGORY','한식')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (5,'CATEGORY','중식')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (6,'CATEGORY','일식')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (7,'CATEGORY','카페')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (8,'VISIT_PURPOSE','아침')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (9,'VISIT_PURPOSE','점심')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (10,'VISIT_PURPOSE','저녁')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (11,'VISIT_PURPOSE','여행')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (12,'VISIT_PURPOSE','가족외식')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (13,'UTILITY','주차')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (14,'UTILITY','24시간영업')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (15,'REGION','서울')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (16,'REGION','경기')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);
INSERT INTO hash_tag values (17,'REGION','인천')
    ON DUPLICATE KEY UPDATE hash_tag_type = VALUES(hash_tag_type), name = VALUES(name);