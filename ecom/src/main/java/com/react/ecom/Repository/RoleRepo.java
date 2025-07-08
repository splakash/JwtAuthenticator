package com.react.ecom.Repository;
import com.react.ecom.model.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<role,Long> {
    role findByRoleName(String role);
}
