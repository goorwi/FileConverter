# FileConverter
## Описание проекта
`FileConverter` - программа, позволяющая конвертировать данные из xml в json, и наоборот.
## Использование
Запуск происходит при помощи командной строки. Программа преполагает два режима работы: автоматический и интерактивный.
### Автоматический режим
Входными данными служат 2 аргумента: исходный путь файла, который необходимо конвертировать, путь сохранения файла, содержащего результаты преобразования.
### Интерактивный режим
Программа запускается без аргументов.
## ПО для сборки и запуска
Для успешной сборки и запуска программы рекомендуется следующее программное обеспечение:
- `Java`: Рекомендуемая версия: 20.0.2
- `Maven`: Рекомендуемая версия: 3.9.2
- `IDE`: Intellij Idea. Рекомендуемая версия: 2023.2.3
## Сборка проекта
1. Клонировать репозиторий:
   ```
   git clone https://github.com/goorwi/FileConverter.git
   cd FileConverter
   ```
2. Собрать проект при помощи IDE или командной строки.
## Структура проекта
- `src`: основная папка проекта
  - `main/java/ru/vyatsu/fileconverter`: папка в которой находится основной код проекта
    - `exception`: классы исключений.
    - `model`: классы, представляющие модели данных. 
    - `service`: классы необходимые для работы с xml и json файлами.
  - `test`: пакет, содержащий классы тестов и временные файлы.
    - `java`: классы юнит-тестов для проверки работы программы.
    - `resources`: пакет с файлами xml и json.
## Пример использования
- Для запуска конвертации data.xml в neData.json, необходимо выполнить запрос следующей команды:
```
java -jar FileConverter.jar data.xml newData.json
```
- Для запуска конвертации data.json в neData.xml, необходимо выполнить запрос следующей команды:
```
java -jar FileConverter.jar data.json newData.xml
```
