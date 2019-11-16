# Endpoints of AuthController

In this file are all Endpoints of the AuthController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | both      |
| method         | both      |

#### Login Endpoint

Used to Login into Backend-Service.  

 ```URL
{host}/api/v1/auth/login
{host}/api/v1/auth/login/
 ```

| Label            | Parameter            |
| ---------------- | -------------------- |
| method           | POST                 |
| authentication   | false                |
| response success | 200                  |
| response fail    | 404                  |
| response         | Bearer Token in Body |

In the body should be contained:

```json
{
	"user": "<USERNAME>",
	"pass": "<PASSWORD>"
}
```