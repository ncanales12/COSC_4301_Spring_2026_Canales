-- Adds an index to improve queries on habitat locations

CREATE INDEX idx_habitats_location ON habitats(location);