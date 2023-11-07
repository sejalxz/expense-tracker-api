CREATE TABLE IF NOT EXISTS expense (
    id UUID PRIMARY KEY,
    description VARCHAR(200) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    date DATE NOT NULL,
    category VARCHAR(200),
    CONSTRAINT check_amount_positive CHECK (amount >= 0)
);