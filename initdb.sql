CREATE DATABASE [healthcare_final];
GO

USE [healthcare_final];
GO

CREATE TABLE medicines (
	medicine_id int IDENTITY(1,1) NOT NULL,
	medicine_name nvarchar(100) NOT NULL,
	description nvarchar(MAX) NULL,
	quantity_in_stock int NOT NULL,
	price decimal(10,2) NOT NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	CONSTRAINT PK_medicines PRIMARY KEY (medicine_id)
);

CREATE TABLE roles (
	role_id int IDENTITY(1,1) NOT NULL,
	role_name nvarchar(50) NOT NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	CONSTRAINT PK_roles PRIMARY KEY (role_id),
	CONSTRAINT UQ_roles UNIQUE (role_name)
);

CREATE TABLE services (
	service_id int IDENTITY(1,1) NOT NULL,
	service_name nvarchar(100) NOT NULL,
	description nvarchar(MAX) NULL,
	price decimal(10,2) NOT NULL,
	duration int NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	[image] nvarchar(MAX) NULL,
	icon varchar(MAX) NULL,
	CONSTRAINT PK_services PRIMARY KEY (service_id)
);

CREATE TABLE time_slots (
	timeslot_id int IDENTITY(1,1) NOT NULL,
	[time] varchar(5) NOT NULL,
	CONSTRAINT PK_time_slots PRIMARY KEY (timeslot_id)
);

CREATE TABLE users (
	user_id int IDENTITY(1,1) NOT NULL,
	username nvarchar(50) NOT NULL,
	password varchar(MAX) NULL,
	first_name nvarchar(50) NOT NULL,
	last_name nvarchar(50) NOT NULL,
	email nvarchar(100) NOT NULL,
	phone nvarchar(20) NULL,
	country nvarchar(50) NULL,
	[language] nvarchar(20) NULL,
	gender nvarchar(10) NULL,
	date_of_birth date NULL,
	is_active bit DEFAULT 1 NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	photos varchar(MAX) NULL,
	CONSTRAINT PK_users PRIMARY KEY (user_id),
	CONSTRAINT UQ_users UNIQUE (username),
	CONSTRAINT CK_users_gender CHECK (([gender]='MALE' OR [gender]='FEMALE' OR [gender]='OTHER'))
);

CREATE TABLE appointments (
	appointment_id int IDENTITY(1,1) NOT NULL,
	customer_id int NULL,
	doctor_id int NULL,
	service_id int NULL,
	appointment_date date NOT NULL,
	status nvarchar(10) DEFAULT 'PENDING' NULL,
	notes nvarchar(MAX) NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	time_slot_id int NULL,
	phone varchar(15) NOT NULL,
	customer_name nvarchar(255) NULL,
	CONSTRAINT PK_appointments PRIMARY KEY (appointment_id),
	CONSTRAINT FK_Appointments_TimeSlots FOREIGN KEY (time_slot_id) REFERENCES time_slots(timeslot_id),
	CONSTRAINT FK_appointments_customer FOREIGN KEY (customer_id) REFERENCES users(user_id),
	CONSTRAINT FK_appointments_doctor FOREIGN KEY (doctor_id) REFERENCES users(user_id),
	CONSTRAINT FK_appointments_service FOREIGN KEY (service_id) REFERENCES services(service_id),
	CONSTRAINT CK_appointments_status CHECK (([status]='PENDING' OR [status]='CONFIRMED' OR [status]='CANCELLED' OR [status]='COMPLETED'))
);

CREATE TABLE inventory (
	inventory_id int IDENTITY(1,1) NOT NULL,
	medicine_id int NULL,
	stock_in int NULL,
	stock_out int NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	CONSTRAINT PK_inventory PRIMARY KEY (inventory_id),
	CONSTRAINT FK_inventory_medicine FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);

CREATE TABLE medical_records (
	record_id int IDENTITY(1,1) NOT NULL,
	patient_id int NULL,
	doctor_id int NULL,
	appointment_id int NULL,
	diagnosis nvarchar(MAX) NULL,
	treatment nvarchar(MAX) NULL,
	created_at datetime DEFAULT getdate() NULL,
	updated_at datetime DEFAULT getdate() NULL,
	CONSTRAINT PK_medical_records PRIMARY KEY (record_id),
	CONSTRAINT FK_medical_records_appointment FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);

