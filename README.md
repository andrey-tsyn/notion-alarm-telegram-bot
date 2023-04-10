# Notion alarm bot

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5481b64e21f24c87ad377c8664b5c3ad)](https://app.codacy.com/gh/Hulio13/notion-alarm-telegram-bot?utm_source=github.com&utm_medium=referral&utm_content=Hulio13/notion-alarm-telegram-bot&utm_campaign=Badge_Grade)

## 🚧WORK IN PROGRESS🚧

## Описание
Суть бота заключается в том, чтобы уведомлять пользователя об отсутствии
изменений на странице/в базе данных notion на протяжении определённого
времени.

## На что следует обратить внимание?
- Реализация локализации. Package: "telegramBot.localization"
- Реализация обработчиков данных с использованием своих аннотаций. Package: "telegramBot.inputHandlers"
- Использование переодического сохранения json базы данных и сохранение при завершении работы
с использованием Shutdown hook. Package: "database.jsonDb"

## Состояние проекта

```
└──notionAlarm
    ├── configuration - rework
    ├── core - done
    ├── database - done
    ├── notion - done
    └── telegramBot - in progress
```
