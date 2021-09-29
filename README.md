
# Application 

Minimum version of JDK is 11

Application has 3 configurations local, prod, testing

**local configuration**
simple DB password and enabled H2 console

**prod configuration**
randomly generated strong DB password and disabled H2 console

**testing configuration**
hostname is localhost

to use /artist/* endpoints you need to supply **Authorization id** which equals the user id in the H2 database.
**For simplicity a default user with userId is created 100001**

---
    /artist/search/{keyword}
keyword example = abba, acdc 

Will return 200 OK status code on success  with body from itunes

---
    /artist/save

Requires request body e.g.
  

    { "artistId" : 372976, "artistName" : "abba", "amgArtistId" : 3492 }

All request body values are taken from the result /artist/search/abba

Will return 201 Created status code on success 
Will return 409 Status if some reason it failed to save

---
    /artist/top/{amgArtistId}

Returns artist top 5 albums only if the artist was saved to favorite artists. As the specification requires

Will return 201 Created status code on success with body from itunes
Will return 409 Status if the artist is not in the users favorites

---
    /health/ping
Simple ping health check exists.

