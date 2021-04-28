package com.dummyblog.app.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {
	
private Long id;
    
	@NotEmpty(message = "Name is required!")
	private String name;
	
	@NotEmpty(message = "Password is required!")
	private String password;
	
	@NotNull(message = "Age is required!")
	private Integer age;
	
	@NotEmpty(message = "Email is required!")
	private String email;
	
	public UserDto() {
		
	}

    public UserDto(Long id, @NotEmpty(message = "Name is required!") String name,
            @NotEmpty(message = "Password is required!") String password,
            @NotNull(message = "Age is required!") Integer age,
            @NotEmpty(message = "Email is required!") String email) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDto other = (UserDto) obj;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", email=" + email
                + "]";
    }

}