CREATE TABLE notifications (
	notification_id int IDENTITY(1,1) NOT NULL,
	recipient_user_id int NOT NULL,
	message nvarchar(255) NOT NULL,
	is_read bit DEFAULT 0 NULL,
	created_at datetime DEFAULT getdate() NOT NULL,
	CONSTRAINT PK_notifications PRIMARY KEY (notification_id),
	CONSTRAINT FK_notifications_user FOREIGN KEY (recipient_user_id) REFERENCES users(user_id)
);

CREATE TABLE user_roles (
	user_id int NOT NULL,
	role_id int NOT NULL,
	CONSTRAINT PK_user_roles PRIMARY KEY (user_id, role_id),
	CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(role_id),
	CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);


INSERT INTO roles (role_name) 
VALUES ('ADMIN'), 
       ('USER'), 
       ('STAFF'), 
       ('DOCTOR');

INSERT INTO users (username, password, first_name, last_name, email, phone, country, language, gender, date_of_birth, photos, is_active)
VALUES 
('admin@gmail.com', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Admin', 'User', 'admin@gmail.com', '0987654321', 'Country A', 'English', 'MALE', '1980-01-01', 'avatar.jpg', 1),
('dmanh@gmail.com', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Regular', 'User', 'user@gmail.com', '0123456789', 'Country B', 'English', 'FEMALE', '1990-02-02', 'avatar.jpg', 1),
('staff@gmail.com', '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Staff', 'Member', 'staff@gmail.com', '0312456789', 'Country C', 'English', 'MALE', '1985-03-03', 'avatar.jpg', 1),
('doctor@gmail.com','5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 'Doctor', 'Smith', 'doctor@gmail.com', '0431256789', 'Country D', 'English', 'FEMALE', '1988-04-04', 'avatar.jpg', 1);


INSERT INTO user_roles (user_id, role_id)
VALUES 
((SELECT user_id FROM users WHERE username = 'admin@gmail.com'), (SELECT role_id FROM roles WHERE role_name = 'ADMIN')),
((SELECT user_id FROM users WHERE username = 'dmanh@gmail.com'), (SELECT role_id FROM roles WHERE role_name = 'USER')),
((SELECT user_id FROM users WHERE username = 'staff@gmail.com'), (SELECT role_id FROM roles WHERE role_name = 'STAFF')),
((SELECT user_id FROM users WHERE username = 'doctor@gmail.com'), (SELECT role_id FROM roles WHERE role_name = 'DOCTOR'));

INSERT INTO time_slots ([time])
VALUES
('08:00'),
('08:30'),
('09:00'),
('09:30'),
('10:00'),
('10:30'),
('11:00'),
('11:30'),
('12:00'),
('12:30'),
('13:00'),
('13:30'),
('14:00'),
('14:30'),
('15:00'),
('15:30'),
('16:00'),
('16:30'),
('17:00'),
('17:30'),
('18:00'),
('18:30'),
('19:00');

CREATE TABLE prescriptions (
	prescription_id INT IDENTITY(1,1) NOT NULL,
	medical_record_id INT NOT NULL,
	medicine_id INT NOT NULL,
	quantity_prescribed INT NOT NULL,
	notes NVARCHAR(MAX) NULL,
	created_at DATETIME DEFAULT GETDATE() NOT NULL,
	updated_at DATETIME DEFAULT GETDATE() NULL,
	CONSTRAINT PK_prescriptions PRIMARY KEY (prescription_id),
	CONSTRAINT FK_prescriptions_medical_record FOREIGN KEY (medical_record_id) REFERENCES medical_records(record_id),
	CONSTRAINT FK_prescriptions_medicine FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);

-- Tạo bảng medicine_purchases để lưu trữ thông tin về các lần mua thuốc của khách hàng
CREATE TABLE medicine_purchases (
	purchase_id INT IDENTITY(1,1) NOT NULL,
	customer_id INT NOT NULL,
	prescription_id INT NULL, -- Liên kết tùy chọn đến đơn thuốc
	medicine_id INT NOT NULL,
	quantity_purchased INT NOT NULL,
	total_price DECIMAL(10,2) NOT NULL,
	purchase_date DATETIME DEFAULT GETDATE() NOT NULL,
	CONSTRAINT PK_medicine_purchases PRIMARY KEY (purchase_id),
	CONSTRAINT FK_medicine_purchases_customer FOREIGN KEY (customer_id) REFERENCES users(user_id),
	CONSTRAINT FK_medicine_purchases_prescription FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
	CONSTRAINT FK_medicine_purchases_medicine FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);


CREATE TRIGGER trg_UpdateMedicineQuantity
ON healthcare_final.dbo.prescriptions
AFTER INSERT
AS
BEGIN
    DECLARE @medicine_id INT;
    DECLARE @quantity_prescribed INT;

    DECLARE cur CURSOR FOR 
    SELECT medicine_id, quantity_prescribed
    FROM inserted;

    OPEN cur;
    FETCH NEXT FROM cur INTO @medicine_id, @quantity_prescribed;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        UPDATE healthcare_final.dbo.medicines
        SET quantity_in_stock = quantity_in_stock - @quantity_prescribed
        WHERE medicine_id = @medicine_id;

        FETCH NEXT FROM cur INTO @medicine_id, @quantity_prescribed;
    END;

    CLOSE cur;
    DEALLOCATE cur;
END;

INSERT INTO healthcare_final.dbo.medicines (medicine_name, description, quantity_in_stock, price)
VALUES
    ('Pain Relief Gel', N'Dùng để giảm đau răng và giảm đau tại chỗ', 50, 150.00),
    ('Toothache Drops', N'Giảm đau nhanh cho các trường hợp đau răng', 80, 120.50),
    ('Oral Antiseptic Rinse', N'Nước súc miệng khử trùng để điều trị đau răng và sưng', 100, 80.75),
    ('Dental Pain Tablets', N'Thuốc giảm đau cho các cơn đau răng', 120, 95.00),
    ('Gingivitis Treatment Gel', N'Thuốc điều trị viêm nướu và đau răng', 75, 135.25),
    ('Ibuprofen 200mg', N'Giảm đau và kháng viêm, dùng cho đau răng và đau cơ', 200, 50.00),
    ('Paracetamol 500mg', N'Thuốc giảm đau nhẹ đến trung bình, dùng cho đau răng', 180, 45.00),
    ('Dental Numbing Gel', N'Gel gây tê nhanh chóng để giảm đau răng tạm thời', 60, 165.75),
    ('Clove Oil', N'Dầu đinh hương để giảm đau răng tự nhiên', 150, 110.00),
    ('Antibiotic Oral Capsule', N'Thuốc kháng sinh dùng trong trường hợp nhiễm trùng răng miệng', 90, 210.00),
    ('Tooth Pain Spray', N'Spray giảm đau nhanh cho các vùng răng miệng', 65, 130.50);

	INSERT INTO healthcare_final.dbo.services (service_name, description, price, duration, created_at, updated_at, [image], icon)
VALUES 
('Mắc cài thường Mini diamond', 
 'Độ bền cao, cứng chắc, lực tác động đều và ổn định. Hiệu quả tốt với các trường hợp sai lệch răng từ đơn giản tới phức tạp. Chi phí hợp lý, phù hợp với tất cả mọi người.', 
 30000000.00, 
 45, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/04/d1.jpg', 
 NULL),
('Mắc cài kim loại tự động damon Q', 
 'Hệ thống nắp trượt tự động giúp lực tác động lên răng đều và liên tục. Ít đau, không cần siết dây cung thường xuyên. Giảm thiểu được các giai đoạn như nong hàm, nhổ răng. Thời gian niềng được rút ngắn, nhanh hơn 6 - 9 tháng.', 
 40000000.00, 
 30, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/04/d4.jpg', 
 NULL),
('Mắc cài sứ thường Symetri Clear', 
 'Chất liệu sứ cao cấp, lành tính, phù hợp với tất cả mọi người. Tính thẩm mỹ cao, giúp bạn tự tin khi giao tiếp. Lực tác động đều, ổn định, hiệu quả với mọi trường hợp răng mọc sai lệch.', 
 40000000.00, 
 30, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/04/d2.jpg', 
 NULL),
('Mắc cài sứ động Damon Clear 2', 
 'Chốt tự động giữ dây cung chắc chắn trong rãnh mắc cài, ngăn chặn xô lệch, bung tuột. Chịu lực tốt, lực tác động lên răng ổn định. Đảm bảo được tính thẩm mỹ khi niềng. Thời gian niềng nhanh hơn, số lần tái khám ít hơn.', 
 50000000.00, 
 30, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/04/d3.jpg', 
 NULL),
('Khay trong suốt Invisalign', 
 'Các trường hợp răng sai lệch từ đơn giản đến phức tạp. Toàn hàm, gồm điều chỉnh khớp cắn. Phù hợp với trẻ em thanh niên và người lớn. Được sử dụng máng trong suốt làm phương tiện di chuyển răng. Các khay được lắp vừa vặn với khung răng có những điểm tạo lực giúp răng đến vị trí mong muốn. Thiết kế trong suốt đảm bảo tính thẩm mỹ, dễ dàng tháo lắp để vệ sinh. Khay đeo mềm dẻo, dễ chịu.', 
 60000000.00, 
 60, 
 getdate(), 
 getdate(), 
 'https://niengvohinh.vn/wp-content/uploads/2023/06/ddd95c5cd5.jpg', 
 NULL),
('Gói niềng Invisalign tiết kiệm Essentials', 
 'Các trường hợp răng chen chúc, khe thưa từ nhẹ đến trung bình hoặc cần niềng lại sau tái phát, niềng đợt 2. Toàn hàm, cải thiện nụ cười, phù hợp với người lớn - Các khay được lắp vừa vặn với khung răng có những điểm tạo lực giúp răng đến vị trí mong muốn. Thiết kế trong suốt đảm bảo tính thẩm mỹ, dễ dàng tháo lắp để vệ sinh, khay niềng cứng hơn.', 
 55000000.00, 
 45, 
 getdate(), 
 getdate(), 
 'https://th.bing.com/th/id/R.3c9eedadbf79a0e9732017b95fbb466c?rik=yzv4%2bACOSrhDkg&pid=ImgRaw&r=0', 
 NULL),
('Bọc sứ Titan', 
 'Răng bị sứt mẻ lớn, răng bị sâu lớn, dễ bị bong tróc thì bọc rang sứ Titan giúp lấy lại hình dáng ban đầu. Giúp bảo vệ răng tránh ê buốt khó chịu khi tiếp súc trực tiếp với thực phẩm. Có chi phí thấp.', 
 2000000.00, 
 50, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/03/truong-hop-nen-boc-rang-su-titan-1.jpg', 
 NULL),
('Bọc răng sứ Venus', 
 'Bọc răng sứ Venus là dòng sản phẩm răng toàn sứ của Đức, chất liệu sứ nguyên chất để tránh tình trạng đen viền, phù hợp với răng cửa vì độ chịu lực thấp hơn, không gây kích ứng, độ bền cao, giá cả hợp lý.', 
 4000000.00, 
 60, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/09/rang1.png', 
 NULL),
('Bọc sứ Cercon/Ceramill', 
 'Loại răng sứ này có độ bền cao, chịu được áp lực lớn và tính thẩm mỹ cao, ôm sát mão răng thật bởi kỹ thuật tinh vi trên máy tính. Tương thích tuyệt đối với môi trường miệng, không gây ra các tác dụng phụ nào khi sử dụng và có tuổi thọ cao.', 
 5000000.00, 
 80, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/09/rang2.png', 
 NULL),
('Bọc sứ Emax', 
 'Lành tính với cơ thể, cảm giác thoải mái khi sử dụng, tính thẩm mỹ cao. Với cấu tạo đặc biệt khi khung sườn bên trong không phải hợp kim như răng sứ mà là những sợi sứ thủy tinh nguyên chất. Vì vậy, nó có màu sắc đẹp tự nhiên như răng thật với độ thấu quang cao. Phù hợp với những người muốn cải thiện răng bị ố vàng, xỉn màu. Và có tuổi thọ cao.', 
 6000000.00, 
 100, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/09/rang3.png', 
 NULL),
('Bọc sứ Lava', 
 'Có mão sứ được nung đúc, phủ lớp sứ cao cấp với độ trong đục đạt chuẩn giống thật tới 99%. Ngoài ra, răng này còn chống bám dính, bền màu, không sợ bị ố vàng.', 
 7000000.00, 
 50, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/09/rang4.png', 
 NULL),
('Nhổ răng 8 trên - thường', 
 'Nhổ răng khôn là một tiểu phẫu để lấy bỏ một hoặc nhiều răng số 8 (gọi là răng khôn) mọc trong cùng hàm răng. Răng khôn mọc đúng chỗ sẽ không gây nguy hiểm gì. Nhưng khi mọc lệch nó sẽ gây các vấn đề về sâu răng, bệnh nướu răng, viêm quanh thân răng. Với các trường hợp vậy nên nhổ răng. Với phương pháp nhổ răng truyền thống, bác sĩ sẽ dùng lực từ kìm, bẩy để nhổ răng khôn để vạt nướu, mở ổ răng để lấy răng. Người bệnh cần há miệng trong suốt quá trình nhổ răng, tương đối lâu, tùy thuộc vào mức độ phức tạp của vị trí răng.', 
 1500000.00, 
 80, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2021/03/nho-rang-khon-moc-ngam.jpg', 
 NULL),
('Nhổ răng 8 trên - nhổ máy', 
 'Công nghệ nhổ răng mới bằng máy Piezotome sử dụng các đầu mũi khoan chuyên dụng. Khi nhổ, bệnh nhân chỉ thấy hơi tê nhẹ, giảm bớt đau đớn và biến chứng sau nhổ răng. Ngoài ra, còn không gây tổn thương đến mô mềm. Thời gian nhổ nhanh chóng, không cần há miệng quá lâu.', 
 2000000.00, 
 100, 
 getdate(), 
 getdate(), 
 'https://bedental.vn/wp-content/uploads/2021/12/nho-rang-khon-piezotome.jpg', 
 NULL),
('Nhổ răng 8 dưới - thường', 
 'Với những trường hợp răng khôn bị mọc lệch, chúng ta nên nhổ nó đi. Nhổ răng khôn với phương pháp truyền thống sẽ tác động nhiều đến phần nướu, và răng có thể bị đau, sưng tấy. Do đó, bác sĩ khuyên nên dùng công nghệ máy Piezotome giúp cho quá trình này nhẹ nhàng hơn.', 
 2500000.00, 
 120, 
 getdate(), 
 getdate(), 
 'https://bedental.vn/wp-content/uploads/2021/12/nho-rang-khon-piezotome.jpg', 
 NULL),
('Làm trắng răng tại nhà', 
 'Trị giá 200.000 đồng/bộ cho 4 khay, có thể sử dụng 4 lần để làm trắng tại nhà. Chỉ cần 30 phút mỗi ngày, sau 1 tuần sẽ thấy hiệu quả rõ rệt. Làm trắng tại nhà giúp tiết kiệm thời gian, tiền bạc và dễ dàng sử dụng.', 
 4000000.00, 
 60, 
 getdate(), 
 getdate(), 
 'https://th.bing.com/th/id/R.80bcb3dcd29810c55de90c47939fcf39?rik=e85HcXU69ZPtjA&pid=ImgRaw&r=0', 
 NULL),
('Làm trắng răng tại nha khoa', 
 'Giá: 1.000.000 đồng/bộ (bao gồm 2 lần thực hiện tại phòng khám) là phương pháp làm trắng tại nha khoa. Có hiệu quả ngay sau 1 lần thực hiện và cho hiệu quả từ 1 đến 2 năm. Chỉ cần thực hiện 2 lần, bạn sẽ sở hữu nụ cười trắng sáng tự nhiên.', 
 2000000.00, 
 70, 
 getdate(), 
 getdate(), 
 'https://nhakhoathuyduc.com.vn/wp-content/uploads/2023/03/lm-trang-rang.jpg', 
 NULL);