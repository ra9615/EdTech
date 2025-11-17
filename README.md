# EdTech - Платформа для управления онлайн-обучением
### Архитектурные уровни системы
- **Controller** - конечные точки REST API, принимающие запросы и формирующие ответы.
- **Service** - слой бизнес-логики, отвечающий за обработку данных и выполнение транзакций.
- **Repository** - интерфейсы доступа к базе данных, построенные на Spring Data JPA.
- **Model** - объектная модель приложения (JPA-сущности с аннотациями валидации).
- **Exception** - инфраструктура для централизованной обработки ошибок.
- **Resources** - Конфигурация и миграции БД

#### Используемые переменные окружения:
```shell
# Настройки БД:
DB_HOST=localhost          # Хост БД (по умолчанию: localhost)
DB_PORT=5432               # Порт БД (по умолчанию: 5432)  
DB_NAME=edu_db             # Имя БД (по умолчанию: edtech_db)
DB_USERNAME=postgres       # Пользователь БД (по умолчанию: postgres)
DB_PASSWORD=password       # Пароль БД (по умолчанию: postgres)

# Приложение:
SERVER_PORT=80             # Порт приложения (по умолчанию: 80)
SPRING_PROFILES_ACTIVE=dev # Активный профиль (по умолчанию: dev)

# Логирование и отладка:
SHOW_SQL=true              # Показывать SQL в логах (по умолчанию: true)
HIBERNATE_SQL_LOGGING=true # Логирование Hibernate SQL (по умолчанию: true)
DDL_AUTO=validate          # Стратегия DDL Hibernate (по умолчанию: validate)
FLYWAY_ENABLED=true        # Включить миграции Flyway (по умолчанию: true)
```

## Maven профили и команды для сборки

### Профили:

- test - для тестов (H2 база)
- prod - для продакшена (PostgreSQL)
- dev - для разработки (PostgreSQL)
### 1. Cборка:

- **ПРОДАКШН**
  * Первая команда - сборка без тестов, вторая - с тестами*

    ```shell
    mvn clean install -P prod -DskipTests
    ```
    ```shell
    mvn clean install -P prod
    ```

- **ТЕСТОВАЯ**
  * Без тестов / с тестами (профиль test)*
    ```shell
    mvn clean install -P test -DskipTests
    ```
    ```shell
    mvn clean install -P test
    ```
- **РАЗРАБОТКА**
  * Дефолтная сборка без тестов / с тестами*

    ```shell
    mvn clean install -DskipTests
    ```
    ```shell
    mvn clean install
    ```
- **ТОЛЬКО ТЕСТЫ**
  * Только unit + интеграционные тесты (профиль test / dev)*

    ```shell
    mvn test -P test
    ```
    ```shell
    mvn test
    ```
- **БЫСТРАЯ СБОРКА**
* Полное отключение тестов
  * Для PowerShell:
  ```shell
  mvn clean install -P prod "-Dmaven.test.skip=true"
  ```
  * Для Bash:
  ```shell
  mvn clean install -P prod -Dmaven.test.skip=true
  ```

### Результат тестов:
![img.png](img.png)
### Развернуть приложение в Docker контейнере
#### Собираем
```shell
mvn clean install -P prod -DskipTests
```
#### Разворачиваем
```shell
docker-compose up -d
```

## API Endpoints

### Пользователи

- `GET /api/users` - Получить всех пользователей
- `GET /api/users/{id}` - Получить пользователя по ID
- `GET /api/users/email/{email}` - Получить пользователя по email
- `POST /api/users` - Создать пользователя
- `PUT /api/users/{id}` - Обновить пользователя
- `DELETE /api/users/{id}` - Удалить пользователя

### Категории

- `GET /api/categories` - Получить все категории
- `GET /api/categories/{id}` - Получить категорию по ID
- `GET /api/categories/name/{name}` - Получить категорию по имени
- `POST /api/categories` - Создать категорию
- `PUT /api/categories/{id}` - Обновить категорию
- `DELETE /api/categories/{id}` - Удалить категорию

### Курсы

- `GET /api/courses/{id}` - Получить курс по ID
- `GET /api/courses/teacher/{teacherId}` - Получить курсы преподавателя
- `POST /api/courses` - Создать курс
- `PUT /api/courses/{id}` - Обновить курс
- `DELETE /api/courses/{id}` - Удалить курс

