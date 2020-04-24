# Routes

| Route |Method|   Headers  |    Body    |                                         Description                                                      |  
|--     |:----:|------------|------------|----------------------------------------------------------------------------------------------------------|  
|/rules | POST |Content-Type|json or yaml| Send the rules to the mock                                                                               |  
|/attack| POST |            |            |Launch attacks based on rules. <br>Query parameter: `type` = `all` or `httpflood` or `xss` or `robustness`|
|/config| POST |            |            | Only on ESP8266.<br>Config the ESP Wi-Fi.<br>Query parameters: `ssid`, `password`, `ip`                  |  
