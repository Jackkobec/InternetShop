CREATE TABLE user_type (
  id INT PRIMARY KEY AUTO_INCREMENT,
  userTypeName VARCHAR (22) CHARACTER SET 'utf8'
);

INSERT INTO user_type(userTypeName) VAlUES ('client');
INSERT INTO user_type(userTypeName) VAlUES ('admin');
INSERT INTO user_type(userTypeName) VAlUES ('banned');

CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR (35) CHARACTER SET 'utf8' NOT NULL,
  password VARCHAR (35) CHARACTER SET 'utf8' NOT NULL,
  name VARCHAR (35) CHARACTER SET 'utf8',
  language VARCHAR (12) CHARACTER SET 'utf8' DEFAULT 'en_EN',
  userType INT(11) NOT NULL DEFAULT 1,
  FOREIGN KEY(userType) REFERENCES user_type(id)
);

INSERT INTO user(email, password, userType) VAlUES ('vasa@gmail.com', '2222', 1);
INSERT INTO user(email, password, userType) VAlUES ('jack@gmail.com', '7777', 2);
INSERT INTO user(email, password, userType) VAlUES ('qq@gmail.com', '2222', 3);

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
  FOREIGN KEY(user_id) REFERENCES user(id),
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
  itemCategoryName VARCHAR (22) CHARACTER SET 'utf8' NOT NULL,
  categoryDescription VARCHAR (122) CHARACTER SET 'utf8',
  categoryPicturePath300x160 VARCHAR (122) CHARACTER SET 'utf8'
);

INSERT INTO item_category(itemCategoryName, categoryPicturePath300x160) VAlUES ('For self-defense', 'view.components/images/main_categories/For self-defense.png');
INSERT INTO item_category(itemCategoryName, categoryPicturePath300x160) VAlUES ('For respect', 'view.components/images/main_categories/For respect.png');
INSERT INTO item_category(itemCategoryName, categoryPicturePath300x160) VAlUES ('Ultimate Solutions', 'view.components/images/main_categories/Ultimate Solutions.png');


CREATE TABLE item (
  id INT PRIMARY KEY AUTO_INCREMENT,
  itemName VARCHAR (43) CHARACTER SET 'utf8' NOT NULL,
  itemSmallDescription VARCHAR (122) CHARACTER SET 'utf8',
  itemFullDescription VARCHAR (3000) CHARACTER SET 'utf8',
  itemProductInfo VARCHAR (777) CHARACTER SET 'utf8',
  itemPrice DECIMAL(7,2) NOT NULL,
  itemBigPicturePath800x600 VARCHAR(122) CHARACTER SET 'utf8',
  itemSmallPicturePath350x260 VARCHAR(122) CHARACTER SET 'utf8',
  itemRating INT(11) NOT NULL,
  itemCategory INT(11) NOT NULL,
  itemStatus INT(11) NOT NULL,
  FOREIGN KEY(itemCategory) REFERENCES item_category(id),
  FOREIGN KEY(itemStatus) REFERENCES item_status(id)
);

INSERT INTO item(itemName, itemSmallDescription, itemFullDescription, itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating,
itemCategory, itemStatus) VAlUES ('Пернач ОЦ-33', 'Автоматический пистолет Стечкина 9мм',
'The Corsair Gaming Series GS600 power supply is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                            <h3>Corsair Gaming Series GS600 Features:</h3>
                            <li>It supports the latest ATX12V v2.3 standard and is backward compatible with ATX12V 2.2 and ATX12V 2.01 systems</li>
                            <li>An ultra-quiet 140mm double ball-bearing fan delivers great airflow at an very low noise level by varying fan speed in response to temperature</li>
                            <li>80Plus certified to deliver 80% efficiency or higher at normal load conditions (20% to 100% load)</li>
                            <li>0.99 Active Power Factor Correction provides clean and reliable power</li>
                            <li>Universal AC input from 90~264V — no more hassle of flipping that tiny red switch to select the voltage input!</li>
                            <li>Extra long fully-sleeved cables support full tower chassis</li>
                            <li>A three year warranty and lifetime access to Corsair’s legendary technical support and customer service</li>
                            <li>Over Current/Voltage/Power Protection, Under Voltage Protection and Short Circuit Protection provide complete component safety</li>
                            <li>Dimensions: 150mm(W) x 86mm(H) x 160mm(L)</li>
                            <li>MTBF: 100,000 hours</li>
                            <li>Safety Approvals: UL, CUL, CE, CB, FCC Class B, TÜV, CCC, C-tick</li>',
                            322.70, 'view.components/images/for_self_defense/PernachOC33_800x600.png', 'view.components/images/for_self_defense/PernachOC33_350x260.png',
                            5, 1, 1);

