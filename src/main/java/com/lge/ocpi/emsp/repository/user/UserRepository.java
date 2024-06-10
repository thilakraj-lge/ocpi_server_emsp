package com.lge.ocpi.emsp.repository.user;

import com.lge.ocpi.emsp.model.entity.autentication.UserInfo;
import com.lge.ocpi.emsp.repository.userHelpers.RefreshableCRUDRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserInfo, Long> {

   public UserInfo findByUsername(String username);
   UserInfo findFirstById(Long id);

}
