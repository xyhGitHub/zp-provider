package com.four.model;
  
import java.io.Serializable;  
import java.util.List;  
  
import lombok.Data;  
/**  
* @ClassName: User  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author jiayq 
* @date 2016年9月5日 下午2:31:58  
*   
*/  
@SuppressWarnings("serial")  
@Data  
public class User implements Serializable {  
    private Long id;  
    private String username;// 用户名  
    private String workNo;// 工作编号  
    private String salt;// 盐(密码安全)  
    private String password;// 密码  
    private Integer age;// 年龄  
    private String state;// 状态  
    private Long orgId;  
    private String pic;  
    private String phone;  
    private String address;  
    private String email;  
    private Integer percent;  
    /** 
     * @Title: getCredentialsSalt 
     * @Description: salt = salt + username 
     * @param @return 设定文件 
     * @return String 返回类型 
     * @author jiayq 
     * @throws 
     */  
    public String getCredentialsSalt() {  
        return username + salt;  
    }  
      
}  