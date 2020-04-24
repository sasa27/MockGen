# Rules Generator

To generate rules, you need to provide a text file with formatted frames as in the examples below.

It has to follow the pattern "Key=value" separated by "@":
```
label(Key=value@Key=value@...)
```
HTTP request must contain at least the source and destination address, the URI and the HTTP verb/method:
```
/label(Source=...@Dest=...@Uri=...@Verb=...)
```
HTTP response must contain at least the source and destination address and the HTTP response status:
```
label(Source=...@Dest=...@status=...)
```
If there is a body in the request/response, it has to be specified with the keyword "contents":
```
label(...@contents=...)
```
#### How to use the script:  
The default destination file name is *__'rules.json'__* and there are only two file types: *__yaml__* and *__json__*.  
`$ ruby generator.rb -filepath=<path_to_file> [-hostaddress=<host_address>] [-filename=<dest_file_name>] [-filetype=<dest_file_type>]`



### HTTP formatted frame example

#### Request
```
/json.htm(Source=192.168.43.102@Dest=192.168.43.76@Verb=GET@Uri=/json.htm?type=command&param=udevice&idx=1&nvalue=0&svalue=26.00;55.00;1&rssi=7@type=command@param=udevice@idx=1@nvalue=0@svalue=26.00;55.00;1@rssi)
```

#### Response
```
OK (Source=192.168.43.76@Dest=192.168.43.102@status=200@response=OK @Content-Length= 53@Content-Type= application/json@charset=UTF-8@Cache-Control= no-cache@Pragma= no-cache@Access-Control-Allow-Origin= *@contents= {   "status" = "OK",   "title" : "Update Device"})
```
### Rules output file

You will get a file with the rules translated to JSON or YAML like bellow:
```json
{
    "type": "inout",
    "request": {
      "method": "GET",
      "path": "/json.htm?type=command&param=switchlight&idx=2&switchcmd=On&rssi=7",
      "headers": {
        "type": "command",
        "param": "switchlight",
        "idx": "2",
        "switchcmd": "On",
        "rssi": null
      }
    },
    "response": {
      "status": 200,
      "headers": {
        "Content-Length": " 51",
        "Content-Type": " application/json",
        "charset": "UTF-8",
        "Cache-Control": " no-cache",
        "Pragma": " no-cache",
        "Access-Control-Allow-Origin": " *"
      },
      "body": " {   \"status\" : \"OK\",   \"title\" : \"SwitchLight\"}"
    }
  }
````

```yaml
- type: inout
  request:
    method: GET
    path: "/json.htm?type=command&param=switchlight&idx=2&switchcmd=On&rssi=7"
    headers:
      type: command
      param: switchlight
      idx: '2'
      switchcmd: 'On'
      rssi: 
  response:
    status: 200
    headers:
      Content-Length: " 51"
      Content-Type: " application/json"
      charset: UTF-8
      Cache-Control: " no-cache"
      Pragma: " no-cache"
      Access-Control-Allow-Origin: " *"
    body: ' {   "status" : "OK",   "title" : "SwitchLight"}'
```