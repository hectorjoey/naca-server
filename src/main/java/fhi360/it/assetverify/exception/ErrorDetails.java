

package fhi360.it.assetverify.exception;

import java.util.Date;

public class ErrorDetails
{
    private final Date timestamp;
    private final String message;
    private final String details;
    
    public ErrorDetails(final Date timestamp, final String message, final String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getDetails() {
        return this.details;
    }
}
