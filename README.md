
# Application 

Minimum version of JDK is 11

Application has 3 configurations local, prod, testing

**local configuration**
simple DB password and enabled H2 console

**prod configuration**
randomly generated strong DB password and disabled H2 console

**testing configuration**
hostname is localhost

To select a configuration you will need to pass the following environment variable 
spring.profiles.active={configuration}

e.g.
spring.profiles.active=local

to use /artist/* endpoints you need to supply **Authorization id** which equals the user id in the H2 database.
**For simplicity a default user with userId is created 100001**

The postman collection included in the repository has set Authorization header and valid request bodies and path parameters

---
    /artist?keyword={keyword}
keyword example = abba, acdc 

Will return 200 OK status code on success  with body from itunes

Will return 401 Unauthorized if Authorization header does not exist or id is invalid

Will return 409 if too many requests

Will return 503 service unavailable if calling music provider fails

---
    /artist

Requires request body e.g.
  

    { "artistId" : 372976, "artistName" : "abba", "amgArtistId" : 3492 }

All request body values are taken from the result /artist/search/abba

Will return 201 Created status code on success

Will return 401 Unauthorized if Authorization header does not exist or id is invalid

Will return 409 if too many requests

---
    /artist/{amgArtistId}/top-albums

Returns artist top 5 albums only if the artist was saved to favorite artists. As the specification requires

Will return 201 Created status code on success with body from itunes

Will return 401 Unauthorized if Authorization header does not exist or id is invalid

Will return 409 if too many requests

Will return 503 service unavailable if calling music provider fails

