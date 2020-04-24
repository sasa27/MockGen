# Attacks specification

With this tool, it's possible to do different types of attack :
- __XSS(Cross Site Scripting)__: inject scripts in the request query parameters, in the headers or in the body.
- __HTTP flood__: send a lot of HTTP GET or POST requests to attack a web server or an application.
- __Robustness attacks__ (here HTTP method modifications): delete the method, write a method that doesn't exist or inject special characters in the method.

## How to send attacks

To send attacks, you have to send a HTTP POST request to the mock IP address, on the "attack" route, with the query parameter "type" and the attack type you want to do.

```
Example : http://192.168.1.1:8080/attack?type=xss
```

List of parameters you can put on the request:
- all (execute all attacks and robustness methods).
- httpflood
- xss
- robustness

## How it works

The class "Attacker" manages these attacks, it contains several methods which change and create OutIn rules according to the type of attack.

__Method httpFloodAttack:__

This method works differently on the ESP8266 web service and on the Java web service :
- On the Java web service: it uses other methods to generate a file with a random String of 5 000 000 alphanumerics characters. Then, a method reads this file and extracts the String to put it in the OutInRule request body. This request is sent 2000 times simultaneously at the addresses obtained with the existing rules.
- On the ESP8266 web service: it doesn't generate file. It creates a random String and put it in the OutInRule request body. This request is sent at the addresses obtained with the existing rules.

__Method XSSAttack:__

There are 4 different scripts which are injected: 
- \<script>alert('XSS')\</script>
- <img src=javascript:alert('XSS')>
- \</script>\<script>alert('XSS')\</script>
- \<svg onload=alert('XSS')>

Each script is injected in the URL query parameters, in the headers and in the body of the request of each existing OutIn rules. 

__Robustness attacks:__

For each existing OutIn rules:
- Method __verbNotExist()__: puts a verb that does not exist in the HTTP verb.
- Method __emptyVerb()__: deletes the HTTP verb.
- Method __specialChar()__: injects special characters in the HTTP verb.
