INSERT INTO instructors (instructor_id, instructor_name, email)
VALUES
    (1, 'Dr. John Smith', 'john.smith@example.com'),
    (2, 'Prof. Sarah Lee', 'sarah.lee@example.com'),
    (3, 'Dr. Michael Chen', 'michael.chen@example.com'),
    (4, 'Prof. Anna Kim', 'anna.kim@example.com'),
    (5, 'Dr. David Brown', 'david.brown@example.com');

INSERT INTO courses (course_id, course_name, description, instructor_id)
VALUES
    (1, 'Spring Boot Development', 'Learn backend development using Spring Boot', 1),
    (2, 'Database Systems', 'Introduction to relational databases and SQL', 2),
    (3, 'Web Development', 'Full stack web development fundamentals', 3),
    (4, 'Mobile App Development', 'Build mobile apps using Flutter', 4),
    (5, 'Software Engineering', 'Software design principles and practices', 5);

INSERT INTO students (student_id, student_name, email, phone_number)
VALUES
    (1, 'Alice Johnson', 'alice.johnson@example.com', '012345678'),
    (2, 'Bob Williams', 'bob.williams@example.com', '098765432'),
    (3, 'Charlie Davis', 'charlie.davis@example.com', '011223344'),
    (4, 'Dara Chhinchheang', 'dara.chhin@example.com', '077889900'),
    (5, 'Emma Wilson', 'emma.wilson@example.com', '066554433');

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