package com.nbchina.ws.api.entinfo.baseinfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.8
 * 2017-11-23T10:23:51.573+08:00
 * Generated source version: 2.7.8
 * 
 */
@WebService(targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "BaseInterface")
@XmlSeeAlso({ObjectFactory.class})
public interface BaseInterface {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectByParentId", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectByParentId")
    @WebMethod
    @ResponseWrapper(localName = "selectByParentIdResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectByParentIdResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectByParentId(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findIndexByEnterpriseName", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.FindIndexByEnterpriseName")
    @WebMethod
    @ResponseWrapper(localName = "findIndexByEnterpriseNameResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.FindIndexByEnterpriseNameResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult findIndexByEnterpriseName(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectCityInfo", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectCityInfo")
    @WebMethod
    @ResponseWrapper(localName = "selectCityInfoResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectCityInfoResponse")
    public java.util.List<com.nbchina.ws.api.entinfo.baseinfo.EntSpread> selectCityInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectByPrimaryKey", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectByPrimaryKey")
    @WebMethod
    @ResponseWrapper(localName = "selectByPrimaryKeyResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectByPrimaryKeyResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.Enterprise selectByPrimaryKey(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectProvinceInfo", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectProvinceInfo")
    @WebMethod
    @ResponseWrapper(localName = "selectProvinceInfoResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectProvinceInfoResponse")
    public java.util.List<com.nbchina.ws.api.entinfo.baseinfo.EntSpread> selectProvinceInfo();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "advanceSelect", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.AdvanceSelect")
    @WebMethod
    @ResponseWrapper(localName = "advanceSelectResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.AdvanceSelectResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult advanceSelect(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.String arg7
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectPartnerWithoutCapiList", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectPartnerWithoutCapiList")
    @WebMethod
    @ResponseWrapper(localName = "selectPartnerWithoutCapiListResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectPartnerWithoutCapiListResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectPartnerWithoutCapiList(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectEntsByScope", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntsByScope")
    @WebMethod
    @ResponseWrapper(localName = "selectEntsByScopeResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntsByScopeResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectEntsByScope(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectPartnerList", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectPartnerList")
    @WebMethod
    @ResponseWrapper(localName = "selectPartnerListResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectPartnerListResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectPartnerList(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectEntNumberByAreacode", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntNumberByAreacode")
    @WebMethod
    @ResponseWrapper(localName = "selectEntNumberByAreacodeResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntNumberByAreacodeResponse")
    public java.util.List<com.nbchina.ws.api.entinfo.baseinfo.CommonGridListEntityVo> selectEntNumberByAreacode(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectPartnerWithCapiByUuid", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectPartnerWithCapiByUuid")
    @WebMethod
    @ResponseWrapper(localName = "selectPartnerWithCapiByUuidResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectPartnerWithCapiByUuidResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.Partner selectPartnerWithCapiByUuid(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectEntByLegalPersonName", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntByLegalPersonName")
    @WebMethod
    @ResponseWrapper(localName = "selectEntByLegalPersonNameResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntByLegalPersonNameResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectEntByLegalPersonName(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectVIPMemberList", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectVIPMemberList")
    @WebMethod
    @ResponseWrapper(localName = "selectVIPMemberListResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectVIPMemberListResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectVIPMemberList(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectByPrimaryKeyWithChildEnts", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectByPrimaryKeyWithChildEnts")
    @WebMethod
    @ResponseWrapper(localName = "selectByPrimaryKeyWithChildEntsResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectByPrimaryKeyWithChildEntsResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.Enterprise selectByPrimaryKeyWithChildEnts(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "selectEntByPartnerName", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntByPartnerName")
    @WebMethod
    @ResponseWrapper(localName = "selectEntByPartnerNameResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.SelectEntByPartnerNameResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult selectEntByPartnerName(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "accurateSelect", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.AccurateSelect")
    @WebMethod
    @ResponseWrapper(localName = "accurateSelectResponse", targetNamespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", className = "com.nbchina.ws.api.entinfo.baseinfo.AccurateSelectResponse")
    public com.nbchina.ws.api.entinfo.baseinfo.QueryResult accurateSelect(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    );
}