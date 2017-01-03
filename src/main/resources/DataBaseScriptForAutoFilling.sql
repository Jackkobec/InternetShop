CREATE TABLE user_type (
  id INT PRIMARY KEY AUTO_INCREMENT,
  userTypeName VARCHAR (22) CHARACTER SET 'utf8' NOT NULL
);

INSERT INTO user_type(userTypeName) VAlUES ('client');
INSERT INTO user_type(userTypeName) VAlUES ('admin');

CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR (35) CHARACTER SET 'utf8' NOT NULL,
  password VARCHAR (35) CHARACTER SET 'utf8' NOT NULL,
  userLanguage VARCHAR (12) CHARACTER SET 'utf8',
  userType INT(11) NOT NULL,
  FOREIGN KEY(userType) REFERENCES user_type(id)
);

INSERT INTO user(name, password, userType) VAlUES ('Vasa', '2222', 1);
INSERT INTO user(name, password, userType) VAlUES ('Jack', '7777', 2);

CREATE TABLE order_status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  orderStatusName VARCHAR (22) CHARACTER SET 'utf8' NOT NULL
);

INSERT INTO order_status(orderStatusName) VAlUES ('NEW');
INSERT INTO order_status(orderStatusName) VAlUES ('DONE');
INSERT INTO order_status(orderStatusName) VAlUES ('CANCELED');

CREATE TABLE orders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  orderDateAndTime DATETIME NOT NULL,
  summaryPrice DECIMAL(7,2) NOT NULL,
  user_id INT(11) NOT NULL,
  orderStatus INT(11) NOT NULL,
  FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY(orderStatus) REFERENCES order_status(id)
);

CREATE TABLE item_status (
  id INT PRIMARY KEY AUTO_INCREMENT,
  itemStatusName VARCHAR (22) CHARACTER SET 'utf8' NOT NULL
);

INSERT INTO item_status(itemStatusName) VAlUES ('PRESENT');
INSERT INTO item_status(itemStatusName) VAlUES ('NOT_PRESENT');

CREATE TABLE item_category (
  id INT PRIMARY KEY AUTO_INCREMENT,
  itemCategoryName VARCHAR (22) CHARACTER SET 'utf8' NOT NULL
);

INSERT INTO item_category(itemCategoryName) VAlUES ('Category1');
INSERT INTO item_category(itemCategoryName) VAlUES ('Category2');

CREATE TABLE items (
  id INT PRIMARY KEY AUTO_INCREMENT,
  itemName VARCHAR (43) CHARACTER SET 'utf8' NOT NULL,
  itemDescription VARCHAR (122) CHARACTER SET 'utf8' NOT NULL,
  itemPrice DECIMAL(7,2) NOT NULL,
  itemCategory INT(11) NOT NULL,
  itemStatus INT(11) NOT NULL,
  FOREIGN KEY(itemCategory) REFERENCES item_category(id),
  FOREIGN KEY(itemStatus) REFERENCES item_status(id)
);

CREATE TABLE order_item (
  order_id INT(11) NOT NULL,
  item_id INT(11) NOT NULL
);
