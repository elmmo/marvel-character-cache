# marvel-character-cache
A content cache of characters from the Marvel universe

## Design/Testing Decisions 

1. **Hash Testing**  
There's a [debate](https://stackoverflow.com/questions/34571/how-do-i-test-a-private-function-or-a-class-that-has-private-methods-fields-or?page=1&tab=votes#tab-top) on whether private methods should be tested. I agree that testing private methods can sometimes be a code smell that the class is too complicated, but given that this hashing function is pretty simple and only used for authorization to Marvel's endpoints, I felt that it was sufficiently important enough and small enough to fit the criteria for unit testing. 