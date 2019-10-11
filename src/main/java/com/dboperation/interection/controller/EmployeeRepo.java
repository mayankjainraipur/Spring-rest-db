/*
 *  Copyright Mocana Corp 2018. All Rights Reserved.
 *  Proprietary and Confidential Material.
 *
 */
package com.dboperation.interection.controller;

import com.dboperation.interection.dao.EmpDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
interface EmployeeRepo extends JpaRepository<EmpDAO, Integer>,
        JpaSpecificationExecutor<EmpDAO> {

    EmpDAO findByName(String name);

}

