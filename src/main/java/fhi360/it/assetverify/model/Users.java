
package fhi360.it.assetverify.model;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "users")
public class Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "email")
    private String email;
    @Column(name = "department")
    private String department;
    @Column(name = "states")
    private String states;
    @Column(name = "project")
    private String project;
    @Column(name = "password")
    private String password;
    
    public long getId() {
        return this.id;
    }
    
    public String getFirstname() {
        return this.firstname;
    }
    
    public String getLastname() {
        return this.lastname;
    }
    
    public String getUserType() {
        return this.userType;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public String getStates() {
        return this.states;
    }
    
    public String getProject() {
        return this.project;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }
    
    public void setUserType(final String userType) {
        this.userType = userType;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setDepartment(final String department) {
        this.department = department;
    }
    
    public void setStates(final String states) {
        this.states = states;
    }
    
    public void setProject(final String project) {
        this.project = project;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Users)) {
            return false;
        }
        final Users other = (Users)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        Label_0079: {
            if (this$firstname == null) {
                if (other$firstname == null) {
                    break Label_0079;
                }
            }
            else if (this$firstname.equals(other$firstname)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        Label_0116: {
            if (this$lastname == null) {
                if (other$lastname == null) {
                    break Label_0116;
                }
            }
            else if (this$lastname.equals(other$lastname)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$userType = this.getUserType();
        final Object other$userType = other.getUserType();
        Label_0153: {
            if (this$userType == null) {
                if (other$userType == null) {
                    break Label_0153;
                }
            }
            else if (this$userType.equals(other$userType)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0190: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0190;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$department = this.getDepartment();
        final Object other$department = other.getDepartment();
        Label_0227: {
            if (this$department == null) {
                if (other$department == null) {
                    break Label_0227;
                }
            }
            else if (this$department.equals(other$department)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$states = this.getStates();
        final Object other$states = other.getStates();
        Label_0264: {
            if (this$states == null) {
                if (other$states == null) {
                    break Label_0264;
                }
            }
            else if (this$states.equals(other$states)) {
                break Label_0264;
            }
            return false;
        }
        final Object this$project = this.getProject();
        final Object other$project = other.getProject();
        Label_0301: {
            if (this$project == null) {
                if (other$project == null) {
                    break Label_0301;
                }
            }
            else if (this$project.equals(other$project)) {
                break Label_0301;
            }
            return false;
        }
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null) {
            return other$password == null;
        }
        else return this$password.equals(other$password);
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Users;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final Object $firstname = this.getFirstname();
        result = result * 59 + (($firstname == null) ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * 59 + (($lastname == null) ? 43 : $lastname.hashCode());
        final Object $userType = this.getUserType();
        result = result * 59 + (($userType == null) ? 43 : $userType.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $department = this.getDepartment();
        result = result * 59 + (($department == null) ? 43 : $department.hashCode());
        final Object $states = this.getStates();
        result = result * 59 + (($states == null) ? 43 : $states.hashCode());
        final Object $project = this.getProject();
        result = result * 59 + (($project == null) ? 43 : $project.hashCode());
        final Object $password = this.getPassword();
        result = result * 59 + (($password == null) ? 43 : $password.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Users(id=" + this.getId() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", userType=" + this.getUserType() + ", email=" + this.getEmail() + ", department=" + this.getDepartment() + ", states=" + this.getStates() + ", project=" + this.getProject() + ", password=" + this.getPassword() + ")";
    }
    
    public Users() {
    }
    
    public Users(final long id, final String firstname, final String lastname, final String userType, final String email, final String department, final String states, final String project, final String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userType = userType;
        this.email = email;
        this.department = department;
        this.states = states;
        this.project = project;
        this.password = password;
    }
}
