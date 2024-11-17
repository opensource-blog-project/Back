CREATE TABLE comments
(
    comment_id INT AUTO_INCREMENT NOT NULL,
    post_id    INT  NOT NULL,
    user_id    INT  NOT NULL,
    content    TEXT NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (comment_id)
);

CREATE TABLE likes
(
    like_id INT AUTO_INCREMENT NOT NULL,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT pk_likes PRIMARY KEY (like_id)
);

CREATE TABLE post
(
    post_id INT AUTO_INCREMENT NOT NULL,
    user_id INT          NOT NULL,
    title   VARCHAR(255) NOT NULL,
    content TEXT NULL,
    CONSTRAINT pk_post PRIMARY KEY (post_id)
);

CREATE TABLE postimages
(
    image_id  INT AUTO_INCREMENT NOT NULL,
    post_id   INT          NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    CONSTRAINT pk_postimages PRIMARY KEY (image_id)
);

CREATE TABLE user
(
    user_id  INT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE likes
    ADD CONSTRAINT uc_0c241178aa654ea46c16b77b1 UNIQUE (post_id, user_id);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_POST FOREIGN KEY (post_id) REFERENCES post (post_id);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);

ALTER TABLE likes
    ADD CONSTRAINT FK_LIKES_ON_POST FOREIGN KEY (post_id) REFERENCES post (post_id);

ALTER TABLE likes
    ADD CONSTRAINT FK_LIKES_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);

ALTER TABLE postimages
    ADD CONSTRAINT FK_POSTIMAGES_ON_POST FOREIGN KEY (post_id) REFERENCES post (post_id);

ALTER TABLE post
    ADD CONSTRAINT FK_POST_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);