# FileConverter
## Описание проекта
`FileConverter` - программа, позволяющая конвертировать данные из xml в json, и наоборот.
## Использование
Запуск происходит при помощи командной строки. Входными данными служат 2 аргумента:
1. Исходный путь файла, который необходимо конвертировать
1. Путь сохранения файла, содержащего результаты преобразования.
## ПО для сборки и запуска
Рекомендуемая версия Intellij Idea - 2023.2.3
## Структура проекта
- `src/main`: основная папка проекта
  - `java/ru/vyatsu`: папка в которой находится основной код проекта
    - `service`: классы необходимые для работы с xml и json файлами 
  - `resources`: примеры xml и json файлов
## Пример использования
- Для запуска конвертации data.xml в neData.json, необходимо выполнить запрос следующей команды:
```
java -jar FileConverter.jar data.xml newData.json
```
- Для запуска конвертации data.json в neData.xml, необходимо выполнить запрос следующей команды:
```
java -jar FileConverter.jar data.json newData.xml
```
