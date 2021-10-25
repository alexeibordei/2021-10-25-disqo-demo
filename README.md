# NotesApp

### Setup

Install jdk 8, maven 3.8, springboot 2.5.5, MySQL 8.0.26

### Create DB

    sudo mysql

Enter commands:

    CREATE DATABASE DISQO_DEMO_DB;
    CREATE USER 'disqo_user'@'%' IDENTIFIED WITH mysql_native_password BY 'test';
    GRANT ALL ON DISQO_DEMO_DB.* TO 'disqo_user'@'%';
    exit 

Login to MySQL with created user:

    mysql -u disqo_user -p

and use created DB:

    use DISQO_DEMO_DB;
Create DB schema:

    source db/schema.sql;

and init with test data:

    source db/data.sql;

### Running application

    mvn clean package
    java -jar target/NotesApp-0.0.1-SNAPSHOT.jar

### Testing

Admin can see all users:

    curl -i --user admin@disqo.com:admin http://localhost:8080/users

Gives 403 for other users, like

    curl -i --user alex@disqo.com:alex http://localhost:8080/users

User can see assigned notes:

    curl -i --user alex@disqo.com:alex http://localhost:8080/notes

Create:

    curl --user alex@disqo.com:alex -X POST -H "Content-Type: application/json" -d '{"title":"Note created from REST","content":"This is just note"}' http://localhost:8080/notes/save

Update using valid note id:

    curl -i --user alex@disqo.com:alex -X POST -H "Content-Type: application/json" -d '{"title":"Note created from REST and updated","content":"This is note. Updated."}' http://localhost:8080/notes/update/{id}

And delete by valid id:

    curl -i --user alex@disqo.com:alex -X DELETE http://localhost:8080/notes/{id}
