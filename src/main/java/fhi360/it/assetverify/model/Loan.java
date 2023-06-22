
package fhi360.it.assetverify.model;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "loan")
public class Loan
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String equipmentType;
    private String model;
    private String location;
    private String serialnumber;
    private String date;
    private String expiryDate;
    private String acknowledgedBy;
    private String status;
    
    public Loan(final Long id, final String firstname, final String lastname, final String email, final String equipmentType, final String model, final String location, final String serialnumber, final String date, final String expiryDate, final String acknowledgedBy, final String status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.equipmentType = equipmentType;
        this.model = model;
        this.location = location;
        this.serialnumber = serialnumber;
        this.date = date;
        this.expiryDate = expiryDate;
        this.acknowledgedBy = acknowledgedBy;
        this.status = status;
    }
    
    public Loan() {
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getFirstname() {
        return this.firstname;
    }
    
    public String getLastname() {
        return this.lastname;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getEquipmentType() {
        return this.equipmentType;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getSerialnumber() {
        return this.serialnumber;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getExpiryDate() {
        return this.expiryDate;
    }
    
    public String getAcknowledgedBy() {
        return this.acknowledgedBy;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setEquipmentType(final String equipmentType) {
        this.equipmentType = equipmentType;
    }
    
    public void setModel(final String model) {
        this.model = model;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public void setSerialnumber(final String serialnumber) {
        this.serialnumber = serialnumber;
    }
    
    public void setDate(final String date) {
        this.date = date;
    }
    
    public void setExpiryDate(final String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setAcknowledgedBy(final String acknowledgedBy) {
        this.acknowledgedBy = acknowledgedBy;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Loan)) {
            return false;
        }
        final Loan other = (Loan)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        Label_0102: {
            if (this$firstname == null) {
                if (other$firstname == null) {
                    break Label_0102;
                }
            }
            else if (this$firstname.equals(other$firstname)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        Label_0139: {
            if (this$lastname == null) {
                if (other$lastname == null) {
                    break Label_0139;
                }
            }
            else if (this$lastname.equals(other$lastname)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        Label_0176: {
            if (this$email == null) {
                if (other$email == null) {
                    break Label_0176;
                }
            }
            else if (this$email.equals(other$email)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$equipmentType = this.getEquipmentType();
        final Object other$equipmentType = other.getEquipmentType();
        Label_0213: {
            if (this$equipmentType == null) {
                if (other$equipmentType == null) {
                    break Label_0213;
                }
            }
            else if (this$equipmentType.equals(other$equipmentType)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$model = this.getModel();
        final Object other$model = other.getModel();
        Label_0250: {
            if (this$model == null) {
                if (other$model == null) {
                    break Label_0250;
                }
            }
            else if (this$model.equals(other$model)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        Label_0287: {
            if (this$location == null) {
                if (other$location == null) {
                    break Label_0287;
                }
            }
            else if (this$location.equals(other$location)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$serialnumber = this.getSerialnumber();
        final Object other$serialnumber = other.getSerialnumber();
        Label_0324: {
            if (this$serialnumber == null) {
                if (other$serialnumber == null) {
                    break Label_0324;
                }
            }
            else if (this$serialnumber.equals(other$serialnumber)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        Label_0361: {
            if (this$date == null) {
                if (other$date == null) {
                    break Label_0361;
                }
            }
            else if (this$date.equals(other$date)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$expiryDate = this.getExpiryDate();
        final Object other$expiryDate = other.getExpiryDate();
        Label_0398: {
            if (this$expiryDate == null) {
                if (other$expiryDate == null) {
                    break Label_0398;
                }
            }
            else if (this$expiryDate.equals(other$expiryDate)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$acknowledgedBy = this.getAcknowledgedBy();
        final Object other$acknowledgedBy = other.getAcknowledgedBy();
        Label_0435: {
            if (this$acknowledgedBy == null) {
                if (other$acknowledgedBy == null) {
                    break Label_0435;
                }
            }
            else if (this$acknowledgedBy.equals(other$acknowledgedBy)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null) {
            return other$status == null;
        }
        else return this$status.equals(other$status);
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Loan;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $firstname = this.getFirstname();
        result = result * 59 + (($firstname == null) ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * 59 + (($lastname == null) ? 43 : $lastname.hashCode());
        final Object $email = this.getEmail();
        result = result * 59 + (($email == null) ? 43 : $email.hashCode());
        final Object $equipmentType = this.getEquipmentType();
        result = result * 59 + (($equipmentType == null) ? 43 : $equipmentType.hashCode());
        final Object $model = this.getModel();
        result = result * 59 + (($model == null) ? 43 : $model.hashCode());
        final Object $location = this.getLocation();
        result = result * 59 + (($location == null) ? 43 : $location.hashCode());
        final Object $serialnumber = this.getSerialnumber();
        result = result * 59 + (($serialnumber == null) ? 43 : $serialnumber.hashCode());
        final Object $date = this.getDate();
        result = result * 59 + (($date == null) ? 43 : $date.hashCode());
        final Object $expiryDate = this.getExpiryDate();
        result = result * 59 + (($expiryDate == null) ? 43 : $expiryDate.hashCode());
        final Object $acknowledgedBy = this.getAcknowledgedBy();
        result = result * 59 + (($acknowledgedBy == null) ? 43 : $acknowledgedBy.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Loan(id=" + this.getId() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", email=" + this.getEmail() + ", equipmentType=" + this.getEquipmentType() + ", model=" + this.getModel() + ", location=" + this.getLocation() + ", serialnumber=" + this.getSerialnumber() + ", date=" + this.getDate() + ", expiryDate=" + this.getExpiryDate() + ", acknowledgedBy=" + this.getAcknowledgedBy() + ", status=" + this.getStatus() + ")";
    }
}
