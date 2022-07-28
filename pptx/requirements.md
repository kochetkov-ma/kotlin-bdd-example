Требования
---

## Контроллер валидации

ℹ️ - Общая информация

По адресу `/validation` принимает HTTP POST запрос с JSON телом

```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "description": "Запрос на проверку",
  "properties": {
    "text": {
      "type": "string",
      "description": "Любой текст на проверку"
    }
  },
  "required": [
    "text"
  ]
}
```

И генерирует ответ:

```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "description": "Ответ с кодом ошибки и сообщением",
  "properties": {
    "code": {
      "type": "integer",
      "description": "Код ошибки или 0"
    },
    "message": {
      "type": "string",
      "description": "Описание ошибки или OK"
    }
  },
  "required": [
    "code",
    "message"
  ]
}
```

___

### REQ1 - Отсутствие поля в запросе

- В исходном запросе отсутствует поле `text`
- `code` ответа = `1`
- `message` ответа = `Null text field`

___

### REQ2 - Пустое поле в запросе

- В исходном запросе поле `text` пустое или состоит из одних пробелов
- `code` ответа = `2`
- `message` ответа = `Blank text field`

___

### REQ3 - Успех

- В исходном запросе поле `text` заполнено
- `code` ответа = `0`
- `message` ответа = `Ok`