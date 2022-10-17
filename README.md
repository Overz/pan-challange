# Challange

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
	"ACCEPTED_RESOURCES": ["films", "peoples"],
	"EDITABLE_RESOURCES": { "films": ["opening_crawl"] }
}
```

## Project Folder Structure

- `config`: pre configure some classes inside the application
- `errors`: throwing application errors
- `events`: handling some application events
- `routes`: application routes only
	- `controllers`: application controllers
		- `services`: application services
