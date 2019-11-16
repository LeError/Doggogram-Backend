# Endpoints of UserController

In this file are all Endpoints of the UserController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | both      |
| method         | both      |



#### Register Endpoint

Used to register a new user in the backend. This 

 ```
{host}/api/v1/users/register
{host}/api/v1/users/register/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | false     |
| response success | 202       |
| response fail    | 406       |
| response         | null      |

In the body should be contained:

```json
{
	"user": "<USERNAME>",
	"pass": "<PASSWORD>"
}
```