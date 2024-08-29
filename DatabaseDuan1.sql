
USE master
GO

IF EXISTS (
	SELECT [name]
		FROM sys.databases
		WHERE [name] = N'DB_NHOM_6_DUAN_1'
)
DROP DATABASE DB_NHOM_6_DUAN_1
GO
CREATE DATABASE DB_NHOM_6_DUAN_1
GO

-- +++++++++++++++++++++++++++++++ CREATE TABLES +++++++++++++++++++++++++++++++ --
USE DB_NHOM_6_DUAN_1
GO

CREATE TABLE MauSac (
	ID_MauSac INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_MauSac NVARCHAR(256) NOT NULL
)
--drop table MauSac
insert into MauSac
values (N'Đen'),
		(N'Xám'),
		(N'Ghi'),
		(N'Nâu'),
		(N'Xanh dương')
SELECT * FROM MauSac

CREATE TABLE DanhMuc (
	ID_DanhMuc INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_DanhMuc NVARCHAR(256) NOT NULL
)
insert into DanhMuc
values (N'Bộ sưu tập mùa hè'),
		(N'Bộ sưu tập mùa đông'),
		(N'Mẫu hot 2023'),
		(N'Bán chạy nhất'),
		(N'Hàng mới về')

		
SELECT * FROM DanhMuc

CREATE TABLE KichThuoc (
	ID_KichThuoc INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_KichThuoc INT NOT NULL,

)
insert into KichThuoc
values (37),
		(38),
		(39),
		(40),
		(41)

		SELECT * FROM KichThuoc

CREATE TABLE XuatXu (
	ID_XuatXu INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_XuatXu NVARCHAR(256) NOT NULL
)
insert into XuatXu
values (N'Việt Nam'),
		(N'Đức'),
		(N'Mỹ'),
		(N'Hàn Quốc'),
		(N'Nga') 

		SELECT * FROM XuatXu

CREATE TABLE ChatLieu (
	ID_ChatLieu INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_ChatLieu NVARCHAR(256) NOT NULL
) 

insert into ChatLieu
values (N'Da bò nhập khẩu'),
		(N'Da PU'),
		(N'Cao su'),
		(N'Tổng hợp'),
		(N'Lưới') 

SELECT * FROM ChatLieu

CREATE TABLE KieuDe (
	ID_KieuDe INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_KieuDe NVARCHAR(256) NOT NULL
)
insert into KieuDe
values (N'Cork Nitrile'),
		(N'Rubber'),
		(N'Crepe'),
		(N'Wedge'),
		(N'Leather') 

SELECT * FROM KieuDe

CREATE TABLE LopLot (
	ID_LopLot INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_LopLot NVARCHAR(256) NOT NULL
)
insert into LopLot
values (N'Adapt Run Max'),
		(N'Da'),
		(N'Superfeet Carbon'),
		(N' Powerstep Pinnacle'),
		(N'Leather') 

select * FROM LopLot

CREATE TABLE KieuMui (
	ID_KieuMui INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_KieuMui NVARCHAR(256) NOT NULL
)
insert into KieuMui
values (N'Mũi nhọn'),
		(N'Mũi vuông'),
		(N'Mũi tròn'),
		(N'Mũi thon'),
		(N'Mũi dục') 

		SELECT* FROM KieuMui

CREATE TABLE HinhAnh (
	ID_HinhAnh INT IDENTITY PRIMARY KEY NOT NULL,
	IMG NVARCHAR(256) NOT NULL
)

insert into HinhAnh
values (N'giay1.jpg'),
		(N'giay2.jpg'),
		(N'giay3.jpg'),
		(N'giay4.jpg'),
		(N'giay5.jpg') 

		SELECT * FROM HinhAnh

