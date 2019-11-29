# Endpoints of UserController

In this file are all Endpoints of the UserController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | both      |
| method         | both      |

#### Register Endpoint

Used to register a new user in the backend.  

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

#### $count Endpoint

When requested displays the amount of registered users

 ```
{host}/api/v1/users/$count
{host}/api/v1/users/$count/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 503       |
| response         | Long      |

#### All User Endpoint

When requested displays all registered users (DTOs) serialized to JSON

 ```
{host}/api/v1/users/all
{host}/api/v1/users/all/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |

#### User Endpoint

When requested displays the data of the requested user serialized to JSON

 ```
{host}/api/v1/users/user/{user}
{host}/api/v1/users/user/{user}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |

#### Follow User Endpoint

When called toggles if the user follows the followUser.
 Returns a Boolean that stats if he follows or not follows (true = following, false = not following).

 ```
{host}/api/v1/users/follow/{followUser}
{host}/api/v1/users/follow/{followUser}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | Boolean   |

#### Update User Password Endpoint

When called updates user password.
Returns new JwtToken for new Password.

 ```
{host}/api/v1/users/update/password
{host}/api/v1/users/update/password/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |

Expected in Body:

 ```
{
	"oldPassword": "<PASSWORD>",
	"newPassword": "<PASSWORD>"
}
 ```

#### Update User Bio Endpoint

When called updates user bio.

 ```
{host}/api/v1/users/update/bio
{host}/api/v1/users/update/bio/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |

Expected in Body:

 ```
{
	"content": "<CONTENT>"
}
 ```

#### Set User Image Endpoint

When called updates user profile picture.

 ```
{host}/api/v1/users/image
{host}/api/v1/users/image/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | null      |

#### Remove User Image Endpoint

When called removes user image.

 ```
{host}/api/v1/users/image/remove
{host}/api/v1/users/image/remove/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | null      |
