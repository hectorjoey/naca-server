

package fhi360.it.assetverify.exception;

import java.util.Date;

public class ErrorDetails
{
    private Date timestamp;
    private String message;
    private String details;
    
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
