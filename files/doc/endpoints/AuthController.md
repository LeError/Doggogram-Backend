# Endpoints of AuthController

In this file are all Endpoints of the AuthController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | false     |
| method         | POST      |
| endpoints      | 1         |
| mappings       | 2         |

#### Login Endpoint

Used to Login into Backend-Service.  

 ```URL
{host}/api/v1/auth/login
{host}/api/v1/auth/login/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | POST                        |
| authentication   | false                       |
| response success | 200                         |
| response         | JSON (Bearer Token in Body) |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |
| password         | text                        |