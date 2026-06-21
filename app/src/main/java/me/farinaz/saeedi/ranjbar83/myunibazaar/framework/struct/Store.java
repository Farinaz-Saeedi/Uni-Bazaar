package me.farinaz.saeedi.ranjbar83.myunibazaar.framework.struct;

public class Store {
    private int idStore;
    private String name;
    private String image;
    private String type;
    private String ownerFirstName;
    private String ownerLastName;
    private Boolean isFraudulent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public Boolean getFraudulent() {
        return isFraudulent;
    }

    public void setFraudulent(Boolean fraudulent) {
        isFraudulent = fraudulent;
    }
}
