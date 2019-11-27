# Endpoints of CommentController

In this file are all Endpoints of the UserController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | true      |
| method         | both      |

#### $count Endpoint

When requested displays the amount of comments

 ```
{host}/api/v1/comment/$count
{host}/api/v1/comment/$count/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 503       |
| response         | Long      |

#### All Comment Endpoint

When requested displays all registered comments (DTOs) serialized to JSON

 ```
{host}/api/v1/comment/all
{host}/api/v1/comment/all/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |