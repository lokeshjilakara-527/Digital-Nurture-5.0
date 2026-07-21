-- ============================================================================
-- Week 1 : PL/SQL Programming  --  Schema + sample data (banking domain)
-- Run this FIRST (SQL*Plus / SQL Developer / Oracle Live SQL).
-- ============================================================================
SET SERVEROUTPUT ON;

-- ---- Tables (from the hands-on schema) --------------------------------------
CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    DOB          DATE,
    Balance      NUMBER,
    LastModified DATE,
    IsVIP        VARCHAR2(5) DEFAULT 'FALSE'   -- added for Exercise 1, Scenario 2
);

CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    AccountType  VARCHAR2(20),
    Balance      NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID   NUMBER PRIMARY KEY,
    AccountID       NUMBER,
    TransactionDate DATE,
    Amount          NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    LoanAmount   NUMBER,
    InterestRate NUMBER,
    StartDate    DATE,
    EndDate      DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID  NUMBER PRIMARY KEY,
    Name        VARCHAR2(100),
    Position    VARCHAR2(50),
    Salary      NUMBER,
    Department  VARCHAR2(50),
    HireDate    DATE
);

-- ---- Sample data (enriched so the exercises produce visible output) ----------
-- Customer 3 is > 60 years old; Customer 4 has balance > 10000 (VIP).
INSERT INTO Customers VALUES (1, 'John Doe',    TO_DATE('1985-05-15','YYYY-MM-DD'),  1000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (2, 'Jane Smith',  TO_DATE('1990-07-20','YYYY-MM-DD'),  1500, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (3, 'Robert Gray', TO_DATE('1955-02-10','YYYY-MM-DD'),  8000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (4, 'Mary Vip',    TO_DATE('1978-11-30','YYYY-MM-DD'), 25000, SYSDATE, 'FALSE');

INSERT INTO Accounts VALUES (1, 1, 'Savings',  1000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking', 1500, SYSDATE);
INSERT INTO Accounts VALUES (3, 3, 'Savings',  8000, SYSDATE);
INSERT INTO Accounts VALUES (4, 4, 'Savings', 25000, SYSDATE);

INSERT INTO Transactions VALUES (1, 1, SYSDATE, 200, 'Deposit');
INSERT INTO Transactions VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

-- Loan for customer 3 (senior) and a loan due within 30 days (customer 1).
INSERT INTO Loans VALUES (1, 3, 5000,  8, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans VALUES (2, 1, 3000, 10, SYSDATE, SYSDATE + 20);

INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager',   70000, 'HR', TO_DATE('2015-06-15','YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown',     'Developer', 60000, 'IT', TO_DATE('2017-03-20','YYYY-MM-DD'));
INSERT INTO Employees VALUES (3, 'Carol White',   'Developer', 65000, 'IT', TO_DATE('2019-01-10','YYYY-MM-DD'));

COMMIT;
