package com.sogeti.entity;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by ROWAGEMA on 3-1-2017.
 */
@Entity
@Table( name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private byte[] salt;

    @Column(name = "validated")
    private boolean validated;

    @OneToOne(cascade=CascadeType.ALL)
    private UserRole userRole;

    public User() {
        this.validated = false;
        this.userRole = new UserRole();
    }

    public User(UserRole.UserRoleEnum ure) {
        this.userRole = new UserRole(ure);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public UserRole getUserRole() { return userRole; }

    public void setUserRole(UserRole userRole) { this.userRole = userRole; }

    public byte[] getSalt() { return salt; }

    public void setSalt(byte[] salt) { this.salt = salt; }

    public void hashPassword() throws NoSuchAlgorithmException, NoSuchProviderException {
        byte[] salt = this.createSalt();

        String securePassword = getSecurePassword(password, salt);

        this.password = securePassword;
        this.salt = salt;
    }
    private String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public boolean isPasswordMatch(String inputPassword) {
        //How to verify the hashed password with the new logged in password
        String regeneratedPasswordToVerify = getSecurePassword(inputPassword, salt);
        return regeneratedPasswordToVerify.equals(this.password);
    }

    //Add salt
    private byte[] createSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
