-- Adds an index to improve filtering and queries by danger level

CREATE INDEX idx_creatures_danger_level ON creatures(danger_level);