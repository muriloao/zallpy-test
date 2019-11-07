/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.dto;

import br.com.muriloao.zallpy.enuns.Sex;
import br.com.muriloao.zallpy.models.Customer;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author muriloao
 */
public class CustomerDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Informe o nome")
    private String name;

    @CPF
    @NotEmpty(message = "Informe o CPF")
    private String cpf;

    @NotNull(message = "Informe o sexo")
    private Sex sex;

    public Customer toModel() {
        return new Customer(this.id, this.name, this.cpf, this.sex);
    }

    @Override
    public String toString() {
        return "CustomerDTO (name: " + this.name + ", cpf:" + this.cpf + ", sex: " + this.sex + ")";
    }

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String cpf, Sex sexo) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.sex = sexo;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDTO other = (CustomerDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