CREATE TABLE Giay (
	ID_Giay INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_Giay NVARCHAR(256) NOT NULL,
	ID_XuatXu INT FOREIGN KEY REFERENCES XuatXu(ID_XuatXu),
	ID_ChatLieu INT FOREIGN KEY REFERENCES ChatLieu(ID_ChatLieu),

	ID_KieuDe INT FOREIGN KEY REFERENCES KieuDe(ID_KieuDe),
	ID_LopLot INT FOREIGN KEY REFERENCES LopLot(ID_LopLot),
	ID_KieuMui INT FOREIGN KEY REFERENCES KieuMui(ID_KieuMui),

	ID_DanhMuc INT FOREIGN KEY REFERENCES DanhMuc(ID_DanhMuc),

	--0 còn hàng, 1 hết hàng
)
INSERT INTO Giay ( Ten_Giay, ID_XuatXu, ID_ChatLieu, ID_KieuDe, ID_LopLot, ID_KieuMui, ID_DanhMuc )
VALUES
    (N'Giày Sneaker A', 1, 2, 3, 4, 1, 1),
    (N'Giày Boots B', 2, 3, 4, 5, 2, 2),
    (N'Giày Sandal C', 3, 4, 5, 1, 3, 3),
    (N'Giày Loafer D', 4, 5, 1, 2, 4, 4),
    (N'Giày Slip-on E', 5, 1, 2, 3, 5, 5);

	SELECT * FROM GIAY

CREATE TABLE Giay_ChiTiet (
	ID_GiayChiTiet INT IDENTITY PRIMARY KEY NOT NULL,
	Ten_GiayChiTiet NVARCHAR(256) NOT NULL,
	ID_Giay INT FOREIGN KEY REFERENCES Giay(ID_Giay),
	SoLuong INT NOT NULL,
	GiaBan FLOAT NOT NULL,
	ID_MauSac INT FOREIGN KEY REFERENCES MauSac(ID_MauSac),
	ID_KichThuoc INT FOREIGN KEY REFERENCES KichThuoc(ID_KichThuoc),
	ID_HinhAnh INT FOREIGN KEY REFERENCES HinhAnh(ID_HinhAnh),
	TrangThai_Giay nvarchar(50)
	)


	GO
	CREATE TRIGGER trg_InsteadOfInsert_Giay_ChiTiet
ON Giay_ChiTiet
INSTEAD OF INSERT
AS
BEGIN
    INSERT INTO Giay_ChiTiet (Ten_GiayChiTiet, ID_Giay, SoLuong, GiaBan, ID_MauSac, ID_KichThuoc, ID_HinhAnh, TrangThai_Giay)
    SELECT 
        Giay.Ten_Giay + '_' + Ten_MauSac + '_' + CONVERT(NVARCHAR(5), Ten_KichThuoc),
        INSERTED.ID_Giay,
        INSERTED.SoLuong,
        INSERTED.GiaBan,
        INSERTED.ID_MauSac,
        INSERTED.ID_KichThuoc,
        INSERTED.ID_HinhAnh,
        INSERTED.TrangThai_Giay
    FROM INSERTED
    JOIN Giay ON INSERTED.ID_Giay = Giay.ID_Giay 
	JOIN MauSac ON INSERTED.ID_MauSac = MauSac.ID_MauSac
	JOIN KichThuoc ON INSERTED.ID_KichThuoc = KichThuoc.ID_KichThuoc
    WHERE NOT EXISTS (
        SELECT 1
        FROM Giay_ChiTiet
        WHERE Giay_ChiTiet.ID_Giay = INSERTED.ID_Giay
          AND Giay_ChiTiet.ID_MauSac = INSERTED.ID_MauSac
          AND Giay_ChiTiet.ID_KichThuoc = INSERTED.ID_KichThuoc
    );
END;


go
INSERT INTO Giay_ChiTiet ( ID_Giay, Ten_GiayChiTiet, SoLuong, GiaBan, ID_MauSac, ID_KichThuoc, ID_HinhAnh, TrangThai_Giay )
VALUES
    (1, N'Chi Tiết 1', 100, 300000, 1, 1, 1, N'Đang Hoạt Động'),
    (1, N'Chi Tiết 2', 100, 200000, 2, 2, 2,N'Đang Hoạt Động' ),
    (2, N'Chi Tiết 3', 100, 300000, 3, 3, 3,N'Ngừng Hoạt Động' ),
    (3, N'Chi Tiết 4', 100, 400000, 4, 4, 4,N'Đang Hoạt Động' ),
    (3, N'Chi Tiết 5', 100, 500000, 5, 5, 5,N'Đang Hoạt Động' ),
    (4, N'Chi Tiết 6', 100, 300000, 1, 1, 1,N'Ngừng Hoạt Động' ),
    (5, N'Chi Tiết 7', 100, 300000, 2, 2, 2, N'Đang Hoạt Động'),
    (5, N'Chi Tiết 8', 100, 200000, 3, 3, 3,N'Đang Hoạt Động' );
	select * from Giay_ChiTiet

