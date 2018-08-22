/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.salesdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.salesdb.service.SalesdbQueryExecutorService;
import com.salesdb.models.query.*;

@RestController(value = "Salesdb.QueryExecutionController")
@RequestMapping("/salesdb/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private SalesdbQueryExecutorService queryService;

    @RequestMapping(value = "/queries/totalLeads", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "totalLeads")
    public Page<TotalLeadsResponse> executeTotalLeads(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: totalLeads");
        Page<TotalLeadsResponse> _result = queryService.executeTotalLeads(pageable);
        LOGGER.debug("got the result for named query: totalLeads, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query totalLeads")
    @RequestMapping(value = "/queries/totalLeads/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTotalLeads(@PathVariable("exportType") ExportType exportType, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: totalLeads");

        return queryService.exportTotalLeads(exportType, pageable);
    }

    @RequestMapping(value = "/queries/salesByReps", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "salesByReps")
    public Page<SalesByRepsResponse> executeSalesByReps(@RequestParam(value = "channel") Integer channel, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: salesByReps");
        Page<SalesByRepsResponse> _result = queryService.executeSalesByReps(channel, pageable);
        LOGGER.debug("got the result for named query: salesByReps, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query salesByReps")
    @RequestMapping(value = "/queries/salesByReps/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSalesByReps(@PathVariable("exportType") ExportType exportType, @RequestParam(value = "channel") Integer channel, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: salesByReps");

        return queryService.exportSalesByReps(exportType, channel, pageable);
    }

    @RequestMapping(value = "/queries/topTrendingProducts", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "returns the top trending products of the week")
    public Page<TopTrendingProductsResponse> executeTopTrendingProducts(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: topTrendingProducts");
        Page<TopTrendingProductsResponse> _result = queryService.executeTopTrendingProducts(pageable);
        LOGGER.debug("got the result for named query: topTrendingProducts, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query topTrendingProducts")
    @RequestMapping(value = "/queries/topTrendingProducts/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTopTrendingProducts(@PathVariable("exportType") ExportType exportType, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: topTrendingProducts");

        return queryService.exportTopTrendingProducts(exportType, pageable);
    }

    @RequestMapping(value = "/queries/totalSalesAndRevenue", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "totalSalesAndRevenue")
    public Page<TotalSalesAndRevenueResponse> executeTotalSalesAndRevenue(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: totalSalesAndRevenue");
        Page<TotalSalesAndRevenueResponse> _result = queryService.executeTotalSalesAndRevenue(pageable);
        LOGGER.debug("got the result for named query: totalSalesAndRevenue, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query totalSalesAndRevenue")
    @RequestMapping(value = "/queries/totalSalesAndRevenue/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTotalSalesAndRevenue(@PathVariable("exportType") ExportType exportType, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: totalSalesAndRevenue");

        return queryService.exportTotalSalesAndRevenue(exportType, pageable);
    }

}


