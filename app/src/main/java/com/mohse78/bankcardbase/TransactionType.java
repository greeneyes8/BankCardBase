package com.mohse78.bankcardbase;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table TransactionTypes.
 */
public class TransactionType {

    private Long id;
    private Boolean type;
    private String name;
    private Integer imageLogoId;
    private String description;

    public TransactionType() {
    }

    public TransactionType(Long id) {
        this.id = id;
    }

    public TransactionType(Long id, Boolean type, String name, Integer imageLogoId, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.imageLogoId = imageLogoId;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageLogoId() {
        return imageLogoId;
    }

    public void setImageLogoId(Integer imageLogoId) {
        this.imageLogoId = imageLogoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}