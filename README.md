### build and deploy
mvn jetty:run

### REST requests
* login
    * GET [http://localhost:8080/registration/rest/user/login?id=root&password=qwerty](http://localhost:8080/registration/rest/user/login?id=root&password=qwerty)
    * will return session token
* get all users
    * GET [http://localhost:8080/registration/rest/user?token=<token>](http://localhost:8080/registration/rest/user?token=<token>)
* get user
    * GET [http://localhost:8080/registration/rest/user/<id>?token=<token>](http://localhost:8080/registration/rest/user/<id>?token=<token>)
* create user
    * POST [http://localhost:8080/registration/rest/user?token=<token>](http://localhost:8080/registration/rest/user?token=<token>)
* delete user
    * DELETE [http://localhost:8080/registration/rest/user/<id>?token=<token>](http://localhost:8080/registration/rest/user/<id>?token=<token>)
