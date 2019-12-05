# Endpoints of ImageController

In this file are all Endpoints of the ImageController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | true      |
| method         | both      |
| endpoints      | 14        |
| mappings       | 28        |

#### $count Endpoint

When requested displays the amount of images stored by the Backend

 ```
{host}/api/v1/images/$count
{host}/api/v1/images/$count/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | Long      |

No params.

#### Image Upload Endpoint

When a POST-Request with a multipart image is received it is stored by the backend.
Required are the Multipart image of the type jpg, jpeg, png, gif, a title and a bio.
They should be placed in the request with the namespaces file, title and bio.
The user is determined by the JwtToken.   

 ```
{host}/api/v1/images/upload
{host}/api/v1/images/upload/
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
| bio              | text                        |
| title            | text                        |

#### Remove Image Endpoint

When a POST-Request with the image id is send removes a image from the database.
The user is determined by the JwtToken. Only the image owner can delete the image.

 ```
{host}/api/v1/images/remove
{host}/api/v1/images/remove/
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
| imageId          | text                        |

#### Get All Images Endpoint

When requested displays a list of all Images base64 encoded.
Should never be called because of traffic. Only for test purposes.

 ```
{host}/api/v1/images/all 
{host}/api/v1/images/all/ 
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

No params.

#### Get Image Endpoint

When requested displays the base64 encoded image of the requested image and its data.

 ```
{host}/api/v1/images/image/{imageId}
{host}/api/v1/images/image/{imageId}/
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

#### Get User Images Endpoint

When requested displays a list of base64 encoded images of the user that is Requested. 
Only a maximum of 9 images is displayed. If a last id with the value 0 is given the 9 most recent pictures will be Displayed.
If a LastId is given the 9 most recent images with a id lower then the lastId will be displayed.

 ```
{host}/api/v1/images/user/{user}/{lastId}
{host}/api/v1/images/user/{user}/{lastId}/
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
| lastId           | text                        |

#### Get Discover Images Endpoint

When requested displays a list of base64 encoded images of the most recent images on the platform. 
Only a maximum of 9 images is displayed. If a last id with the value 0 is given the 9 most recent pictures will be Displayed.
If a LastId is given the 9 most recent images with a id lower then the lastId will be displayed.

 ```
{host}/api/v1/images/discover/{lastId} 
{host}/api/v1/images/discover/{lastId}/
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
| lastId           | text                        |

#### Get Followed User Images Endpoint

When requested displays a list of base64 encoded images of the user that the user follows. 
Only a maximum of 9 images is displayed. If a last id with the value 0 is given the 9 most recent pictures will be Displayed.
If a LastId is given the 9 most recent images with a id lower then the lastId will be displayed.

 ```
{host}/api/v1/images/feed/{lastId}
{host}/api/v1/images/feed/{lastId}/ 
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
| lastId           | text                        |

#### Toggle Image Like Endpoint 

When requested toggles the like state of a Image for the user that requested the endpoint.
The user is determined by the Auth Token. Returns the state of the Like (true = Liked; false = not Liked). 

 ```
{host}/api/v1/images/liked
{host}/api/v1/images/liked/ 
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
| imageId          | text                        |

#### Get Image Like Endpoint 

When requested returns if the image is liked by the user requesting the endpoint. 
The user is determined by the Auth Token. Returns the state of the Like (true = Liked; false = not Liked). 

 ```
{host}/api/v1/images/liked/{imageId}
{host}/api/v1/images/liked/{imageId}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response         | Boolean   |

In the body should be contained (path-variable):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| imageId          | text                        |

#### Get Image Likes Endpoint 

When requested returns the amount of likes a image has.

 ```
{host}/api/v1/images/image/likes/{imageId}
{host}/api/v1/images/image/likes/{imageId}/
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
| imageId          | text                        |

#### Get Liked Images Endpoint 

When requested returns a list of liked Images similar to all feeds.

 ```
{host}/api/v1/images/image/liked/{lastId}
{host}/api/v1/images/image/liked/{lastId}/
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
| lastId           | text                        |

#### Update Image Title

When requested updates title of Image. The user is determined by the Auth Token.
Only the Owner of the image can update it

 ```
{host}/api/v1/images/update/title
{host}/api/v1/images/update/title/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| imageId          | text                        |
| content          | text                        |

#### Update Image Bio

When requested updates bio of Image. The user is determined by the Auth Token.
Only the Owner of the image can update it

 ```
{host}/api/v1/images/update/bio
{host}/api/v1/images/update/bio/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response         | JSON      |

In the body should be contained (form-data):

| Label            | Typ                         |
| ---------------- | --------------------------- |
| imageId          | text                        |
| content          | text                        |