CREATE TABLE KHACHHANG(
ID_KhachHang  INT identity ,
TenKH NVARCHAR(30),
GioiTinh BIT ,
NgaySinh date,
SoDT nvarchar(13) ,
Email NVARCHAR(30),
DiaChi NVARCHAR(50),
NgayTao DATE,
NgayCapNhat DATE,
TrangThai bit,

PRIMARY KEY (ID_KhachHang)

)
GO



-------------------------------------------------------------------------------------
insert into KHACHHANG(TenKH, GioiTinh, NgaySinh, SoDT, Email, DiaChi, NgayTao, NgayCapNhat, TrangThai)
values  (N'Nguyễn Thu Hằng',0,'2004-07-30','0344691228','HangThu307@gmail.com',N'Hải Dương','2023-07-22', '',0),
		(N'Phạm Thái Sơn',1,'2004-04-30','0975921234','ThaiSon2004@gmail.com',N'Điện Biên','2023-05-22','',1),
		(N'Lê Thu Trang',0,'2000-11-12','0975921989','TrangLe2000@gmail.com',N'Hà Nội ','2023-07-09','2023-07-13',0),
		(N'Nguyễn Ngọc Phương',0,'2001-05-10','0972222905','NgocPhuong2001@gmail.com',N'Hưng Yên ','2303-07-24','',1),
		(N'Trần Việt Cường',0,'2003-02-24','0975944445','CuongTran242@gmail.com',N'Thái Bình','2023-06-23','',0)

		Select * from KHACHHANG
	
CREATE TABLE NhanVien (
	ID_NhanVien INT identity(1,1) PRIMARY KEY,
	TenNhanVien nvarchar(50),
	NgaySinh nvarchar(50),
	DiaChi nvarchar(100),
	SDT nvarchar(10),
	Email nvarchar(100),
	GioiTinh bit,
	HinhAnh nvarchar(10),
)
INSERT INTO NhanVien (TenNhanVien, NgaySinh, DiaChi, SDT, Email, GioiTinh, HinhAnh)
VALUES 
    (N'Tiến Hải', '1990-01-01', '123 Main Street', '0123456789', 'john.doe@email.com', 0, 'image1.jpg'),
    ('Thu Hằng', '1995-05-15', '456 Oak Avenue', '0987654321', 'jane.doe@email.com', 1, 'image2.jpg'),
    ('Khánh Đăng', '1985-08-20', '789 Pine Road', '0555666777', 'bob.smith@email.com', 1, 'image3.jpg'),
    ('Mạnh Dũng', '1992-11-10', '101 Cedar Lane', '0333222111', 'alice.johnson@email.com', 0, 'image4.jpg');


Create table chucvu(
ID_ChucVu int identity(1,1) primary key,
TenChucVu nvarchar(255),
)

insert into chucvu values (N'Nhân viên'),(N'Quản lý');

CREATE TABLE TaiKhoanNV(
	ID_TaiKhoan INT identity(1,1) PRIMARY KEY,
	TenTK NVARCHAR(256) NOT NULL,
	MatKhau NVARCHAR(100) NOT NULL,
	ID_ChucVu int,
	ID_NhanVien INT FOREIGN KEY REFERENCES NhanVien(ID_NhanVien),
	TrangThai NVARCHAR(256) NOT NULL,
	foreign key(ID_ChucVu) references chucvu(ID_ChucVu)
)



INSERT INTO TaiKhoanNV (TenTK, MatKhau, ID_ChucVu, ID_NhanVien, TrangThai)
VALUES 
    ('nhanvien1', '123', 1, 1, N'Đang hoạt động'),
    ('nhanvien2', '123', 1, 2, N'Đang hoạt động'),
    ('admin1', '123', 2, 3, N'Đang hoạt động'),
    ('admin2', '123', 2, 4, N'Ngừng hoạt động');

	
								
	select * from NhanVien
	select * from chucvu
	select * from TaiKhoanNV


