// 
// Decompiled by Procyon v0.5.36
// 

package fhi360.it.assetverify.response;

public class ApiResponse<T>
{
    private int status;
    private String message;
    private T result;
    private long id;
    private String userType;
    private String email;
    private String firstname;
    private String lastname;
    private String states;
    private String project;
    
    public int getStatus() {
        return this.status;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public T getResult() {
        return this.result;
    }
    
    public long getId() {
        return this.id;
    }
    
    public String getUserType() {
        return this.userType;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getFirstname() {
        return this.firstname;
    }
    
    public String getLastname() {
        return this.lastname;
    }
    
    public String getStates() {
        return this.states;
    }
    
    public String getProject() {
        return this.project;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public void setResult(final T result) {
        this.result = result;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setUserType(final String userType) {
        this.userType = userType;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }
    
    public void setStates(final String states) {
        this.states = states;
    }
    
    public void setProject(final String project) {
        this.project = project;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ApiResponse)) {
            return false;
        }
        final ApiResponse<?> other = (ApiResponse<?>)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        Label_0092: {
            if (this$message == null) {
                if (other$message == null) {
                    break Label_0092;
                }
            }
            else if (this$message.equals(other$message)) {
                break Label_0092;
            }
            return false;
        }
        final Object this$result = this.getResult();
        final Object other$result = other.getResult();
        Label_0129: {
            if (this$result == null) {
                if (other$result == null) {
                    break Label_0129;
                }
            }
            else if (this$result.equals(other$result)) {
                break Label_0129;
            }
            return false;
        }
        final Object this$userType = this.getUserType();
        final Object other$userType = other.getUserType();
        Label_0166: {
            if (this$userType == null) {
                if (other$userType == null) {
                    break Label_0166;
                }
            }
            else if (this$userType.equals(other$userType)) {
                break Label_0166;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0203: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0203;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0203;
            }
            return false;
        }
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        Label_0240: {
            if (this$firstname == null) {
                if (other$firstname == null) {
                    break Label_0240;
                }
            }
            else if (this$firstname.equals(other$firstname)) {
                break Label_0240;
            }
            return false;
        }
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        Label_0277: {
            if (this$lastname == null) {
                if (other$lastname == null) {
                    break Label_0277;
                }
            }
            else if (this$lastname.equals(other$lastname)) {
                break Label_0277;
            }
            return false;
        }
        final Object this$states = this.getStates();
        final Object other$states = other.getStates();
        Label_0314: {
            if (this$states == null) {
                if (other$states == null) {
                    break Label_0314;
                }
            }
            else if (this$states.equals(other$states)) {
                break Label_0314;
            }
            return false;
        }
        final Object this$project = this.getProject();
        final Object other$project = other.getProject();
        if (this$project == null) {
            return other$project == null;
        }
        else return this$project.equals(other$project);
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ApiResponse;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getStatus();
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final Object $message = this.getMessage();
        result = result * 59 + (($message == null) ? 43 : $message.hashCode());
        final Object $result = this.getResult();
        result = result * 59 + (($result == null) ? 43 : $result.hashCode());
        final Object $userType = this.getUserType();
        result = result * 59 + (($userType == null) ? 43 : $userType.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $firstname = this.getFirstname();
        result = result * 59 + (($firstname == null) ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * 59 + (($lastname == null) ? 43 : $lastname.hashCode());
        final Object $states = this.getStates();
        result = result * 59 + (($states == null) ? 43 : $states.hashCode());
        final Object $project = this.getProject();
        result = result * 59 + (($project == null) ? 43 : $project.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ApiResponse(status=" + this.getStatus() + ", message=" + this.getMessage() + ", result=" + this.getResult() + ", id=" + this.getId() + ", userType=" + this.getUserType() + ", email=" + this.getEmail() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", states=" + this.getStates() + ", project=" + this.getProject() + ")";
    }
    
    public ApiResponse(final int status, final String message, final T result, final long id, final String userType, final String email, final String firstname, final String lastname, final String states, final String project) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.id = id;
        this.userType = userType;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.states = states;
        this.project = project;
    }
}
