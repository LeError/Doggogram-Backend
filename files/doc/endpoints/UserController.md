# Endpoints of UserController

In this file are all Endpoints of the UserController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | both      |
| method         | both      |
| endpoints      | 16        |
| mappings       | 32        |

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
| response success | 204       |
| response         | null      |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |
| password         | text                        |

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
| response         | Long      |

No params.

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
| response         | JSON      |

No params.

#### Get User Endpoint

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
| response         | JSON      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Search User Endpoint

When requested Searches for matching users

 ```
{host}/api/v1/users/search/{user}
{host}/api/v1/users/search/{user}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Follow User Endpoint

When called toggles if the user follows the followUser.
Returns a Boolean that stats if he follows or not follows (true = following, false = not following).

 ```
{host}/api/v1/users/follow
{host}/api/v1/users/follow/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response         | Boolean   |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| followUser       | text                        |

#### Get Username Endpoint

When called returns the username of a user.

 ```
{host}/api/v1/users/username
{host}/api/v1/users/username/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | String    |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Get Followers Endpoint

When called returns a list of Users that follow the given user.

 ```
{host}/api/v1/users/followers/user/{user}
{host}/api/v1/users/followers/user/{user}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Get count of Followers Endpoint

When called returns the amount of users that follow the given user.

 ```
{host}/api/v1/users/followers/$count/{user}
{host}/api/v1/users/followers/$count/{user}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | Long      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Get Following Endpoint

When called returns the List of Users that the given user is following.

 ```
{host}/api/v1/users/following/user/{user}
{host}/api/v1/users/following/user/{user}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Get Following count Endpoint

When called returns the amount of users the given user is following.

 ```
{host}/api/v1/users/following/$count/{user}/
{host}/api/v1/users/following/$count/{user}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | Long      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| user             | text                        |

#### Update User Password Endpoint

When called updates user password.
Returns new JwtToken for new Password.
User is determined by the Auth Token.

 ```
{host}/api/v1/users/update/password
{host}/api/v1/users/update/password/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

Expected in Body:

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| oldPassword      | text                        |
| newPassword      | text                        |

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
| response success | 204       |
| response         | JSON      |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| content          | text                        |

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
| response success | 204       |
| response         | null      |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| file             | file                        |

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
| response success | 204       |
| response         | null      |

No params.

#### Get Image Liker Endpoint

When called returns a list of users that liked the image with the given id.

 ```
{host}/api/v1/users/images/liker/{imageId}
{host}/api/v1/users/images/liker/{imageId}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| imageId          | text                        |
