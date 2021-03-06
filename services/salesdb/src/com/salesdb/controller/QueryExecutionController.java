/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.salesdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.security.xss.XssDisable;
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

    @Autowired
	private ExportedFileManager exportedFileManager;

    @RequestMapping(value = "/queries/totalLeads", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "totalLeads")
    public Page<TotalLeadsResponse> executeTotalLeads(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: totalLeads");
        Page<TotalLeadsResponse> _result = queryService.executeTotalLeads(pageable);
        LOGGER.debug("got the result for named query: totalLeads, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query totalLeads")
    @RequestMapping(value = "/queries/totalLeads/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportTotalLeads(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: totalLeads");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "totalLeads";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportTotalLeads( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
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

    @ApiOperation(value = "Returns downloadable file url for query salesByReps")
    @RequestMapping(value = "/queries/salesByReps/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportSalesByReps(@RequestParam(value = "channel") Integer channel, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: salesByReps");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "salesByReps";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportSalesByReps(channel,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
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

    @ApiOperation(value = "Returns downloadable file url for query topTrendingProducts")
    @RequestMapping(value = "/queries/topTrendingProducts/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportTopTrendingProducts(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: topTrendingProducts");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "topTrendingProducts";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportTopTrendingProducts( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
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

    @ApiOperation(value = "Returns downloadable file url for query totalSalesAndRevenue")
    @RequestMapping(value = "/queries/totalSalesAndRevenue/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @XssDisable
    public StringWrapper exportTotalSalesAndRevenue(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: totalSalesAndRevenue");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "totalSalesAndRevenue";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportTotalSalesAndRevenue( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

}