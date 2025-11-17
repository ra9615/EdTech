INSERT INTO users (name, email, role)
VALUES ('Alice Johnson', 'alice@example.com', 'STUDENT'),
       ('Bob Smith', 'bob@example.com', 'TEACHER'),
       ('Carol White', 'carol@example.com', 'ADMIN');

INSERT INTO categories (name)
VALUES ('Programming'),
       ('Design'),
       ('Marketing');

INSERT INTO tags (name)
VALUES ('Java'),
       ('Basics'),
       ('Design');

INSERT INTO courses (title, description, duration, start_date, category_id, teacher_id)
VALUES ('Java Basics', 'Introduction to Java programming', 40, '2024-08-01', 1, 2),
       ('Advanced Design', 'Deep dive in design principles', 60, '2024-09-01', 2, 2);

INSERT INTO profiles (bio, avatar_url, phone, user_id)
VALUES ('Student interested in Java', 'http://example.com/avatar1.jpg', '+1234567890', 1),
       ('Experienced teacher of CS', 'http://example.com/avatar2.jpg', '+1234567891', 2),
       ('Administrator of platform', 'http://example.com/avatar3.jpg', '+1234567892', 3);

INSERT INTO modules (title, order_index, description, course_id)
VALUES ('Introduction', 1, 'Getting started with Java', 1),
       ('OOP Concepts', 2, 'Object-Oriented Programming', 1),
       ('Design Basics', 1, 'Basic principles of design', 2);

INSERT INTO lessons (title, content, video_url, module_id)
VALUES ('Java Setup', 'Installing JDK and IDE', 'http://example.com/java-setup', 1),
       ('Classes and Objects', 'Understanding classes', 'http://example.com/classes', 2),
       ('Color Theory', 'Basics of color theory', 'http://example.com/color-theory', 3);

INSERT INTO assignments (title, description, due_date, max_score, lesson_id)
VALUES ('Install Java', 'Install JDK and IntelliJ IDEA', '2024-08-10', 100, 1),
       ('Create Classes', 'Implement sample classes', '2024-08-15', 100, 2),
       ('Color Wheel', 'Create a color wheel', '2024-09-05', 100, 3);

INSERT INTO quizzes (title, time_limit_minutes, module_id)
VALUES ('Introduction to Java Quiz', 30, 1),
       ('Advanced Java Concepts Quiz', 45, 2);

INSERT INTO course_tag (course_id, tag_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);

INSERT INTO enrollments (student_id, course_id, enroll_date, status)
VALUES (1, 1, '2024-07-20', 'ACTIVE'),
       (1, 2, '2024-07-21', 'ACTIVE');

INSERT INTO quiz_submissions (quiz_id, student_id, score, taken_at)
VALUES (1, 1, 85.5, '2024-08-12 11:00:00');

INSERT INTO submissions (assignment_id, student_id, submitted_at, content, score, feedback)
VALUES (1, 1, '2024-08-09 10:00:00', 'Installation completed', 95, 'Good job'),
       (2, 1, '2024-08-14 15:00:00', 'Implemented classes as asked', 90, 'Well done'),
       (3, 1, NULL, NULL, NULL, NULL); -- задание ещё не сдано

INSERT INTO course_reviews (course_id, student_id, rating, comment, created_at)
VALUES (1, 1, 5, 'Excellent course!', '2024-08-20 12:00:00');

INSERT INTO questions (text, type, quiz_id)
VALUES ('What is JVM?', 'SINGLE_CHOICE', 1),
       ('Select OOP features', 'MULTIPLE_CHOICE', 1);

INSERT INTO answer_options (text, is_correct, question_id)
VALUES ('Java Virtual Machine', TRUE, 1),
       ('Java Very Much', FALSE, 1),
       ('Encapsulation', TRUE, 2),
       ('Inheritance', TRUE, 2),
       ('Polymorphism', TRUE, 2),
       ('Compilation', FALSE, 2);
