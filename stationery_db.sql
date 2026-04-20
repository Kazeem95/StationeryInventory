CREATE DATABASE IF NOT EXISTS stationery_db;
USE stationery_db;

DROP TABLE IF EXISTS StationeryItem;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS app_user;

CREATE TABLE Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE StationeryItem (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category_id INT,
    brand VARCHAR(100),
    price DECIMAL(10,2),
    quantity_in_stock INT DEFAULT 0,
    description TEXT,
    date_added DATE,
    FOREIGN KEY (category_id) REFERENCES Category(category_id) ON UPDATE CASCADE
);

CREATE TABLE app_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100)
);

INSERT INTO Category (category_name, description) VALUES
('Writing Instruments', 'Pens, pencils and markers'),
('Notebooks and Paper', 'Notebooks, journals and loose paper'),
('Office Supplies', 'Staplers, scissors and general office items'),
('Storage and Filing', 'Folders, binders and filing solutions'),
('Art and Craft', 'Art supplies and craft materials');

INSERT INTO StationeryItem (name, category_id, brand, price, quantity_in_stock, description, date_added) VALUES
('Black Ballpoint Pen 10-Pack', 1, 'Bic', 2.99, 150, 'Smooth writing ballpoint pens black ink', '2025-01-10'),
('Blue Rollerball Pen', 1, 'Pilot', 1.49, 200, 'Smooth rollerball pen with blue ink', '2025-01-12'),
('Graphite Pencil HB', 1, 'Staedtler', 0.79, 300, 'Standard HB graphite pencil', '2025-01-15'),
('Highlighter Set 5 Colours', 1, 'Stabilo', 4.49, 90, 'Assorted colour highlighters pack of 5', '2025-02-10'),
('A5 Hardback Journal', 2, 'Leuchtturm', 12.99, 40, 'Hardback dotted journal 249 pages', '2025-02-05'),
('A4 Spiral Notebook 200pg', 2, 'Oxford', 4.99, 80, 'Ruled spiral notebook 200 pages', '2025-01-20'),
('Stapler 26/6', 3, 'Rapesco', 6.99, 35, 'Full strip stapler for up to 25 sheets', '2025-01-18'),
('Scissors 21cm', 3, 'Fiskars', 5.49, 50, 'Stainless steel scissors', '2025-01-22'),
('Binder Clips Assorted 30-Pack', 3, 'Esselte', 3.29, 70, 'Assorted sizes binder clips', '2025-03-01'),
('Correction Tape 5-Pack', 3, 'Tipp-Ex', 7.99, 45, 'Instant correction tape multipack', '2025-03-08'),
('4-Ring Binder A4', 4, 'Avery', 3.49, 55, 'A4 4-ring binder 25mm spine', '2025-03-10'),
('A4 Document Wallet', 4, 'Leitz', 1.99, 100, 'Clear document wallet A4', '2025-03-05'),
('Desktop Organiser 6-Section', 4, 'Fellowes', 9.99, 20, 'Six section desktop organiser', '2025-03-12'),
('Coloured Pencils 24-Set', 5, 'Faber-Castell', 8.99, 60, 'Colour pencils set of 24', '2025-02-15'),
('Watercolour Paint Set 12', 5, 'Winsor', 14.99, 25, '12 colour watercolour paint set', '2025-02-20');

INSERT INTO app_user (username, password, full_name) VALUES
('admin', 'admin123', 'Admin User'),
('kazeem', 'pass1234', 'Kazeem Ariyo'),
('declan', 'pass5678', 'Declan Watts');
