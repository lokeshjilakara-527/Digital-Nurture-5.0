-- ============================================================================
-- Exercise 3 : Stored Procedures  (MANDATORY)
-- Requires: 00-schema-and-data.sql
-- ============================================================================
SET SERVEROUTPUT ON;

-- ---- Scenario 1 : ProcessMonthlyInterest ------------------------------------
-- Apply 1% monthly interest to the balance of every Savings account.
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
       SET Balance      = Balance + (Balance * 0.01),
           LastModified = SYSDATE
     WHERE AccountType = 'Savings';
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to ' || SQL%ROWCOUNT || ' savings account(s).');
    COMMIT;
END;
/

-- ---- Scenario 2 : UpdateEmployeeBonus ---------------------------------------
-- Add a bonus percentage (parameter) to the salary of every employee in a dept.
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department    IN Employees.Department%TYPE,
    p_bonus_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
       SET Salary = Salary + (Salary * p_bonus_percent / 100)
     WHERE Department = p_department;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department ' || p_department || '.');
    ELSE
        DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || ' employee(s) in ' || p_department
                             || ' received a ' || p_bonus_percent || '% bonus.');
    END IF;
    COMMIT;
END;
/

-- ---- Scenario 3 : TransferFunds ---------------------------------------------
-- Transfer an amount between accounts after checking sufficient balance.
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN Accounts.AccountID%TYPE,
    p_to_account   IN Accounts.AccountID%TYPE,
    p_amount       IN NUMBER
) AS
    v_balance Accounts.Balance%TYPE;
BEGIN
    -- Lock the source row while we read and update it.
    SELECT Balance INTO v_balance
      FROM Accounts
     WHERE AccountID = p_from_account
       FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001,
            'Insufficient balance in account ' || p_from_account);
    END IF;

    UPDATE Accounts
       SET Balance = Balance - p_amount, LastModified = SYSDATE
     WHERE AccountID = p_from_account;

    UPDATE Accounts
       SET Balance = Balance + p_amount, LastModified = SYSDATE
     WHERE AccountID = p_to_account;

    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account '
                         || p_from_account || ' to account ' || p_to_account || '.');
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: source account ' || p_from_account || ' not found.');
        ROLLBACK;
END;
/

-- ---- Example calls ----------------------------------------------------------
BEGIN
    ProcessMonthlyInterest;
    UpdateEmployeeBonus('IT', 10);
    TransferFunds(4, 1, 500);      -- succeeds (account 4 has enough)
END;
/

-- This one demonstrates the insufficient-funds error:
-- BEGIN TransferFunds(1, 2, 999999); END;
-- /
