
structure : 

- controller : 
     requestmapping -> "products/"
     post : "/create"
     get : /
     post : "/edit"
     get : "/edit/{id}"
     delete : "delete/{id}"
- service
- repository
- model

model : 

- Product : id, name et price

pom : 

- jpa data
- thymeleaf
- h2
- spring web
- lombock


thymeleaf / templates : 

- create-product.html
- edit-product.html
- products.html