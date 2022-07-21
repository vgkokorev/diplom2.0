Предварительно должен быть скачен и запущен Docker. Скачаны образы для докера Node.js, mysql, postgresql.
1. Выполнить в терминале команду `docker-compose up`
2. Открыть новую консоль.

Работа с MySQL

1. Запускаем приложение командой `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar`
2. После подъема приложений запускаем автотесты командой `gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app allureReport`

Работа с Postgres

1. Запускаем приложение командой `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar`
2. После подъема приложений запускаем автотесты командой `gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app allureReport`.

После прохождения тестов, в последнем терминале остановить работу приложения сочетанием клавиш ctrl + C.
Остановить контейнеры командой docker-compose down