package fhi360.it.assetverify.model;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "visitor")
public class Visitor
{
    @Id
    @GeneratedValue
    private int id;
    private String ip;
    private String method;
    private String url;
    private String page;
    private String userAgent;
    private String device;
    private LocalDateTime loggedTime;
    private boolean uniqueVisit;
    private int status;
    
    public int getId() {
        return this.id;
    }
    
    public String getIp() {
        return this.ip;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public String getPage() {
        return this.page;
    }
    
    public String getUserAgent() {
        return this.userAgent;
    }
    
    public String getDevice() {
        return this.device;
    }
    
    public LocalDateTime getLoggedTime() {
        return this.loggedTime;
    }
    
    public boolean isUniqueVisit() {
        return this.uniqueVisit;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public void setIp(final String ip) {
        this.ip = ip;
    }
    
    public void setMethod(final String method) {
        this.method = method;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public void setPage(final String page) {
        this.page = page;
    }
    
    public void setUserAgent(final String userAgent) {
        this.userAgent = userAgent;
    }
    
    public void setDevice(final String device) {
        this.device = device;
    }
    
    public void setLoggedTime(final LocalDateTime loggedTime) {
        this.loggedTime = loggedTime;
    }
    
    public void setUniqueVisit(final boolean uniqueVisit) {
        this.uniqueVisit = uniqueVisit;
    }
    
    public void setStatus(final int status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Visitor)) {
            return false;
        }
        final Visitor other = (Visitor)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getId() != other.getId()) {
            return false;
        }
        if (this.isUniqueVisit() != other.isUniqueVisit()) {
            return false;
        }
        if (this.getStatus() != other.getStatus()) {
            return false;
        }
        final Object this$ip = this.getIp();
        final Object other$ip = other.getIp();
        Label_0104: {
            if (this$ip == null) {
                if (other$ip == null) {
                    break Label_0104;
                }
            }
            else if (this$ip.equals(other$ip)) {
                break Label_0104;
            }
            return false;
        }
        final Object this$method = this.getMethod();
        final Object other$method = other.getMethod();
        Label_0141: {
            if (this$method == null) {
                if (other$method == null) {
                    break Label_0141;
                }
            }
            else if (this$method.equals(other$method)) {
                break Label_0141;
            }
            return false;
        }
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        Label_0178: {
            if (this$url == null) {
                if (other$url == null) {
                    break Label_0178;
                }
            }
            else if (this$url.equals(other$url)) {
                break Label_0178;
            }
            return false;
        }
        final Object this$page = this.getPage();
        final Object other$page = other.getPage();
        Label_0215: {
            if (this$page == null) {
                if (other$page == null) {
                    break Label_0215;
                }
            }
            else if (this$page.equals(other$page)) {
                break Label_0215;
            }
            return false;
        }
        final Object this$userAgent = this.getUserAgent();
        final Object other$userAgent = other.getUserAgent();
        Label_0252: {
            if (this$userAgent == null) {
                if (other$userAgent == null) {
                    break Label_0252;
                }
            }
            else if (this$userAgent.equals(other$userAgent)) {
                break Label_0252;
            }
            return false;
        }
        final Object this$device = this.getDevice();
        final Object other$device = other.getDevice();
        Label_0289: {
            if (this$device == null) {
                if (other$device == null) {
                    break Label_0289;
                }
            }
            else if (this$device.equals(other$device)) {
                break Label_0289;
            }
            return false;
        }
        final Object this$loggedTime = this.getLoggedTime();
        final Object other$loggedTime = other.getLoggedTime();
        if (this$loggedTime == null) {
            if (other$loggedTime == null) {
                return true;
            }
        }
        else if (this$loggedTime.equals(other$loggedTime)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof Visitor;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getId();
        result = result * 59 + (this.isUniqueVisit() ? 79 : 97);
        result = result * 59 + this.getStatus();
        final Object $ip = this.getIp();
        result = result * 59 + (($ip == null) ? 43 : $ip.hashCode());
        final Object $method = this.getMethod();
        result = result * 59 + (($method == null) ? 43 : $method.hashCode());
        final Object $url = this.getUrl();
        result = result * 59 + (($url == null) ? 43 : $url.hashCode());
        final Object $page = this.getPage();
        result = result * 59 + (($page == null) ? 43 : $page.hashCode());
        final Object $userAgent = this.getUserAgent();
        result = result * 59 + (($userAgent == null) ? 43 : $userAgent.hashCode());
        final Object $device = this.getDevice();
        result = result * 59 + (($device == null) ? 43 : $device.hashCode());
        final Object $loggedTime = this.getLoggedTime();
        result = result * 59 + (($loggedTime == null) ? 43 : $loggedTime.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "Visitor(id=" + this.getId() + ", ip=" + this.getIp() + ", method=" + this.getMethod() + ", url=" + this.getUrl() + ", page=" + this.getPage() + ", userAgent=" + this.getUserAgent() + ", device=" + this.getDevice() + ", loggedTime=" + this.getLoggedTime() + ", uniqueVisit=" + this.isUniqueVisit() + ", status=" + this.getStatus() + ")";
    }
    
    public Visitor() {
    }
    
    public Visitor(final int id, final String ip, final String method, final String url, final String page, final String userAgent, final String device, final LocalDateTime loggedTime, final boolean uniqueVisit, final int status) {
        this.id = id;
        this.ip = ip;
        this.method = method;
        this.url = url;
        this.page = page;
        this.userAgent = userAgent;
        this.device = device;
        this.loggedTime = loggedTime;
        this.uniqueVisit = uniqueVisit;
        this.status = status;
    }
}
