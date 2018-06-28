
package com.nbchina.ws.api.entinfo.baseinfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nbchina.ws.api.entinfo.baseinfo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SelectCityInfoResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectCityInfoResponse");
    private final static QName _SelectEntsByScopeResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntsByScopeResponse");
    private final static QName _SelectProvinceInfo_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectProvinceInfo");
    private final static QName _SelectEntByPartnerName_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntByPartnerName");
    private final static QName _SelectEntNumberByAreacodeResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntNumberByAreacodeResponse");
    private final static QName _SelectByParentId_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectByParentId");
    private final static QName _SelectByPrimaryKeyResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectByPrimaryKeyResponse");
    private final static QName _SelectCityInfo_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectCityInfo");
    private final static QName _FindIndexByEnterpriseName_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "findIndexByEnterpriseName");
    private final static QName _SelectByPrimaryKeyWithChildEnts_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectByPrimaryKeyWithChildEnts");
    private final static QName _SelectPartnerWithCapiByUuid_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectPartnerWithCapiByUuid");
    private final static QName _AccurateSelect_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "accurateSelect");
    private final static QName _SelectPartnerWithoutCapiListResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectPartnerWithoutCapiListResponse");
    private final static QName _SelectPartnerWithoutCapiList_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectPartnerWithoutCapiList");
    private final static QName _SelectVIPMemberListResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectVIPMemberListResponse");
    private final static QName _SelectByPrimaryKey_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectByPrimaryKey");
    private final static QName _SelectEntByPartnerNameResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntByPartnerNameResponse");
    private final static QName _SelectPartnerList_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectPartnerList");
    private final static QName _SelectEntByLegalPersonName_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntByLegalPersonName");
    private final static QName _AccurateSelectResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "accurateSelectResponse");
    private final static QName _FindIndexByEnterpriseNameResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "findIndexByEnterpriseNameResponse");
    private final static QName _SelectByParentIdResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectByParentIdResponse");
    private final static QName _SelectPartnerWithCapiByUuidResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectPartnerWithCapiByUuidResponse");
    private final static QName _SelectEntByLegalPersonNameResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntByLegalPersonNameResponse");
    private final static QName _AdvanceSelectResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "advanceSelectResponse");
    private final static QName _SelectEntNumberByAreacode_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntNumberByAreacode");
    private final static QName _AdvanceSelect_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "advanceSelect");
    private final static QName _SelectEntsByScope_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectEntsByScope");
    private final static QName _SelectProvinceInfoResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectProvinceInfoResponse");
    private final static QName _SelectByPrimaryKeyWithChildEntsResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectByPrimaryKeyWithChildEntsResponse");
    private final static QName _SelectPartnerListResponse_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectPartnerListResponse");
    private final static QName _SelectVIPMemberList_QNAME = new QName("http://baseInfo.entInfo.api.ws.nbchina.com/", "selectVIPMemberList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nbchina.ws.api.entinfo.baseinfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SelectByPrimaryKeyResponse }
     * 
     */
    public SelectByPrimaryKeyResponse createSelectByPrimaryKeyResponse() {
        return new SelectByPrimaryKeyResponse();
    }

    /**
     * Create an instance of {@link SelectEntNumberByAreacodeResponse }
     * 
     */
    public SelectEntNumberByAreacodeResponse createSelectEntNumberByAreacodeResponse() {
        return new SelectEntNumberByAreacodeResponse();
    }

    /**
     * Create an instance of {@link SelectByParentId }
     * 
     */
    public SelectByParentId createSelectByParentId() {
        return new SelectByParentId();
    }

    /**
     * Create an instance of {@link AccurateSelect }
     * 
     */
    public AccurateSelect createAccurateSelect() {
        return new AccurateSelect();
    }

    /**
     * Create an instance of {@link SelectPartnerWithoutCapiListResponse }
     * 
     */
    public SelectPartnerWithoutCapiListResponse createSelectPartnerWithoutCapiListResponse() {
        return new SelectPartnerWithoutCapiListResponse();
    }

    /**
     * Create an instance of {@link SelectCityInfo }
     * 
     */
    public SelectCityInfo createSelectCityInfo() {
        return new SelectCityInfo();
    }

    /**
     * Create an instance of {@link SelectPartnerWithCapiByUuid }
     * 
     */
    public SelectPartnerWithCapiByUuid createSelectPartnerWithCapiByUuid() {
        return new SelectPartnerWithCapiByUuid();
    }

    /**
     * Create an instance of {@link SelectByPrimaryKeyWithChildEnts }
     * 
     */
    public SelectByPrimaryKeyWithChildEnts createSelectByPrimaryKeyWithChildEnts() {
        return new SelectByPrimaryKeyWithChildEnts();
    }

    /**
     * Create an instance of {@link FindIndexByEnterpriseName }
     * 
     */
    public FindIndexByEnterpriseName createFindIndexByEnterpriseName() {
        return new FindIndexByEnterpriseName();
    }

    /**
     * Create an instance of {@link SelectCityInfoResponse }
     * 
     */
    public SelectCityInfoResponse createSelectCityInfoResponse() {
        return new SelectCityInfoResponse();
    }

    /**
     * Create an instance of {@link SelectEntsByScopeResponse }
     * 
     */
    public SelectEntsByScopeResponse createSelectEntsByScopeResponse() {
        return new SelectEntsByScopeResponse();
    }

    /**
     * Create an instance of {@link SelectEntByPartnerName }
     * 
     */
    public SelectEntByPartnerName createSelectEntByPartnerName() {
        return new SelectEntByPartnerName();
    }

    /**
     * Create an instance of {@link SelectProvinceInfo }
     * 
     */
    public SelectProvinceInfo createSelectProvinceInfo() {
        return new SelectProvinceInfo();
    }

    /**
     * Create an instance of {@link AdvanceSelectResponse }
     * 
     */
    public AdvanceSelectResponse createAdvanceSelectResponse() {
        return new AdvanceSelectResponse();
    }

    /**
     * Create an instance of {@link SelectEntByLegalPersonNameResponse }
     * 
     */
    public SelectEntByLegalPersonNameResponse createSelectEntByLegalPersonNameResponse() {
        return new SelectEntByLegalPersonNameResponse();
    }

    /**
     * Create an instance of {@link AdvanceSelect }
     * 
     */
    public AdvanceSelect createAdvanceSelect() {
        return new AdvanceSelect();
    }

    /**
     * Create an instance of {@link SelectEntsByScope }
     * 
     */
    public SelectEntsByScope createSelectEntsByScope() {
        return new SelectEntsByScope();
    }

    /**
     * Create an instance of {@link SelectEntNumberByAreacode }
     * 
     */
    public SelectEntNumberByAreacode createSelectEntNumberByAreacode() {
        return new SelectEntNumberByAreacode();
    }

    /**
     * Create an instance of {@link SelectProvinceInfoResponse }
     * 
     */
    public SelectProvinceInfoResponse createSelectProvinceInfoResponse() {
        return new SelectProvinceInfoResponse();
    }

    /**
     * Create an instance of {@link SelectVIPMemberList }
     * 
     */
    public SelectVIPMemberList createSelectVIPMemberList() {
        return new SelectVIPMemberList();
    }

    /**
     * Create an instance of {@link SelectByPrimaryKeyWithChildEntsResponse }
     * 
     */
    public SelectByPrimaryKeyWithChildEntsResponse createSelectByPrimaryKeyWithChildEntsResponse() {
        return new SelectByPrimaryKeyWithChildEntsResponse();
    }

    /**
     * Create an instance of {@link SelectPartnerListResponse }
     * 
     */
    public SelectPartnerListResponse createSelectPartnerListResponse() {
        return new SelectPartnerListResponse();
    }

    /**
     * Create an instance of {@link SelectByPrimaryKey }
     * 
     */
    public SelectByPrimaryKey createSelectByPrimaryKey() {
        return new SelectByPrimaryKey();
    }

    /**
     * Create an instance of {@link SelectVIPMemberListResponse }
     * 
     */
    public SelectVIPMemberListResponse createSelectVIPMemberListResponse() {
        return new SelectVIPMemberListResponse();
    }

    /**
     * Create an instance of {@link SelectPartnerWithoutCapiList }
     * 
     */
    public SelectPartnerWithoutCapiList createSelectPartnerWithoutCapiList() {
        return new SelectPartnerWithoutCapiList();
    }

    /**
     * Create an instance of {@link SelectPartnerList }
     * 
     */
    public SelectPartnerList createSelectPartnerList() {
        return new SelectPartnerList();
    }

    /**
     * Create an instance of {@link SelectEntByPartnerNameResponse }
     * 
     */
    public SelectEntByPartnerNameResponse createSelectEntByPartnerNameResponse() {
        return new SelectEntByPartnerNameResponse();
    }

    /**
     * Create an instance of {@link SelectPartnerWithCapiByUuidResponse }
     * 
     */
    public SelectPartnerWithCapiByUuidResponse createSelectPartnerWithCapiByUuidResponse() {
        return new SelectPartnerWithCapiByUuidResponse();
    }

    /**
     * Create an instance of {@link SelectByParentIdResponse }
     * 
     */
    public SelectByParentIdResponse createSelectByParentIdResponse() {
        return new SelectByParentIdResponse();
    }

    /**
     * Create an instance of {@link FindIndexByEnterpriseNameResponse }
     * 
     */
    public FindIndexByEnterpriseNameResponse createFindIndexByEnterpriseNameResponse() {
        return new FindIndexByEnterpriseNameResponse();
    }

    /**
     * Create an instance of {@link AccurateSelectResponse }
     * 
     */
    public AccurateSelectResponse createAccurateSelectResponse() {
        return new AccurateSelectResponse();
    }

    /**
     * Create an instance of {@link SelectEntByLegalPersonName }
     * 
     */
    public SelectEntByLegalPersonName createSelectEntByLegalPersonName() {
        return new SelectEntByLegalPersonName();
    }

    /**
     * Create an instance of {@link Patent }
     * 
     */
    public Patent createPatent() {
        return new Patent();
    }

    /**
     * Create an instance of {@link EntSpread }
     * 
     */
    public EntSpread createEntSpread() {
        return new EntSpread();
    }

    /**
     * Create an instance of {@link Partner }
     * 
     */
    public Partner createPartner() {
        return new Partner();
    }

    /**
     * Create an instance of {@link PartnerRealCapi }
     * 
     */
    public PartnerRealCapi createPartnerRealCapi() {
        return new PartnerRealCapi();
    }

    /**
     * Create an instance of {@link PartnerShouldCapi }
     * 
     */
    public PartnerShouldCapi createPartnerShouldCapi() {
        return new PartnerShouldCapi();
    }

    /**
     * Create an instance of {@link Enterprise }
     * 
     */
    public Enterprise createEnterprise() {
        return new Enterprise();
    }

    /**
     * Create an instance of {@link CommonGridListEntityVo }
     * 
     */
    public CommonGridListEntityVo createCommonGridListEntityVo() {
        return new CommonGridListEntityVo();
    }

    /**
     * Create an instance of {@link VipMember }
     * 
     */
    public VipMember createVipMember() {
        return new VipMember();
    }

    /**
     * Create an instance of {@link QueryResult }
     * 
     */
    public QueryResult createQueryResult() {
        return new QueryResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectCityInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectCityInfoResponse")
    public JAXBElement<SelectCityInfoResponse> createSelectCityInfoResponse(SelectCityInfoResponse value) {
        return new JAXBElement<SelectCityInfoResponse>(_SelectCityInfoResponse_QNAME, SelectCityInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntsByScopeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntsByScopeResponse")
    public JAXBElement<SelectEntsByScopeResponse> createSelectEntsByScopeResponse(SelectEntsByScopeResponse value) {
        return new JAXBElement<SelectEntsByScopeResponse>(_SelectEntsByScopeResponse_QNAME, SelectEntsByScopeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectProvinceInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectProvinceInfo")
    public JAXBElement<SelectProvinceInfo> createSelectProvinceInfo(SelectProvinceInfo value) {
        return new JAXBElement<SelectProvinceInfo>(_SelectProvinceInfo_QNAME, SelectProvinceInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntByPartnerName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntByPartnerName")
    public JAXBElement<SelectEntByPartnerName> createSelectEntByPartnerName(SelectEntByPartnerName value) {
        return new JAXBElement<SelectEntByPartnerName>(_SelectEntByPartnerName_QNAME, SelectEntByPartnerName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntNumberByAreacodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntNumberByAreacodeResponse")
    public JAXBElement<SelectEntNumberByAreacodeResponse> createSelectEntNumberByAreacodeResponse(SelectEntNumberByAreacodeResponse value) {
        return new JAXBElement<SelectEntNumberByAreacodeResponse>(_SelectEntNumberByAreacodeResponse_QNAME, SelectEntNumberByAreacodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectByParentId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectByParentId")
    public JAXBElement<SelectByParentId> createSelectByParentId(SelectByParentId value) {
        return new JAXBElement<SelectByParentId>(_SelectByParentId_QNAME, SelectByParentId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectByPrimaryKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectByPrimaryKeyResponse")
    public JAXBElement<SelectByPrimaryKeyResponse> createSelectByPrimaryKeyResponse(SelectByPrimaryKeyResponse value) {
        return new JAXBElement<SelectByPrimaryKeyResponse>(_SelectByPrimaryKeyResponse_QNAME, SelectByPrimaryKeyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectCityInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectCityInfo")
    public JAXBElement<SelectCityInfo> createSelectCityInfo(SelectCityInfo value) {
        return new JAXBElement<SelectCityInfo>(_SelectCityInfo_QNAME, SelectCityInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindIndexByEnterpriseName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "findIndexByEnterpriseName")
    public JAXBElement<FindIndexByEnterpriseName> createFindIndexByEnterpriseName(FindIndexByEnterpriseName value) {
        return new JAXBElement<FindIndexByEnterpriseName>(_FindIndexByEnterpriseName_QNAME, FindIndexByEnterpriseName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectByPrimaryKeyWithChildEnts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectByPrimaryKeyWithChildEnts")
    public JAXBElement<SelectByPrimaryKeyWithChildEnts> createSelectByPrimaryKeyWithChildEnts(SelectByPrimaryKeyWithChildEnts value) {
        return new JAXBElement<SelectByPrimaryKeyWithChildEnts>(_SelectByPrimaryKeyWithChildEnts_QNAME, SelectByPrimaryKeyWithChildEnts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPartnerWithCapiByUuid }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectPartnerWithCapiByUuid")
    public JAXBElement<SelectPartnerWithCapiByUuid> createSelectPartnerWithCapiByUuid(SelectPartnerWithCapiByUuid value) {
        return new JAXBElement<SelectPartnerWithCapiByUuid>(_SelectPartnerWithCapiByUuid_QNAME, SelectPartnerWithCapiByUuid.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccurateSelect }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "accurateSelect")
    public JAXBElement<AccurateSelect> createAccurateSelect(AccurateSelect value) {
        return new JAXBElement<AccurateSelect>(_AccurateSelect_QNAME, AccurateSelect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPartnerWithoutCapiListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectPartnerWithoutCapiListResponse")
    public JAXBElement<SelectPartnerWithoutCapiListResponse> createSelectPartnerWithoutCapiListResponse(SelectPartnerWithoutCapiListResponse value) {
        return new JAXBElement<SelectPartnerWithoutCapiListResponse>(_SelectPartnerWithoutCapiListResponse_QNAME, SelectPartnerWithoutCapiListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPartnerWithoutCapiList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectPartnerWithoutCapiList")
    public JAXBElement<SelectPartnerWithoutCapiList> createSelectPartnerWithoutCapiList(SelectPartnerWithoutCapiList value) {
        return new JAXBElement<SelectPartnerWithoutCapiList>(_SelectPartnerWithoutCapiList_QNAME, SelectPartnerWithoutCapiList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectVIPMemberListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectVIPMemberListResponse")
    public JAXBElement<SelectVIPMemberListResponse> createSelectVIPMemberListResponse(SelectVIPMemberListResponse value) {
        return new JAXBElement<SelectVIPMemberListResponse>(_SelectVIPMemberListResponse_QNAME, SelectVIPMemberListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectByPrimaryKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectByPrimaryKey")
    public JAXBElement<SelectByPrimaryKey> createSelectByPrimaryKey(SelectByPrimaryKey value) {
        return new JAXBElement<SelectByPrimaryKey>(_SelectByPrimaryKey_QNAME, SelectByPrimaryKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntByPartnerNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntByPartnerNameResponse")
    public JAXBElement<SelectEntByPartnerNameResponse> createSelectEntByPartnerNameResponse(SelectEntByPartnerNameResponse value) {
        return new JAXBElement<SelectEntByPartnerNameResponse>(_SelectEntByPartnerNameResponse_QNAME, SelectEntByPartnerNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPartnerList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectPartnerList")
    public JAXBElement<SelectPartnerList> createSelectPartnerList(SelectPartnerList value) {
        return new JAXBElement<SelectPartnerList>(_SelectPartnerList_QNAME, SelectPartnerList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntByLegalPersonName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntByLegalPersonName")
    public JAXBElement<SelectEntByLegalPersonName> createSelectEntByLegalPersonName(SelectEntByLegalPersonName value) {
        return new JAXBElement<SelectEntByLegalPersonName>(_SelectEntByLegalPersonName_QNAME, SelectEntByLegalPersonName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AccurateSelectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "accurateSelectResponse")
    public JAXBElement<AccurateSelectResponse> createAccurateSelectResponse(AccurateSelectResponse value) {
        return new JAXBElement<AccurateSelectResponse>(_AccurateSelectResponse_QNAME, AccurateSelectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindIndexByEnterpriseNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "findIndexByEnterpriseNameResponse")
    public JAXBElement<FindIndexByEnterpriseNameResponse> createFindIndexByEnterpriseNameResponse(FindIndexByEnterpriseNameResponse value) {
        return new JAXBElement<FindIndexByEnterpriseNameResponse>(_FindIndexByEnterpriseNameResponse_QNAME, FindIndexByEnterpriseNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectByParentIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectByParentIdResponse")
    public JAXBElement<SelectByParentIdResponse> createSelectByParentIdResponse(SelectByParentIdResponse value) {
        return new JAXBElement<SelectByParentIdResponse>(_SelectByParentIdResponse_QNAME, SelectByParentIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPartnerWithCapiByUuidResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectPartnerWithCapiByUuidResponse")
    public JAXBElement<SelectPartnerWithCapiByUuidResponse> createSelectPartnerWithCapiByUuidResponse(SelectPartnerWithCapiByUuidResponse value) {
        return new JAXBElement<SelectPartnerWithCapiByUuidResponse>(_SelectPartnerWithCapiByUuidResponse_QNAME, SelectPartnerWithCapiByUuidResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntByLegalPersonNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntByLegalPersonNameResponse")
    public JAXBElement<SelectEntByLegalPersonNameResponse> createSelectEntByLegalPersonNameResponse(SelectEntByLegalPersonNameResponse value) {
        return new JAXBElement<SelectEntByLegalPersonNameResponse>(_SelectEntByLegalPersonNameResponse_QNAME, SelectEntByLegalPersonNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdvanceSelectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "advanceSelectResponse")
    public JAXBElement<AdvanceSelectResponse> createAdvanceSelectResponse(AdvanceSelectResponse value) {
        return new JAXBElement<AdvanceSelectResponse>(_AdvanceSelectResponse_QNAME, AdvanceSelectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntNumberByAreacode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntNumberByAreacode")
    public JAXBElement<SelectEntNumberByAreacode> createSelectEntNumberByAreacode(SelectEntNumberByAreacode value) {
        return new JAXBElement<SelectEntNumberByAreacode>(_SelectEntNumberByAreacode_QNAME, SelectEntNumberByAreacode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdvanceSelect }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "advanceSelect")
    public JAXBElement<AdvanceSelect> createAdvanceSelect(AdvanceSelect value) {
        return new JAXBElement<AdvanceSelect>(_AdvanceSelect_QNAME, AdvanceSelect.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectEntsByScope }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectEntsByScope")
    public JAXBElement<SelectEntsByScope> createSelectEntsByScope(SelectEntsByScope value) {
        return new JAXBElement<SelectEntsByScope>(_SelectEntsByScope_QNAME, SelectEntsByScope.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectProvinceInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectProvinceInfoResponse")
    public JAXBElement<SelectProvinceInfoResponse> createSelectProvinceInfoResponse(SelectProvinceInfoResponse value) {
        return new JAXBElement<SelectProvinceInfoResponse>(_SelectProvinceInfoResponse_QNAME, SelectProvinceInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectByPrimaryKeyWithChildEntsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectByPrimaryKeyWithChildEntsResponse")
    public JAXBElement<SelectByPrimaryKeyWithChildEntsResponse> createSelectByPrimaryKeyWithChildEntsResponse(SelectByPrimaryKeyWithChildEntsResponse value) {
        return new JAXBElement<SelectByPrimaryKeyWithChildEntsResponse>(_SelectByPrimaryKeyWithChildEntsResponse_QNAME, SelectByPrimaryKeyWithChildEntsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPartnerListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectPartnerListResponse")
    public JAXBElement<SelectPartnerListResponse> createSelectPartnerListResponse(SelectPartnerListResponse value) {
        return new JAXBElement<SelectPartnerListResponse>(_SelectPartnerListResponse_QNAME, SelectPartnerListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectVIPMemberList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://baseInfo.entInfo.api.ws.nbchina.com/", name = "selectVIPMemberList")
    public JAXBElement<SelectVIPMemberList> createSelectVIPMemberList(SelectVIPMemberList value) {
        return new JAXBElement<SelectVIPMemberList>(_SelectVIPMemberList_QNAME, SelectVIPMemberList.class, null, value);
    }

}
