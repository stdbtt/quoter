# quoter

API for the webservice where users share quotes.

---
#### Create user
    POST /api/users
Body parameters: 
* name
* email
* password

Return value: 200OK

---

#### Add quote
    POST /api/quotes
Body parameters: 
* content
* user:
  * name
  
Return value: ID of created quote

---

#### Update quote
    PATCH /api/quotes/{id}
Body parameters: 
* content

Return value: 200OK

---

#### Get quote
    GET /api/quotes/{id}

Return value: Quote

Return fields:
* content
* user:
  * name
* votes:
  * upvotes
  * downvotes
---

#### Delete quote
    DELETE /api/quotes/{id}

Return value: 200OK

---

#### Get random quote
    GET /api/quotes/random

Return value: Quote

Return fields:
* content
* user:
  * name
* votes:
  * upvotes
  * downvotes
---

#### Upvote quote
    POST /api/quotes/{id}/upvote

Return value: 200OK

---

#### Downvote quote
    POST /api/quotes/{id}/downvote

Return value: 200OK

---

#### Get Top10 
    GET /api/quotes/top10
    
Return value: array of Quotes

Return fields:
* content
* user:
  * name
* votes:
  * upvotes
  * downvotes
---    

#### Get Worst10
    GET/api/quotes/worst10
    
Return value: array of Quotes

Return fields:
* content
* user:
  * name
* votes:
  * upvotes
  * downvotes
---  

## Example:

    POST /api/quotes
    {
        "content": "Bonjour!",
        "user": {
           "name": "Tomas"
        }
    }
    
Return: 1

    GET /api/quotes/1
    {
        "id": 1,
        "content": "Bonjour!",
        "user": {
            "name": "Tomas"
        },
        "votes": {
            "upvotes": 0,
            "downvotes": 0
        }
    }

