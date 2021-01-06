package com.example.innovativebanking.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.innovativebanking.models.VaultModel;

import java.util.List;

@Dao
public interface VaultDAO {

    @Query("SELECT * FROM vault WHERE vaultId = (:vaultId)")
    VaultModel getVaultById(int vaultId);

    @Query("SELECT * FROM vault WHERE userId = (:userId)")
    List<VaultModel> getAllVaultsByUserId(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addVault(VaultModel vaultModel);

    @Update
    void updateVault(VaultModel vaultModel);

    @Delete
    void deleteVault(VaultModel vaultModel);


}
