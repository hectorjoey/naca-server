
package fhi360.it.assetverify.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

//@Getter
//@Setter
@Data
public class UserLoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
