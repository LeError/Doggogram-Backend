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
{host}/api/v1/storage/images/$count
{host}/api/v1/storage/images/$count/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | int       |

#### All Image Filenames Endpoint

When requested displays all filenames of all images stored serialized to JSON

 ```
{host}/api/v1/storage/images/filenames/all
{host}/api/v1/storage/images/filenames/all/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 400       |
| response         | JSON      |

#### Image Upload Endpoint

When a POST-Request with a multipart image is received it is stored by the backend

 ```
{host}/api/v1/storage/images/upload
{host}/api/v1/storage/images/upload/
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | POST      |
| authentication   | true      |
| response success | 200       |
| response fail    | 409       |
| response         | JSON      |

#### Image Endpoint

When requested displays the base64 encoded image of the requested image

 ```
{host}/api/v1/storage/images/load/{filename:.+}
{host}/api/v1/storage/images/load/{filename:.+}
 ```

| Label            | Parameter |
| ---------------- | --------- |
| method           | GET       |
| authentication   | true      |
| response success | 200       |
| response fail    | 409       |
| response         | JSON      |
