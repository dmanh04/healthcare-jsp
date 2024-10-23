-- Create a new database
CREATE DATABASE [healthcare-final];
GO

-- Use the new database
USE [healthcare-final];
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
    image nvarchar(MAX) NULL,
    icon varchar(MAX) NULL,
    CONSTRAINT PK_services PRIMARY KEY (service_id)
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
    language nvarchar(20) NULL,
    gender nvarchar(10) NULL,
    date_of_birth date NULL,
    is_active bit DEFAULT 1 NULL,
    created_at datetime DEFAULT getdate() NULL,
    updated_at datetime DEFAULT getdate() NULL,
    photos varchar(MAX) NULL,
    CONSTRAINT PK_users PRIMARY KEY (user_id),
    CONSTRAINT UQ_users UNIQUE (username),
    CONSTRAINT CK_users_gender CHECK (gender IN ('OTHER', 'FEMALE', 'MALE'))
);

CREATE TABLE inventory (
    inventory_id int IDENTITY(1,1) NOT NULL,
    medicine_id int NULL,
    stock_in int NULL,
    stock_out int NULL,
    created_at datetime DEFAULT getdate() NULL,
    updated_at datetime DEFAULT getdate() NULL,
    CONSTRAINT PK_inventory PRIMARY KEY (inventory_id),
    CONSTRAINT FK_inventory_medicine FOREIGN KEY (medicine_id) REFERENCES dbo.medicines(medicine_id)
);

CREATE TABLE time_slots (
    slot_id int IDENTITY(1,1) NOT NULL,
    doctor_id int NULL,
    appointment_date date NULL,
    start_time time NULL,
    end_time time NULL,
    status nvarchar(10) DEFAULT 'AVAILABLE' NULL,
    created_at datetime DEFAULT getdate() NULL,
    updated_at datetime DEFAULT getdate() NULL,
    CONSTRAINT PK_time_slots PRIMARY KEY (slot_id),
    CONSTRAINT FK_time_slots_doctor FOREIGN KEY (doctor_id) REFERENCES dbo.users(user_id),
    CONSTRAINT CK_time_slots_status CHECK (status IN ('BOOKED', 'AVAILABLE'))
);

CREATE TABLE user_roles (
    user_id int NOT NULL,
    role_id int NOT NULL,
    CONSTRAINT PK_user_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES dbo.roles(role_id),
    CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES dbo.users(user_id)
);

CREATE TABLE appointments (
    appointment_id int IDENTITY(1,1) NOT NULL,
    customer_id int NULL,
    doctor_id int NULL,
    service_id int NULL,
    status nvarchar(10) DEFAULT 'PENDING' NULL,
    notes nvarchar(MAX) NULL,
    created_at datetime DEFAULT getdate() NULL,
    updated_at datetime DEFAULT getdate() NULL,
    time_slot_id int NULL,
    CONSTRAINT PK_appointments PRIMARY KEY (appointment_id),
    CONSTRAINT FK_Appointments_TimeSlots FOREIGN KEY (time_slot_id) REFERENCES dbo.time_slots(slot_id),
    CONSTRAINT FK_appointments_customer FOREIGN KEY (customer_id) REFERENCES dbo.users(user_id),
    CONSTRAINT FK_appointments_doctor FOREIGN KEY (doctor_id) REFERENCES dbo.users(user_id),
    CONSTRAINT FK_appointments_service FOREIGN KEY (service_id) REFERENCES dbo.services(service_id),
    CONSTRAINT CK_appointments_status CHECK (status IN ('COMPLETED', 'CANCELLED', 'CONFIRMED', 'PENDING'))
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
    CONSTRAINT FK_medical_records_appointment FOREIGN KEY (appointment_id) REFERENCES dbo.appointments(appointment_id)
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