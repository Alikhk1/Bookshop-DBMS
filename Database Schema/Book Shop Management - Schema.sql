


CREATE TABLE "Supplier"(
    "SPID" VARCHAR(255) NOT NULL,
    "FirstName" VARCHAR(255) NOT NULL,
    "LastName" VARCHAR(255) NOT NULL,
    "ContactNumber" VARCHAR(255) NOT NULL,
    "Address" VARCHAR(255) NOT NULL,
    "Email" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "Supplier" ADD CONSTRAINT "supplier_spid_primary" PRIMARY KEY("SPID");
CREATE TABLE "Supplier_Order"(
    "S_ONO" VARCHAR(255) NOT NULL,
    "SPID" VARCHAR(255) NOT NULL,
    "Date" DATE NOT NULL
);
ALTER TABLE
    "Supplier_Order" ADD CONSTRAINT "supplier_order_s_ono_primary" PRIMARY KEY("S_ONO");
CREATE TABLE "Stationery"(
    "SID" VARCHAR(255) NOT NULL,
    "StationaryName" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL,
    "Price" DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    "Stationery" ADD CONSTRAINT "stationery_sid_primary" PRIMARY KEY("SID");
CREATE TABLE "Customer_Bill"(
    "C_BNO" VARCHAR(255) NOT NULL,
    "CID" VARCHAR(255) NOT NULL,
    "Date" DATE NOT NULL,
    "Total_Price" DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    "Customer_Bill" ADD CONSTRAINT "customer_bill_c_bno_primary" PRIMARY KEY("C_BNO");
CREATE TABLE "Stationery_Customer_Bill"(
    "SID" VARCHAR(255) NOT NULL,
    "C_BNO" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL,
    "Price" DECIMAL(8, 2) NOT NULL
);
CREATE TABLE "Books"(
    "BID" VARCHAR(255) NOT NULL,
    "Author" VARCHAR(255) NOT NULL,
    "Title" VARCHAR(255) NOT NULL,
    "Publisher" VARCHAR(255) NOT NULL,
    "Year" DATE NOT NULL,
    "Pages" INT NOT NULL,
    "Language" VARCHAR(255) NOT NULL,
    "Topic" VARCHAR(255) NOT NULL,
    "ISBN" VARCHAR(255) NOT NULL,
    "OldVersion" VARCHAR(255) NULL,
    "DateAdded" DATE NOT NULL,
    "DeweyDecimalClassification" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL,
    "Price" DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    "Books" ADD CONSTRAINT "books_bid_primary" PRIMARY KEY("BID");
CREATE UNIQUE INDEX "books_isbn_unique" ON
    "Books"("ISBN");
CREATE UNIQUE INDEX "books_deweydecimalclassification_unique" ON
    "Books"("DeweyDecimalClassification");
CREATE TABLE "Supplier_Bill"(
    "S_BNO" VARCHAR(255) NOT NULL,
    "SPID" VARCHAR(255) NOT NULL,
    "Date" DATE NOT NULL,
    "Total_Price" DECIMAL(8, 2) NOT NULL
);
ALTER TABLE
    "Supplier_Bill" ADD CONSTRAINT "supplier_bill_s_bno_primary" PRIMARY KEY("S_BNO");
CREATE TABLE "Books_Customer_Bill"(
    "BID" VARCHAR(255) NOT NULL,
    "C_BNO" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL,
    "Price" DECIMAL(8, 2) NOT NULL
);
CREATE TABLE "Employee"(
    "EID" VARCHAR(255) NOT NULL,
    "FirstName" VARCHAR(255) NOT NULL,
    "LastName" VARCHAR(255) NOT NULL,
    "DateJoined" DATE NOT NULL,
    "Designation" VARCHAR(255) NOT NULL,
    "Salary" DECIMAL(8, 2) NOT NULL,
    "DOB" DATE NOT NULL,
    "Address" VARCHAR(255) NOT NULL,
    "Email" VARCHAR(255) NULL
);
ALTER TABLE
    "Employee" ADD CONSTRAINT "employee_eid_primary" PRIMARY KEY("EID");
CREATE TABLE "Books_Supplier_Order"(
    "BID" VARCHAR(255) NOT NULL,
    "S_ONO" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL
);
CREATE TABLE "Books_Supplier_Bill"(
    "BID" VARCHAR(255) NOT NULL,
    "S_BNO" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL,
    "Total_Price" DECIMAL(8, 2) NOT NULL
);
CREATE TABLE "Stationery_Supplier_Bill"(
    "SID" VARCHAR(255) NOT NULL,
    "S_BNO" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL,
    "Price" DECIMAL(8, 2) NOT NULL
);
CREATE TABLE "Customer"(
    "CID" VARCHAR(255) NOT NULL,
    "FirstName" VARCHAR(255) NOT NULL,
    "LastName" VARCHAR(255) NOT NULL,
    "ContactNumber" VARCHAR(255) NULL,
    "Address" VARCHAR(255) NULL,
    "Email" VARCHAR(255) NULL
);
ALTER TABLE
    "Customer" ADD CONSTRAINT "customer_cid_primary" PRIMARY KEY("CID");
CREATE TABLE "Stationery_Supplier_Order"(
    "SID" VARCHAR(255) NOT NULL,
    "S_ONO" VARCHAR(255) NOT NULL,
    "Quantity" INT NOT NULL
);
ALTER TABLE
    "Books_Supplier_Bill" ADD CONSTRAINT "books_supplier_bill_s_bno_foreign" FOREIGN KEY("S_BNO") REFERENCES "Supplier_Bill"("S_BNO");
ALTER TABLE
    "Stationery_Supplier_Order" ADD CONSTRAINT "stationery_supplier_order_sid_foreign" FOREIGN KEY("SID") REFERENCES "Stationery"("SID");
ALTER TABLE
    "Stationery_Supplier_Order" ADD CONSTRAINT "stationery_supplier_order_s_ono_foreign" FOREIGN KEY("S_ONO") REFERENCES "Supplier_Order"("S_ONO");
ALTER TABLE
    "Supplier_Order" ADD CONSTRAINT "supplier_order_spid_foreign" FOREIGN KEY("SPID") REFERENCES "Supplier"("SPID");
ALTER TABLE
    "Stationery_Supplier_Bill" ADD CONSTRAINT "stationery_supplier_bill_s_bno_foreign" FOREIGN KEY("S_BNO") REFERENCES "Supplier_Bill"("S_BNO");
ALTER TABLE
    "Supplier_Bill" ADD CONSTRAINT "supplier_bill_spid_foreign" FOREIGN KEY("SPID") REFERENCES "Supplier"("SPID");
ALTER TABLE
    "Stationery_Supplier_Bill" ADD CONSTRAINT "stationery_supplier_bill_sid_foreign" FOREIGN KEY("SID") REFERENCES "Stationery"("SID");
ALTER TABLE
    "Books_Customer_Bill" ADD CONSTRAINT "books_customer_bill_c_bno_foreign" FOREIGN KEY("C_BNO") REFERENCES "Customer_Bill"("C_BNO");
ALTER TABLE
    "Books_Customer_Bill" ADD CONSTRAINT "books_customer_bill_bid_foreign" FOREIGN KEY("BID") REFERENCES "Books"("BID");
ALTER TABLE
    "Customer_Bill" ADD CONSTRAINT "customer_bill_cid_foreign" FOREIGN KEY("CID") REFERENCES "Customer"("CID");
ALTER TABLE
    "Books_Supplier_Order" ADD CONSTRAINT "books_supplier_order_s_ono_foreign" FOREIGN KEY("S_ONO") REFERENCES "Supplier_Order"("S_ONO");
ALTER TABLE
    "Books_Supplier_Bill" ADD CONSTRAINT "books_supplier_bill_bid_foreign" FOREIGN KEY("BID") REFERENCES "Books"("BID");
ALTER TABLE
    "Books" ADD CONSTRAINT "books_oldversion_foreign" FOREIGN KEY("OldVersion") REFERENCES "Books"("BID");
ALTER TABLE
    "Stationery_Customer_Bill" ADD CONSTRAINT "stationery_customer_bill_c_bno_foreign" FOREIGN KEY("C_BNO") REFERENCES "Customer_Bill"("C_BNO");
ALTER TABLE
    "Books_Supplier_Order" ADD CONSTRAINT "books_supplier_order_bid_foreign" FOREIGN KEY("BID") REFERENCES "Books"("BID");
ALTER TABLE
    "Stationery_Customer_Bill" ADD CONSTRAINT "stationery_customer_bill_sid_foreign" FOREIGN KEY("SID") REFERENCES "Stationery"("SID");



	SELECT * FROM Books
	DELETE FROM BOOKs WHERE BID = 2


	INSERT INTO Customer_Bill (C_BNO)
	VALUES ('1');
	INSERT INTO Customer_Bill (C_BNO)
	VALUES ('2');
	INSERT INTO Customer_Bill (C_BNO)
	VALUES ('3');


	SELECT * FROM Customer_Bill




	