package com.example.innovativebanking.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vault")
public class VaultModel {

    @PrimaryKey(autoGenerate = true)
    private int vaultId;
    @ColumnInfo(name = "vault_name")
    private String vaultName;
    @ColumnInfo(name = "vault_description")
    private String vaultDescription;
    @ColumnInfo(name = "vault_target")
    private float vaultTarget;
    @ColumnInfo(name = "vault_balance")
    private float vaultBalance;
    @ColumnInfo(name = "userId")
    private int userId;


    public VaultModel(String vaultName, String vaultDescription, float vaultTarget, float vaultBalance, int userId) {
        this.vaultName = vaultName;
        this.vaultDescription = vaultDescription;
        this.vaultTarget = vaultTarget;
        this.vaultBalance = vaultBalance;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVaultId() {
        return vaultId;
    }

    public void setVaultId(int vaultId) {
        this.vaultId = vaultId;
    }

    public String getVaultName() {
        return vaultName;
    }

    public void setVaultName(String vaultName) {
        this.vaultName = vaultName;
    }

    public String getVaultDescription() {
        return vaultDescription;
    }

    public void setVaultDescription(String vaultDescription) {
        this.vaultDescription = vaultDescription;
    }

    public float getVaultTarget() {
        return vaultTarget;
    }

    public void setVaultTarget(float vaultTarget) {
        this.vaultTarget = vaultTarget;
    }

    public float getVaultBalance() {
        return vaultBalance;
    }

    public void setVaultBalance(float vaultBalance) {
        this.vaultBalance = vaultBalance;
    }

    @Override
    public String toString() {
        return "VaultModel{" +
                "vaultId=" + vaultId +
                ", vaultName='" + vaultName + '\'' +
                ", vaultDescription='" + vaultDescription + '\'' +
                ", vaultTarget=" + vaultTarget +
                ", vaultBalance=" + vaultBalance +
                '}';
    }
}
