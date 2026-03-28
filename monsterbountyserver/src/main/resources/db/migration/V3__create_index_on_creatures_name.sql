-- Adds an index to improve lookup performance on creature names

CREATE INDEX idx_creatures_name ON creatures(name);