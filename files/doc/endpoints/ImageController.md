# Endpoints of ImageController

In this file are all Endpoints of the ImageController listed.

| Label          | Parameter |
| -------------- | --------- |
| version        | V1        |
| authentication | true      |
| method         | both      |

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
| response fail    | 503       |
| response         | Long      |

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
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |

#### All Images Endpoint

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
| response fail    | 400       |
| response         | JSON      |

#### Image Endpoint

When requested displays the base64 encoded image of the requested image

 ```
{host}/api/v1/images/image/{imageId}
{host}/api/v1/images/image/{imageId}/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 409       |
| response         | JSON      |

#### User Images Endpoint

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
| response fail    | 404       |
| response         | JSON      |

#### Discover Images Endpoint

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
| response fail    | 404       |
| response         | JSON      |

#### Followed User Images Endpoint

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
| response fail    | 404       |
| response         | JSON      |