INSERT INTO item(itemName, itemSmallDescription, itemFullDescription, itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating,
itemCategory, itemStatus) VAlUES ('Тт', 'Пистолет обр. 1933 г. (ТТ, Тульский Токарева, Индекс ГРАУ — 56-А-132) 7.62мм',
'The Corsair Gaming Series GS600 power supply is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                            <h3>Corsair Gaming Series GS600 Features:</h3>
                            <li>Пистолет ТТ был разработан для конкурса 1929 года на новый армейский пистолет, объявленного с целью замены револьвера «наган» и нескольких моделей револьверов и пистолетов иностранного производства, находившихся на вооружении Красной Армии к середине 1920-х годов. В качестве штатного патрона был принят немецкий патрон 7,63×25 мм Маузер, который в значительных количествах закупался для состоявших на вооружении пистолетов Mauser C96[1].


Пистолеты ТТ разных лет выпуска: до 1947 (слева) и более поздний

Пистолет ТТ производства военного времени
Конкурсная комиссия во главе с М. Ф. Грушецким сочла пистолет конструкции Ф. В. Токарева наиболее подходящим для принятия на вооружение при условии, что будут устранены выявленные недостатки. Требования комиссии включали повышение точности стрельбы, облегчение усилия спуска и безопасности в обращении. За несколько месяцев работы недостатки были устранены. 23 декабря 1930 года было принято решение о дополнительных испытаниях.</li>',
                            233.00, 'view.components/images/for_self_defense/TT_800x600.png', 'view.components/images/for_self_defense/TT_350x260.png',
                            4, 1, 1);

INSERT INTO item(itemName, itemSmallDescription, itemFullDescription, itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating,
itemCategory, itemStatus) VAlUES ('FN Five-seveN', 'FN Five-seveN — самозарядный пистолет, разработанный и производимый бельгийской фирмой Fabrique Nationale of Herstal[',
'The Corsair Gaming Series GS600 power supply is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                            <h3>Corsair Gaming Series GS600 Features:</h3>
                            <li>It supports the latest ATX12V v2.3 standard and is backward compatible with ATX12V 2.2 and ATX12V 2.01 systems</li>
                            <li>An ultra-quiet 140mm double ball-bearing fan delivers great airflow at an very low noise level by varying fan speed in response to temperature</li>
                            <li>80Plus certified to deliver 80% efficiency or higher at normal load conditions (20% to 100% load)</li>
                            <li>0.99 Active Power Factor Correction provides clean and reliable power</li>
                            <li>Universal AC input from 90~264V — no more hassle of flipping that tiny red switch to select the voltage input!</li>
                            <li>Extra long fully-sleeved cables support full tower chassis</li>
                            <li>A three year warranty and lifetime access to Corsair’s legendary technical support and customer service</li>
                            <li>Over Current/Voltage/Power Protection, Under Voltage Protection and Short Circuit Protection provide complete component safety</li>
                            <li>Dimensions: 150mm(W) x 86mm(H) x 160mm(L)</li>
                            <li>MTBF: 100,000 hours</li>
                            <li>Safety Approvals: UL, CUL, CE, CB, FCC Class B, TÜV, CCC, C-tick</li>',
                            322.70, 'view.components/images/for_self_defense/fn_five-seven_800x600.png', 'view.components/images/for_self_defense/fn_five-seven_350x260.png',
                            5, 1, 1);

INSERT INTO item(itemName, itemSmallDescription, itemFullDescription, itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating,
itemCategory, itemStatus) VAlUES ('Беретта 92', 'Беретта 92 - Семейство самозарядных пистолетов 9мм',
'The Corsair Gaming Series GS600 power supply is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                            <h3>Corsair Gaming Series GS600 Features:</h3>
                            <li>It supports the latest ATX12V v2.3 standard and is backward compatible with ATX12V 2.2 and ATX12V 2.01 systems</li>
                            <li>An ultra-quiet 140mm double ball-bearing fan delivers great airflow at an very low noise level by varying fan speed in response to temperature</li>
                            <li>80Plus certified to deliver 80% efficiency or higher at normal load conditions (20% to 100% load)</li>
                            <li>0.99 Active Power Factor Correction provides clean and reliable power</li>
                            <li>Universal AC input from 90~264V — no more hassle of flipping that tiny red switch to select the voltage input!</li>
                            <li>Extra long fully-sleeved cables support full tower chassis</li>
                            <li>A three year warranty and lifetime access to Corsair’s legendary technical support and customer service</li>
                            <li>Over Current/Voltage/Power Protection, Under Voltage Protection and Short Circuit Protection provide complete component safety</li>
                            <li>Dimensions: 150mm(W) x 86mm(H) x 160mm(L)</li>
                            <li>MTBF: 100,000 hours</li>
                            <li>Safety Approvals: UL, CUL, CE, CB, FCC Class B, TÜV, CCC, C-tick</li>',
                            444.00, 'view.components/images/for_self_defense/Beretta92_800x600.png', 'view.components/images/for_self_defense/Beretta92_350x260.png',
                            5, 1, 1);

