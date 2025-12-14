CREATE TABLE scoring_parameter
(
    id bigint NOT NULL,
    a double precision NOT NULL,
    b double precision NOT NULL,
    c double precision NOT NULL,
    is_time_based boolean NOT NULL,
    multiplier double precision NOT NULL,
    CONSTRAINT scoring_parameter_pkey PRIMARY KEY (id)
);

INSERT INTO scoring_parameter (id, a, b, c, is_time_based, multiplier) VALUES
    (0, 25.4347, 18, 1.81, true, 1),
    (1, 0.14354, 220, 1.4, false, 100),
    (2, 51.39, 1.5, 1.05, false, 1),
    (3, 0.8465, 75, 1.42, false, 100),
    (4, 1.53775, 82, 1.81, true, 1),
    (5, 5.74352, 28.5, 1.92, true, 1),
    (6, 12.91, 4, 1.1, false, 1),
    (7, 0.2797, 100, 1.35, false, 100),
    (8, 10.14, 7, 1.08, false, 1),
    (9, 0.03768, 480, 1.85, true, 1);
