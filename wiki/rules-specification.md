# Rules Specification

**Rules must be sent as a list of rule object.**

### Rule object:
| Attribute | Type    | Required               | Description                                            |
| --------  | ------- | ---------------------- | ------------------------------------------------------ |
| type      | string  | yes                    | The rule type. Possible values: `inout`, `outin`.      |
| request   | object  | yes                    | The Request object.                                    |
| response  | object  | only with `type=inout` | The Response object.                                   |
| timeout   | long    | no                     | Time in milliseconds to wait before the first request. |
| repeat    | integer | no                     | Number of request to send (0 for infinite).            |
| interval  | long    | no                     | Time in milliseconds between each request.             |

### Request object:
| Attribute | Type    | Required               | Description                                            |
| --------  | ------- | ---------------------- | ------------------------------------------------------ |
| method    | string  | yes                    | The HTTP method/verb.                                  |
| path      | string  | yes                    | The path to resource or host URL.                      |
| headers   | object  | no                     | An object representing the headers.                    |
| body      | string  | no                     | The body of the request.                               |

### Response object:
| Attribute | Type    | Required               | Description                                            |
| --------  | ------- | ---------------------- | ------------------------------------------------------ |
| status    | integer | yes                    | The HTTP Response status (between 100 and 600).        |
| headers   | object  | no                     | An object representing the headers.                    |
| body      | string  | no                     | The body of the request.                               |


## Json Example
```json
[
  {
    "type": "inOut",
    "request": {
      "method": "GET",
      "path": "/hello"
    },
    "response": {
      "status": 200,
      "headers": {
        "Content-Type": "application/json"
      },
      "body": "Hello !"
    }
  },
  {
    "type": "outIn",
    "request": {
      "method": "POST",
      "path": "http://www.domain.com/light1",
      "headers": {
        "Content-Type": "application/json"
      },
      "body": "{\"action\": \"on\"}"
    },
    "timeout": 5000,
    "repeat": 1
  }
]
```

## Yaml Example
```yaml
- type: inOut
  request:
    method: GET
    path: /hello
  response:
    status: 200
    headers:
      Content-Type: application/json
    body: "Hello !"
- type: outIn
  request:
    method: POST
    path: http://www.domain.com/light1
    headers:
      Content-Type: application/json
    body: "{\"action\": \"on\"}"

  timeout: 5000
  repeat: 1
```
