INSERT INTO instructors
VALUES
    (default, 'Dr. John Smith', 'john.smith@example.com'),
    (default, 'Prof. Sarah Lee', 'sarah.lee@example.com'),
    (default, 'Dr. Michael Chen', 'michael.chen@example.com'),
    (default, 'Prof. Anna Kim', 'anna.kim@example.com'),
    (default, 'Dr. David Brown', 'david.brown@example.com');

INSERT INTO courses
VALUES
    (default, 'Spring Boot Development', 'Learn backend development using Spring Boot', 1),
    (default, 'Database Systems', 'Introduction to relational databases and SQL', 2),
    (default, 'Web Development', 'Full stack web development fundamentals', 3),
    (default, 'Mobile App Development', 'Build mobile apps using Flutter', 4),
    (default, 'Software Engineering', 'Software design principles and practices', 5);

INSERT INTO students
VALUES
    (default, 'Alice Johnson', 'alice.johnson@example.com', '012345678'),
    (default, 'Bob Williams', 'bob.williams@example.com', '098765432'),
    (default, 'Charlie Davis', 'charlie.davis@example.com', '011223344'),
    (default, 'Dara Chhinchheang', 'dara.chhin@example.com', '077889900'),
    (default, 'Emma Wilson', 'emma.wilson@example.com', '066554433');

INSERT INTO student_course (student_id, course_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 1),
    (3, 4),
    (4, 2),
    (4, 5),
    (5, 3),
    (5, 4),
    (2, 5);