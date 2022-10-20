# Backend Challange - Pan Bank - [Challange](./docs/challange.md)

Tests are disabled

## Setup Project

Java version: 17

Profiles:
- dev
- prod

Environments:
- `DEBUG`: boolean
- `ACCEPTED_RESOURCES`: string list with values `people`,`planets`,`films`,`species`,`vehicles`,`starships`, used for enable actions inside the app
- `EDITABLE_RESOURCES`: hashtable following the struct `{"key": ["field_name"]}`, used for enable edition in resources fields.

Environments Example:

```json
{
	"DEBUG": false,
	"ACCEPTED_RESOURCES": "films,peoples",
	"EDITABLE_RESOURCES": { "films": ["opening_crawl"] }
}
```

## Project Folder Structure

- `aspects`: aspectj weaver
  - `routes`: routes aspects
    - `validations`: validations before enter in the application router
- `config`: pre configure some classes inside the application
- `controllers`: layers
- `errors`: throwing/handling application errors
- `events`: handling events
- `routes`: application routes only
  - `controllers`: application controllers
    - `services`: application services
- `services`: layers
- `utils`: whatever