INSERT INTO item(itemName, itemSmallDescription, itemFullDescription, itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating,
itemCategory, itemStatus) VAlUES ('Walther P99', 'Walther P99 — немецкий пистолет, разрабатывался оружейной компанией «Carl Walther Sportwaffen GmbH» 19мм',
'The Corsair Gaming Series GS600 power supply is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                            <h3>Corsair Gaming Series GS600 Features:</h3>
                            <li>It supports the latest ATX12V v2.3 standard and is backward compatible with ATX12V 2.2 and ATX12V 2.01 systems</li>
                            <li>An ultra-quiet 140mm double ball-bearing fan delivers great airflow at an very low noise level by varying fan speed in response to temperature</li>
                            <li>80Plus certified to deliver 80% efficiency or higher at normal load conditions (20% to 100% load)</li>
                            <li>0.99 Active Power Factor Correction provides clean and reliable power</li>
                            <li>Universal AC input from 90~264V — no more hassle of flipping that tiny red switch to select the voltage input!</li>
                            <li>Extra long fully-sleeved cables support full tower chassis</li>
                            <li>A three year warranty and lifetime access to Corsair’s legendary technical support and customer service</li>
                            <li>Over Current/Voltage/Power Protection, Under Voltage Protection and Short Circuit Protection provide complete component safety</li>
                            <li>Dimensions: 150mm(W) x 86mm(H) x 160mm(L)</li>
                            <li>MTBF: 100,000 hours</li>
                            <li>Safety Approvals: UL, CUL, CE, CB, FCC Class B, TÜV, CCC, C-tick</li>',
                            534.00, 'view.components/images/for_self_defense/P99_800x600.png', 'view.components/images/for_self_defense/P99_350x260.png',
                            5, 1, 1);

INSERT INTO item(itemName, itemSmallDescription, itemFullDescription, itemPrice, itemBigPicturePath800x600, itemSmallPicturePath350x260, itemRating,
itemCategory, itemStatus) VAlUES ('FN FNP', 'FN FNP — самозарядный пистолет, разработанный компанией FNH-USA 9мм',
'The Corsair Gaming Series GS600 power supply is the ideal price-performance solution for building or upgrading a Gaming PC. A single +12V rail provides up to 48A of reliable, continuous power for multi-core gaming PCs with multiple graphics cards. The ultra-quiet, dual ball-bearing fan automatically adjusts its speed according to temperature, so it will never intrude on your music and games. Blue LEDs bathe the transparent fan blades in a cool glow. Not feeling blue? You can turn off the lighting with the press of a button.

                            <h3>Corsair Gaming Series GS600 Features:</h3>
                            <li>It supports the latest ATX12V v2.3 standard and is backward compatible with ATX12V 2.2 and ATX12V 2.01 systems</li>
                            <li>An ultra-quiet 140mm double ball-bearing fan delivers great airflow at an very low noise level by varying fan speed in response to temperature</li>
                            <li>80Plus certified to deliver 80% efficiency or higher at normal load conditions (20% to 100% load)</li>
                            <li>0.99 Active Power Factor Correction provides clean and reliable power</li>
                            <li>Universal AC input from 90~264V — no more hassle of flipping that tiny red switch to select the voltage input!</li>
                            <li>Extra long fully-sleeved cables support full tower chassis</li>
                            <li>A three year warranty and lifetime access to Corsair’s legendary technical support and customer service</li>
                            <li>Over Current/Voltage/Power Protection, Under Voltage Protection and Short Circuit Protection provide complete component safety</li>
                            <li>Dimensions: 150mm(W) x 86mm(H) x 160mm(L)</li>
                            <li>MTBF: 100,000 hours</li>
                            <li>Safety Approvals: UL, CUL, CE, CB, FCC Class B, TÜV, CCC, C-tick</li>',
                            577.00, 'view.components/images/for_self_defense/FN FNP_800x600.png', 'view.components/images/for_self_defense/FN FNP_350x260.png',
                            4, 1, 1);

CREATE TABLE order_item (
  order_id INT(11) NOT NULL,
  item_id INT(11) NOT NULL
);

CREATE TABLE six_item_carousel (
  item_id INT(11) NOT NULL,
  FOREIGN KEY(item_id) REFERENCES item(id)
);

INSERT INTO six_item_carousel(item_id) VAlUES (1);
INSERT INTO six_item_carousel(item_id) VAlUES (2);
INSERT INTO six_item_carousel(item_id) VAlUES (3);
INSERT INTO six_item_carousel(item_id) VAlUES (4);
INSERT INTO six_item_carousel(item_id) VAlUES (5);
INSERT INTO six_item_carousel(item_id) VAlUES (6);