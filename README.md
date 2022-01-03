# digicertinterview

Checkout project - booklibrary
Use your favourite IDE - I use Intellij
Run the main class BookLibraryApplication
Run postman calls

Sample postman calls

Create book
Post
http://localhost:8080/api/books
{
    "title": "how to learn java",
    "description": "all about java",
    "publisher": "kirthan"
}

Update book
Put
http://localhost:8080/api/books/1
{
    "title": "how to learn spring boot",
    "description": "all about latest and greatest spring boot rest api",
    "publisher": "kirthan",
    "issued": "true"
}

Retrive all books
Get
http://localhost:8080/api/books

Also there are few more operations and a delete to delete all records.
All the CRUD opperation included
