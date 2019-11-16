# List of all Endpoints

In this file are all Endpoints of the Backend-API listed in short version. Go to controller files for a detailed overview.

| Endpoint                                 | Method    | Authentication | Controller        | Version   |
| ---------------------------------------- | --------- | -------------- | ----------------- | --------- |
| {host}/api/v1/users/register             | POST      | false          | UserController    | V1        |
| {host}/api/v1/users/register/            | POST      | false          | UserController    | V1        |
| {host}/api/v1/users/$count               | GET       | true           | UserController    | V1        |
| {host}/api/v1/users/$count/              | GET       | true           | UserController    | V1        |
| {host}/api/v1/users/all                  | GET       | true           | UserController    | V1        |
| {host}/api/v1/users/all/                 | GET       | true           | UserController    | V1        |
| {host}/api/v1/users/user/{user}          | GET       | true           | UserController    | V1        |
| {host}/api/v1/users/user/{user}/         | GET       | true           | UserController    | V1        |
| {host}/api/v1/auth/login                 | POST      | false          | AuthController    | V1        |
| {host}/api/v1/auth/login/                | POST      | false          | AuthController    | V1        |
| {host}/api/v1/images/storage/$count      | GET       | true           | ImageController   | V1        |
| {host}/api/v1/images/storage/$count/     | GET       | true           | ImageController   | V1        |
| {host}/api/v1/images/filenames/all       | GET       | true           | ImageController   | V1        |
| {host}/api/v1/images/filenames/all/      | GET       | true           | ImageController   | V1        |
| {host}/api/v1/images/upload              | POST      | true           | ImageController   | V1        |
| {host}/api/v1/images/upload/             | POST      | true           | ImageController   | V1        |
| {host}/api/v1/images/load/{filename:.+}  | GET       | true           | ImageController   | V1        |
| {host}/api/v1/images/load/{filename:.+}/ | GET       | true           | ImageController   | V1        |