CREATE TABLE LOAIGIAMGIA(
ID INT IDENTITY(1,1) PRIMARY KEY,
TENLOAIGIAMGIA NVARCHAR(50)
)
INSERT INTO LOAIGIAMGIA (TENLOAIGIAMGIA)
VALUES
(N'Giảm giá theo tiền'),
(N'Giảm giá theo %');
SELECT * FROM LOAIGIAMGIA

CREATE TABLE PHIEUGIAMGIA (
  ID_PGG INT IDENTITY(1,1) PRIMARY KEY,
  MAGIAMGIA INT,
  TENGIAMGIA nvarchar(255),
  GIATRIGIAM decimal(13),
  DONHANGTOITHIEU DECIMAL(13),
  NGAYTAO DATE,
  THOIGIANBATDAU DATE,
  THOIGIANKETTHUC DATE,
  ID_NHANVIEN INT,
  IDLOAIGIAMGIA INT,
  TRANGTHAI BIT,
  FOREIGN KEY(ID_NHANVIEN) REFERENCES NHANVIEN(ID_NHANVIEN),
  FOREIGN KEY(IDLOAIGIAMGIA) REFERENCES LOAIGIAMGIA(ID),
)
INSERT INTO PHIEUGIAMGIA (MAGIAMGIA, TENGIAMGIA, GIATRIGIAM, DONHANGTOITHIEU, NGAYTAO, THOIGIANBATDAU, THOIGIANKETTHUC, ID_NHANVIEN, IDLOAIGIAMGIA,TRANGTHAI)
VALUES
(1234568, N'Giảm giá 10000', 10000,100000, '2023-11-01', '2023-11-05', '2023-12-23', 3, 1,0),
(1234568, N'Giảm giá 10000', 10000,100000, '2023-11-01', '2023-11-05', '2023-12-15', null, 1,0),
  (123456, N'Giảm giá 10000', 10000,100000, '2023-11-01', '2023-11-05', '2023-11-15', 1, 1,0),
  (789012, N'Giảm giá 20%', 20,10000000, '2023-11-02', '2023-11-06', '2023-11-16', 2, 2,0);


  select * from PHIEUGIAMGIA

CREATE TABLE PhuongThucThanhToan(
    ID_PTTT INT PRIMARY KEY NOT NULL,
    TenPTTT NVARCHAR(256) NOT NULL
)
INSERT INTO PhuongThucThanhToan VALUES 
(1,N'Tiền Mặt'),(2,'ATM'),(3,N'Tất cả');
SELECT * FROM PhuongThucThanhToan
CREATE TABLE DotGiamGia
(
	ID_DotGiamGia INT IDENTITY PRIMARY KEY NOT NULL,
	TenDotGiamGia NVARCHAR(256) NOT NULL,
	HinhAnh VARCHAR(256) NULL,
	NgayBatDau DATE NOT NULL,
	NgayKetThuc DATE NOT NULL ,
	Giam FLOAT NOT NULL CHECK(Giam>0),
	TrangThai BIT,
	MoTa  VARCHAR(max),
	ID_PTTT INT,
	FOREIGN KEY (ID_PTTT) REFERENCES PhuongThucThanhToan(ID_PTTT)

)
INSERT INTO DotGiamGia
VALUES
	('CTKM xxxx xxxx', '', '2023/10/10', '2023/12/22', 38, 1, N'Đây là đợt giảm giá nhân dịp sinh nhật cửa hàng', 3),
	('CTKM yyyy yyyy', '', '2023/9/10', '2023/12/22', 27, 1, N'Thích thì giảm , không thích thì giảm', 3),
	('CTKM zzzz zzzz', '', '2023/8/10', '2023/8/15', 16, 1, N'Tưng bừng tri ân , không khuyến mãi', 3),
	('CTKM oooo oooo', '', '2023/7/10', '2023/7/15', 15, 1, N'Không có mô tả', 3),
	('CTKM eeee eeee', '', '2023/6/10', '2023/6/15', 24, 1, N'Không có mô tả', 3),
	('CTKM wwww wwww', '', '2023/5/10', '2023/5/15', 33, 0, N'Không có mô tả', 3),
	('CTKM ssss ssss', '', '2023/4/10', '2023/4/15', 22, 1, N'Đợt giảm giá nhân dịp thương hiệu ra đời', 3);
