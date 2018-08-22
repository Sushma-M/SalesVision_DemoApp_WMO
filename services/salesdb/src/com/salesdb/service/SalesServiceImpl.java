/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.salesdb.Sales;


/**
 * ServiceImpl object for domain model class Sales.
 *
 * @see Sales
 */
@Service("salesdb.SalesService")
@Validated
public class SalesServiceImpl implements SalesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesServiceImpl.class);


    @Autowired
    @Qualifier("salesdb.SalesDao")
    private WMGenericDao<Sales, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Sales, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "salesdbTransactionManager")
    @Override
	public Sales create(Sales salesInstance) {
        LOGGER.debug("Creating a new Sales with information: {}", salesInstance);

        Sales salesInstanceCreated = this.wmGenericDao.create(salesInstance);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(salesInstanceCreated);
    }

	@Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public Sales getById(Integer salesId) {
        LOGGER.debug("Finding Sales by id: {}", salesId);
        return this.wmGenericDao.findById(salesId);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public Sales findById(Integer salesId) {
        LOGGER.debug("Finding Sales by id: {}", salesId);
        try {
            return this.wmGenericDao.findById(salesId);
        } catch(EntityNotFoundException ex) {
            LOGGER.debug("No Sales found with id: {}", salesId, ex);
            return null;
        }
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "salesdbTransactionManager")
	@Override
	public Sales update(Sales salesInstance) {
        LOGGER.debug("Updating Sales with information: {}", salesInstance);

        this.wmGenericDao.update(salesInstance);
        this.wmGenericDao.refresh(salesInstance);

        return salesInstance;
    }

    @Transactional(value = "salesdbTransactionManager")
	@Override
	public Sales delete(Integer salesId) {
        LOGGER.debug("Deleting Sales with id: {}", salesId);
        Sales deleted = this.wmGenericDao.findById(salesId);
        if (deleted == null) {
            LOGGER.debug("No Sales found with id: {}", salesId);
            throw new EntityNotFoundException(String.valueOf(salesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "salesdbTransactionManager")
	@Override
	public void delete(Sales salesInstance) {
        LOGGER.debug("Deleting Sales with {}", salesInstance);
        this.wmGenericDao.delete(salesInstance);
    }

	@Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public Page<Sales> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Sales");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Page<Sales> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Sales");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service salesdb for table Sales to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "salesdbTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

