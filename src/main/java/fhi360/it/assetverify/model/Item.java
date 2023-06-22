package fhi360.it.assetverify.model;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "items")
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private String description;
    private String quantity;
    private String requestedBy;
    private String requesterEmail;
    private String status;
    private String condition;
    private String states;
    private String location;
    private String authorizedBy;
    
    public long getId() {
        return this.id;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getQuantity() {
        return this.quantity;
    }
    
    public String getRequestedBy() {
        return this.requestedBy;
    }
    
    public String getRequesterEmail() {
        return this.requesterEmail;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getCondition() {
        return this.condition;
    }
    
    public String getStates() {
        return this.states;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public String getAuthorizedBy() {
        return this.authorizedBy;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public void setDate(final String date) {
        this.date = date;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }
    
    public void setRequestedBy(final String requestedBy) {
        this.requestedBy = requestedBy;
    }
    
    public void setRequesterEmail(final String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setCondition(final String condition) {
        this.condition = condition;
    }
    
    public void setStates(final String states) {
        this.states = states;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public void setAuthorizedBy(final String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        final Item other = (Item)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        Label_0079: {
            if (this$date == null) {
                if (other$date == null) {
                    break Label_0079;
                }
            }
            else if (this$date.equals(other$date)) {
                break Label_0079;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        Label_0116: {
            if (this$description == null) {
                if (other$description == null) {
                    break Label_0116;
                }
            }
            else if (this$description.equals(other$description)) {
                break Label_0116;
            }
            return false;
        }
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        Label_0153: {
            if (this$quantity == null) {
                if (other$quantity == null) {
                    break Label_0153;
                }
            }
            else if (this$quantity.equals(other$quantity)) {
                break Label_0153;
            }
            return false;
        }
        final Object this$requestedBy = this.getRequestedBy();
        final Object other$requestedBy = other.getRequestedBy();
        Label_0190: {
            if (this$requestedBy == null) {
                if (other$requestedBy == null) {
                    break Label_0190;
                }
            }
            else if (this$requestedBy.equals(other$requestedBy)) {
                break Label_0190;
            }
            return false;
        }
        final Object this$requesterEmail = this.getRequesterEmail();
        final Object other$requesterEmail = other.getRequesterEmail();
        Label_0227: {
            if (this$requesterEmail == null) {
                if (other$requesterEmail == null) {
                    break Label_0227;
                }
            }
            else if (this$requesterEmail.equals(other$requesterEmail)) {
                break Label_0227;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0264: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0264;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0264;
            }
            return false;
        }
        final Object this$condition = this.getCondition();
        final Object other$condition = other.getCondition();
        Label_0301: {
            if (this$condition == null) {
                if (other$condition == null) {
                    break Label_0301;
                }
            }
            else if (this$condition.equals(other$condition)) {
                break Label_0301;
            }
            return false;
        }
        final Object this$states = this.getStates();
        final Object other$states = other.getStates();
        Label_0338: {
            if (this$states == null) {
                if (other$states == null) {
                    break Label_0338;
                }
            }
            else if (this$states.equals(other$states)) {
                break Label_0338;
            }
            return false;
        }
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        Label_0375: {
            if (this$location == null) {
                if (other$location == null) {
                    break Label_0375;
                }
            }
            else if (this$location.equals(other$location)) {
                break Label_0375;
            }
            return false;
        }
        final Object this$authorizedBy = this.getAuthorizedBy();
        final Object other$authorizedBy = other.getAuthorizedBy();
        if (this$authorizedBy == null) {
            return other$authorizedBy == null;
        }
        else return this$authorizedBy.equals(other$authorizedBy);
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Item;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        final Object $date = this.getDate();
        result = result * 59 + (($date == null) ? 43 : $date.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * 59 + (($quantity == null) ? 43 : $quantity.hashCode());
        final Object $requestedBy = this.getRequestedBy();
        result = result * 59 + (($requestedBy == null) ? 43 : $requestedBy.hashCode());
        final Object $requesterEmail = this.getRequesterEmail();
        result = result * 59 + (($requesterEmail == null) ? 43 : $requesterEmail.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $condition = this.getCondition();
        result = result * 59 + (($condition == null) ? 43 : $condition.hashCode());
        final Object $states = this.getStates();
        result = result * 59 + (($states == null) ? 43 : $states.hashCode());
        final Object $location = this.getLocation();
        result = result * 59 + (($location == null) ? 43 : $location.hashCode());
        final Object $authorizedBy = this.getAuthorizedBy();
        result = result * 59 + (($authorizedBy == null) ? 43 : $authorizedBy.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Item(id=" + this.getId() + ", date=" + this.getDate() + ", description=" + this.getDescription() + ", quantity=" + this.getQuantity() + ", requestedBy=" + this.getRequestedBy() + ", requesterEmail=" + this.getRequesterEmail() + ", status=" + this.getStatus() + ", condition=" + this.getCondition() + ", states=" + this.getStates() + ", location=" + this.getLocation() + ", authorizedBy=" + this.getAuthorizedBy() + ")";
    }
    
    public Item() {
    }
    
    public Item(final long id, final String date, final String description, final String quantity, final String requestedBy, final String requesterEmail, final String status, final String condition, final String states, final String location, final String authorizedBy) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.quantity = quantity;
        this.requestedBy = requestedBy;
        this.requesterEmail = requesterEmail;
        this.status = status;
        this.condition = condition;
        this.states = states;
        this.location = location;
        this.authorizedBy = authorizedBy;
    }
}