CREATE TABLE ChiTietDotGiamGia
(
	ID_DotGiamGia INT NOT NULL,
	ID_GiayChiTiet INT NOT NULL,
	PRIMARY KEY (ID_DotGiamGia,ID_GiayChiTiet),
	FOREIGN KEY (ID_GiayChiTiet) REFERENCES Giay_ChiTiet(ID_GiayChiTiet),
	FOREIGN KEY (ID_DotGiamGia) REFERENCES DotGiamGia(ID_DotGiamGia)
)
-- SELECT Giay.ID_Giay,Giay_ChiTiet.ID_GiayChiTiet,Ten_Giay,Ten_MauSac,Ten_KichThuoc,SoLuong,GiaBan,Ten_ChatLieu,TrangThai_Giay FROM Giay 
-- JOIN Giay_ChiTiet ON Giay.ID_Giay = Giay_ChiTiet.ID_Giay 
-- JOIN MauSac ON Giay_ChiTiet.ID_MauSac = MauSac.ID_MauSac
-- JOIN KichThuoc ON Giay_ChiTiet.ID_KichThuoc = KichThuoc.ID_KichThuoc
-- JOIN ChatLieu ON Giay.ID_ChatLieu = ChatLieu.ID_ChatLieu

--------------------------Hoá đơn---------------------------

CREATE TABLE HoaDon(
	ID_HoaDon INT IDENTITY PRIMARY KEY NOT NULL,
	NgayLap DATE ,
	ID_NhanVien INT FOREIGN KEY REFERENCES NhanVien(ID_NhanVien),
	ID_KhachHang INT FOREIGN KEY REFERENCES KhachHang(ID_KhachHang),
	ID_PGG INT FOREIGN KEY REFERENCES PhieuGiamGia(ID_PGG),
	ID_DotGiamGia INT FOREIGN KEY REFERENCES DotGiamGia(ID_DotGiamGia),
	ID_PTTT INT FOREIGN KEY REFERENCES PhuongThucThanhToan(ID_PTTT),
	TrangThai NVARCHAR(255),
	TongTien MONEY, 
	SoTienGiamGiaVC MONEY,
	SoTienGiamGiaCT MONEY,
	ThanhToan MONEY, 
	TienKhachDua MONEY, 
	TienTralaiKhach MONEY,
	GhiChu NVARCHAR(255)
)
CREATE TABLE HoaDonChiTiet(
	ID_HoaDonChiTiet INT IDENTITY PRIMARY KEY NOT NULL,
	ID_HoaDon INT FOREIGN KEY REFERENCES HoaDon(ID_HoaDon),
	ID_GiayChiTiet INT FOREIGN KEY REFERENCES Giay_ChiTiet(ID_GiayChiTiet),
	SoLuong INT NOT NULL
)

----DROP TABLE PhieuDoiTra
--CREATE TABLE PhieuDoiTra
--(
--	ID_Phieu INT IDENTITY PRIMARY KEY NOT NULL ,
--	ID_KhachHang INT ,
--	ID_NhanVien INT ,
--	ID_HoaDon INT,
--	Ngay_Doi DATE
--	FOREIGN KEY (ID_HoaDon) REFERENCES HOADON(ID_HoaDon),
--	FOREIGN KEY (ID_KhachHang) REFERENCES KhachHang (ID_KhachHang),
--	FOREIGN KEY (ID_NhanVien) REFERENCES NhanVien (ID_NhanVien)
--)
--GO

--INSERT INTO PhieuDoiTra (ID_KhachHang, ID_NhanVien, ID_HoaDon, Ngay_Doi)
--VALUES
--	(1, 1, 5, '2023-11-14'),
--	(2, 2, 4, '2023-11-15'),
--	(3, 3, 3, '2023-11-16'),
--	(4, 4, 2, '2023-11-17'),
--	(5, 5, 1, '2023-11-18');

