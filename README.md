bayes-dota
==========

This is the [task](TASK.md).

## Additional information
- I didn't finish the damage part of the task due to lack of time.
## Building & running
Build and run the application either from your favorite IDE or from the command line with command.
`mvn spring-boot:run`
## Ingestion
When you ingest with the command
`curl -i -H "Content-Type: text/plain" -d @combatlog_1.txt http://localhost:8080/api/match` pay attention that match 
id will be returned in the response as a next sequence number in the database. Meaning match_id may differ from the value `1`.

After ingestion you can check the data in the db under http://localhost:8080/h2-console/