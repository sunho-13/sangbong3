package com.softagape.myjpa.phonebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softagape.myjpa.category.CategoryEntity;
import com.softagape.myjpa.category.ICategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
public class PhoneBookRequest implements IPhoneBook {
    @JsonIgnore
    private Long id;

    @NotBlank
    @Size(min = 2, max = 12)
    private String name;

    @NotBlank
    private CategoryEntity category;

    @NotBlank
    @Size(min = 10, max = 20)
    private String phoneNumber;

    @Size(min = 0, max = 200)
    private String email;

    @Override
    public void setCategory(ICategory category) {
        if ( category == null ) {
            return;
        }
        CategoryEntity entity = new CategoryEntity();
        entity.copyFields(category);
        this.category = entity;
    }
}