--select *
--from phieudoitra

----drop TABLE TrangThaiPhieu
--CREATE TABLE TrangThaiPhieu
--(
--	ID_TrangThai INT IDENTITY(1,1) PRIMARY KEY,
--	TrangThai NVARCHAR(50),
--)
--GO

--INSERT INTO TrangThaiPhieu ( TrangThai)
--VALUES
--	( N'Đang sử lý'),
--	( N'Hoàn thành'),
--	( N'Huỷ')
	

--SELECT * FROM TrangThaiPhieu

--DROP TABLE LyDoDoiTra
--CREATE TABLE LyDoDoiTra
--(
--	ID_LyDo INT IDENTITY(1,1) PRIMARY KEY,
--	LyDo NVARCHAR(255)
--)
--GO

--INSERT INTO LyDoDoiTra ( LyDo)
--VALUES
--	( N'Sản phẩm bị lỗi'),
--	( N'Sản phẩm không đúng mô tả'),
--	( N'Sản phẩm bị hư hỏng trong quá trình vận chuyển'),
--	( N'Khách hàng đổi ý'),
--	( N'Khách hàng không hài lòng với sản phẩm');
--SELECT * FROM LyDoDoiTra

--drop table khohangdoitra
--CREATE TABLE KhoHangDoiTra
--(
--	ID_KhoHang INT IDENTITY(1,1) PRIMARY KEY,
--	Ma_Kho NVARCHAR(10),
--	DiaChi NVARCHAR(255),
--	TenKho NVARCHAR(255),
--	NgayTao NVARCHAR(50),
--	LienHe VARCHAR (20),
--	SLHangTrongkho INT,
--	GhiChu NVARCHAR(MAX),

--)
--GO

--INSERT INTO KhoHangDoiTra
--	( Ma_Kho, DiaChi, TenKho, NgayTao, LienHe, GhiChu, SLHangTrongkho)
--VALUES
--	( N'001', N'Số 100, Đường Nguyễn Trãi, Hà Nội', N'Kho hàng chính', '10-10-2023', '0123456789', N'Kho hàng chính của công ty', 1000),
--	( N'002', N'Số 50, Đường Điện Biên Phủ, Hà Nội', N'Kho hàng phụ', '10-10-2023', '0234567891', N'Kho hàng phụ của công ty', 2000),
--	( N'003', N'Số 100, Đường Lê Văn Lương, Hà Nội', N'Kho hàng miền Bắc', '10-10-2023', '0345678912', N'Kho hàng miền Bắc của công ty', 3000),
--	( N'004', N'Số 100, Đường Nguyễn Chí Thanh, Đà Nẵng', N'Kho hàng miền Trung', '10-10-2023', '0456789123', N'Kho hàng miền Trung của công ty', 1500),
--	( N'005', N'Số 100, Đường Cách Mạng Tháng Tám, Thành phố Hồ Chí Minh', N'Kho hàng miền Nam', '10-10-2023', '0567891234', N'Kho hàng miền Nam của công ty', 2400);

--SELECT *
--FROM KhoHangDoiTra
--DROP TABLE ChiTietPhieu_ĐoiTra
--GO
--CREATE TABLE ChiTietPhieu_ĐoiTra
--(
--	ID_Phieu_ChiTiet INT IDENTITY PRIMARY KEY NOT NULL,
--	Ma_PCT NVARCHAR(15),
--	ID_Phieu INT,
--	ID_KhoHang INT,
--	ID_TrangThai INT,
--	ID_LyDo INT,
--	SoLuongDoi_Tra INT,
--	GhiChu text,

--	FOREIGN KEY (ID_Phieu) REFERENCES PhieuDoiTra (ID_Phieu),
--	FOREIGN KEY (ID_TrangThai) REFERENCES TrangThaiPhieu (ID_TrangThai),
--	FOREIGN KEY (ID_LyDo) REFERENCES LyDoDoiTra (ID_LyDo),
--	FOREIGN KEY (ID_KhoHang) REFERENCES KhoHangDoiTra (ID_KhoHang)
--);

