# Endpoints of CommentController

In this file are all Endpoints of the UserController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | true      |
| method         | both      |
| endpoints      | 8         |
| mappings       | 16        |

#### $count Endpoint

When requested displays the total amount of comments

 ```
{host}/api/v1/comment/$count
{host}/api/v1/comment/$count/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | Long      |

No params.

#### All Comment Endpoint

When requested displays all registered comments (DTOs) serialized to JSON.
Only for test purposes.

 ```
{host}/api/v1/comment/all
{host}/api/v1/comment/all/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

No params.

#### Add Comment Endpoint

Used to add a Comment to a Image. 

 ```
{host}/api/v1/comment/add
{host}/api/v1/comment/add/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | POST                        |
| authentication   | true                        |
| response success | 204                         |
| response         | null                        |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| content          | text                        |
| imageId          | text                        |

#### Edit Comment Endpoint

Used to edit a existing Comment. 

 ```
{host}/api/v1/comment/update
{host}/api/v1/comment/update/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | POST                        |
| authentication   | true                        |
| response success | 204                         |
| response         | null                        |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| content          | text                        |
| commentId        | text                        |

#### Remove Comment Endpoint

Remove a existing comment. (Only the author of a image can remove it).

 ```
{host}/api/v1/comment/remove
{host}/api/v1/comment/remove/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | POST                        |
| authentication   | true                        |
| response success | 204                         |
| response         | null                        |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| commentId        | text                        |

#### Get Comment Endpoint

Used to get a specific comment by its id 

 ```
{host}/api/v1/comment/comment/{commentId}
{host}/api/v1/comment/comment/{commentId}/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | GET                         |
| authentication   | true                        |
| response success | 200                         |
| response         | JSON                        |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| commentId        | text                        |

#### Get Comments of Image Endpoint

Used to get all comments of a image by its id 

 ```
{host}/api/v1/comment/image/comments/{imageId}
{host}/api/v1/comment/image/comments/{imageId}/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | GET                         |
| authentication   | true                        |
| response success | 200                         |
| response         | JSON                        |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| imageId          | text                        |

#### Get Comments Amount of Image Endpoint

Used to get the amount of comments on a image by its id 

 ```
{host}/api/v1/comment/image/$count/{imageId}
{host}/api/v1/comment/image/$count/{imageId}/
 ```

| Label            | Parameter                   |
| ---------------- | --------------------------- |
| method           | GET                         |
| authentication   | true                        |
| response success | 200                         |
| response         | JSON                        |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| imageId          | text                        |