-- Inserts a default habitat used when creating new creatures

INSERT INTO habitats (biome, location, min_temp_c, max_temp_c, created_at)
VALUES ('FOREST', 'Default Habitat', 10, 25, NOW());