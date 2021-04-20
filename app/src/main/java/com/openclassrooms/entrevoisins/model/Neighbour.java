package com.openclassrooms.entrevoisins.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Serializable {

    /**
     * Identifier
     */
    private long id;

    /**
     * Full name
     */
    private String name;

    /**
     * Avatar
     */
    private String avatarUrl;

    /**
     * Adress
     */
    private String address;

    /**
     * Phone number
     */
    private String phoneNumber;

    /**
     * Web link
     */
    private String weblink;

    /**
     * About me
     */
    private String aboutMe;

    /**
     * Favoris me
     */
    private Boolean isFavoris;

    /**
     * Neighbour constructor
     *
     * @param id          long number to identify neighbour
     * @param name        sentence string for name
     * @param avatarUrl   sentence string for Url of avatar
     * @param address     sentence string for user adresse
     * @param phoneNumber sentence string for user phone number
     * @param weblink     sentence string for user weblink
     * @param aboutMe     text string for neighbour paragraph
     * @param isFavoris   boolean for favorite status
     */
    public Neighbour(
            long id, String name, String avatarUrl, String address,
            String phoneNumber,String weblink, String aboutMe, Boolean isFavoris) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.weblink = weblink;
        this.aboutMe = aboutMe;
        this.isFavoris = isFavoris;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets avatar url.
     *
     * @return the avatar url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets avatar url.
     *
     * @param avatarUrl the avatar url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets weblink.
     *
     * @return the weblink
     */
    public String getWeblink() {
        return weblink;
    }

    /**
     * Sets weblink.
     *
     * @param weblink the weblink
     */
    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    /**
     * Gets about me.
     *
     * @return the about me text
     */
    public String getAboutMe() {
        return aboutMe;
    }

    /**
     * Sets about me.
     *
     * @param aboutMe the about me text
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    /**
     * Gets favoris.
     *
     * @return the favoris
     */
    public Boolean isFavoris() {
        return isFavoris;
    }

    /**
     * Sets favoris.
     *
     * @param favoris the favoris
     */
    public void setFavoris(Boolean favoris) {
        this.isFavoris = favoris;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
