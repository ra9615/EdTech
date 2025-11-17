CREATE TABLE users
(
    id    BIGSERIAL PRIMARY KEY,
    name  TEXT,
    email TEXT UNIQUE,
    role  VARCHAR(50)
);

CREATE TABLE categories
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE tags
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE courses
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT,
    description VARCHAR(2000),
    duration    INTEGER,
    start_date  DATE,
    category_id BIGINT,
    teacher_id  BIGINT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES users (id)
);

CREATE TABLE profiles
(
    id         BIGSERIAL PRIMARY KEY,
    bio        TEXT,
    avatar_url TEXT,
    phone      TEXT,
    user_id    BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE modules
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT,
    order_index INTEGER,
    description TEXT,
    course_id   BIGINT NOT NULL,
    CONSTRAINT fk_course_module FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE TABLE lessons
(
    id        BIGSERIAL PRIMARY KEY,
    title     TEXT,
    content   VARCHAR(5000),
    video_url TEXT,
    module_id BIGINT,
    CONSTRAINT fk_module FOREIGN KEY (module_id) REFERENCES modules (id)
);

CREATE TABLE assignments
(
    id          BIGSERIAL PRIMARY KEY,
    title       TEXT,
    description VARCHAR(2000),
    due_date    TIMESTAMP,
    max_score   INTEGER,
    lesson_id   BIGINT,
    CONSTRAINT fk_lesson FOREIGN KEY (lesson_id) REFERENCES lessons (id)
);

CREATE TABLE quizzes
(
    id                 BIGSERIAL PRIMARY KEY,
    title              TEXT,
    time_limit_minutes INTEGER,
    module_id          BIGINT UNIQUE,
    CONSTRAINT fk_module_quizzes FOREIGN KEY (module_id) REFERENCES modules (id)
);

CREATE TABLE course_tag
(
    course_id BIGINT NOT NULL,
    tag_id    BIGINT NOT NULL,
    PRIMARY KEY (course_id, tag_id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (id),
    CONSTRAINT fk_tag FOREIGN KEY (tag_id) REFERENCES tags (id)
);

CREATE TABLE enrollments
(
    id          BIGSERIAL PRIMARY KEY,
    student_id  BIGINT NOT NULL,
    course_id   BIGINT NOT NULL,
    enroll_date TIMESTAMP,
    status      VARCHAR(50),
    CONSTRAINT uq_student_course UNIQUE (student_id, course_id),
    CONSTRAINT fk_student_enrollment FOREIGN KEY (student_id) REFERENCES users (id),
    CONSTRAINT fk_course_enrollment FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE TABLE quiz_submissions
(
    id         BIGSERIAL PRIMARY KEY,
    quiz_id    BIGINT,
    student_id BIGINT,
    score      DOUBLE PRECISION,
    taken_at   TIMESTAMP,
    CONSTRAINT fk_quiz FOREIGN KEY (quiz_id) REFERENCES quizzes (id),
    CONSTRAINT fk_student_quizz FOREIGN KEY (student_id) REFERENCES users (id)
);

CREATE TABLE submissions
(
    id            BIGSERIAL PRIMARY KEY,
    assignment_id BIGINT NOT NULL,
    student_id    BIGINT NOT NULL,
    submitted_at  TIMESTAMP,
    content       VARCHAR(5000),
    score         INTEGER,
    feedback      TEXT,
    CONSTRAINT uq_assignment_student UNIQUE (assignment_id, student_id),
    CONSTRAINT fk_assignment FOREIGN KEY (assignment_id) REFERENCES assignments (id),
    CONSTRAINT fk_student_submission FOREIGN KEY (student_id) REFERENCES users (id)
);

CREATE TABLE course_reviews
  (
      id         BIGSERIAL PRIMARY KEY,
      rating     INTEGER,
      comment    VARCHAR(2000),
      created_at TIMESTAMP,
      course_id  BIGINT,
      student_id BIGINT,
      CONSTRAINT fk_course_review FOREIGN KEY (course_id) REFERENCES courses (id),
      CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES users (id)
  );

CREATE TABLE questions
(
    id      BIGSERIAL PRIMARY KEY,
    text    VARCHAR(1000),
    type    VARCHAR(255),
    quiz_id BIGINT,
    CONSTRAINT fk_quiz_question FOREIGN KEY (quiz_id) REFERENCES quizzes (id)
);

CREATE TABLE answer_options
(
    id          BIGSERIAL PRIMARY KEY,
    text        TEXT,
    is_correct  BOOLEAN,
    question_id BIGINT,
    CONSTRAINT fk_question FOREIGN KEY (question_id) REFERENCES questions (id)
);