### Записи на курсы

- `GET /api/enrollments/courses/{courseId}/students/{studentId}` - Создать запись
- `DELETE /api/enrollments/courses/{courseId}/students/{studentId}` - Удалить запись
- `GET /api/enrollments/courses/{courseId}/students` - Получить записанных студентов
- `GET /api/enrollments/students/{studentId}/courses` - Получить курсы студента
- `GET /api/enrollments/student/{studentId}` - Получить курсы записанного студента


### Модули курсов

- `GET /api/modules` - Получить все модули
- `GET /api/modules/{id}` - Получить модуль по ID
- `GET /api/modules/course/{courseId}` - Получить модули курса
- `POST /api/modules` - Создать модуль
- `PUT /api/modules/{id}` - Обновить модуль
- `DELETE /api/modules/{id}` - Удалить модуль

### Уроки

- `GET /api/lessons` - Получить все уроки
- `GET /api/lessons/{id}` - Получить урок по ID
- `GET /api/lessons/module/{moduleId}` - Получить уроки по модулю
- `POST /api/lessons` - Создать урок
- `PUT /api/lessons/{id}` - Обновить урок
- `DELETE /api/lessons/{id}` - Удалить урок

### Задания

- `POST /api/assignments/{lessonId}` - Создать задание
- `POST /api/assignments/{assignmentId}/submit/{studentId}` - Подтвердить задание
- `PUT /api/assignments/submissions/{submissionId}/grade` - Оценить задание
- `GET /api/assignments/{assignmentId}/submissions` - Получить задание
- `GET /api/assignments/students/{studentId}/submissions` - Получить задание студента



### Отправки работ

- `GET /api/submissions` - Получить все отправки работ
- `GET /api/submissions/{id}` - Получить отправку по ID
- `GET /api/submissions/assignment/{assignmentId}` - Получить отправки по заданию
- `GET /api/submissions/student/{studentId}` - Получить отправки студента
- `GET /api/submissions/assignment/{assignmentId}/student/{studentId}` - Получить отправку студента по заданию
- `POST /api/submissions` - Создать отправку работы
- `PUT /api/submissions/{id}` - Обновить отправку
- `DELETE /api/submissions/{id}` - Удалить отправку

### Тесты

- `GET /api/quizzes` - Получить все тесты
- `GET /api/quizzes/{id}` - Получить тест по ID
- `GET /api/quizzes/module/{moduleId}` - Получить тест модуля
- `POST /api/quizzes` - Создать тест
- `PUT /api/quizzes/{id}` - Обновить тест
- `DELETE /api/quizzes/{id}` - Удалить тест

### Вопросы тестов

- `GET /api/questions` - Получить все вопросы
- `GET /api/questions/{id}` - Получить вопрос по ID
- `GET /api/questions/quiz/{quizId}` - Получить вопросы теста
- `POST /api/questions` - Создать вопрос
- `PUT /api/questions/{id}` - Обновить вопрос
- `DELETE /api/questions/{id}` - Удалить вопрос

### Варианты ответов

- `GET /api/answer-options` - Получить все варианты ответов
- `GET /api/answer-options/{id}` - Получить вариант ответа по ID
- `GET /api/answer-options/question/{questionId}` - Получить варианты ответов вопроса
- `POST /api/answer-options` - Создать вариант ответа
- `PUT /api/answer-options/{id}` - Обновить вариант ответа
- `DELETE /api/answer-options/{id}` - Удалить вариант ответа

### Отправки тестов

- `GET /api/quiz-submissions` - Получить все отправки тестов
- `GET /api/quiz-submissions/{id}` - Получить отправку теста по ID
- `GET /api/quiz-submissions/student/{studentId}` - Получить отправки тестов студента
- `GET /api/quiz-submissions/quiz/{quizId}` - Получить отправки по тесту
- `GET /api/quiz-submissions/quiz/{quizId}/student/{studentId}` - Получить отправку студента по тесту
- `POST /api/quiz-submissions` - Создать отправку теста
- `PUT /api/quiz-submissions/{id}` - Обновить отправку теста
- `DELETE /api/quiz-submissions/{id}` - Удалить отправку теста