--INSERT INTO ChiTietPhieu_ĐoiTra
--	( ID_Phieu, ID_KhoHang, ID_TrangThai, ID_LyDo, SoLuongDoi_Tra, GhiChu)
--VALUES
--	( 1, 1, 2, 1, 5, N'ok'),
--	( 2, 2, 1, 5, 4, N'not ok'),
--	( 3, 3, 3, 3, 3, N'no ok'),
--	( 4, 4, 2, 4, 2, N'super ok'),
--	( 5, 5, 1, 2, 1, N'yes');
--GO

----CREATE OR ALTER VIEW Show_SP
----AS
----	(
----	SELECT ct.ID_GiayChiTiet, g.ID_Giay, Ten_Giay, GiaBan, IMG
----	FROM Giay g
----		JOIN Giay_ChiTiet ct on g.ID_Giay = ct.ID_Giay
----		JOIN HinhAnh img on ct.ID_HinhAnh = img.ID_HinhAnh
----    )
----GO

----SELECT * FROM Show_SP
----GO

----CREATE OR ALTER VIEW Show_HD
----AS
----	(
----	SELECT hd.ID, hd.ID_KhachHang, TenKH, hd.ID_NhanVien, TenNhanVien, hd.ID_GiayChiTiet, Ten_Giay, SoLuongSanPham, GiaBan, TongTien, hd.NgayTao
----	FROM Giay g
----		JOIN Giay_ChiTiet ct on g.ID_Giay = ct.ID_Giay
----		JOIN HOADON hd on ct.ID_GiayChiTiet = hd.ID_GiayChiTiet
----		JOIN KHACHHANG kh on hd.ID_KhachHang = kh.ID_KhachHang
----		JOIN NHANVIEN nv on hd.ID_NhanVien = nv.ID_NhanVien
------     order BY ID
----)
----GO

----CREATE OR ALTER view Show_PD
----AS
----	(

----	SELECT pd.ID_Phieu, pd.ID_KhachHang, kh.TenKH, pd.ID_NhanVien, nv.TenNhanVien, g.Ten_Giay, pd.ID_HoaDon, pd.Ngay_Doi
----	FROM PhieuDoiTra pd
----		JOIN KHACHHANG kh on pd.ID_KhachHang = kh.ID_KhachHang
----		JOIN NhanVien nv on nv.ID_NhanVien = pd.ID_NhanVien
----		JOIN HOADON hd on hd.ID = pd.ID_HoaDon
----		JOIN Giay_ChiTiet gct on gct.ID_GiayChiTiet = hd.ID_GiayChiTiet
----		JOIN Giay g on g.ID_Giay = gct.ID_Giay
----)
----GO

----CREATE OR ALTER VIEW Show_CTP
----AS
----	(
----	SELECT ctp.ID_Phieu, ctp.Ma_PCT, g.ID_Giay, g.Ten_Giay, ctp.SoLuongDoi_Tra, hd.SoLuongSanPham, gct.GiaBan, kho.ID_KhoHang, Ld.ID_LyDo, tt.ID_TrangThai, ctp.GhiChu
----	FROM ChiTietPhieu_ĐoiTra ctp
----		JOIN PhieuDoiTra pdt ON ctp.ID_Phieu = pdt.ID_Phieu
----		JOIN TrangThaiPhieu tt ON tt.ID_TrangThai = ctp.ID_TrangThai
----		JOIN LyDoDoiTra Ld on Ld.ID_LyDo = ctp.ID_LyDo
----		JOIN KhoHangDoiTra kho ON kho.ID_KhoHang = ctp.ID_KhoHang
----		JOIN HOADON hd ON pdt.ID_HoaDon = hd.ID
----		JOIN Giay_ChiTiet gct ON gct.ID_GiayChiTiet = hd.ID_GiayChiTiet
----		JOIN Giay g ON g.ID_Giay = gct.ID_Giay
----)
----GO

----UPDATE ChiTietPhieu_ĐoiTra
----SET Ma_PCT = CONCAT('PCTD','000',ID_Phieu)

----UPDATE HOADON
----SET NgayTao = '2023/10/02'

----GO

select * from NhanVien