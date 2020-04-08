package pojo;

public class Lable {
    private Integer lableId; //id
    private String keyName;
    private String primaryKey;
    private String employer; //发包人
    private String contractor; //承包人
    private String projectName; //项目名
    private String contractPrice; //合同价
    private String plannedCommencementDate;//计划开工日期
    private String plannedCompletionDate; //计划竣工日期
    private String contractFilingCode; //合同备案码
    private String projectEncoding; //项目编码
    private String contractCode; //合同验证码
    private String projectManager; //项目经理
    private String projectManagerCode; //项目经理Code
    private String projectSupervisor; //--------项目主管
    private String technicalDirector; //技术负责人
    private String securityOfficer; //安全员
    private String qualityWorker; //质量员
    private String constructors;//施工员
    private String constructionUnit; //建设单位
    private String mediumBidName; //中标人名称
    private String mediumBidPrice; //中标价
    private String winningBidPeriod; //中标工期
    private String bidWinnQualityStandard;  //中标质量标准
    private String website; //网址
    private String completionStatus;//竣工情况
    private String confirmationDate;//确认日期

    private String personnel; //人员变更
    private String licence; //许可证

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getProjectManagerCode() {
        return projectManagerCode;
    }

    public void setProjectManagerCode(String projectManagerCode) {
        this.projectManagerCode = projectManagerCode;
    }

    public String getProjectSupervisor() {
        return projectSupervisor;
    }

    public void setProjectSupervisor(String projectSupervisor) {
        this.projectSupervisor = projectSupervisor;
    }

    public Integer getLableId() {
        return lableId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setLableId(Integer lableId) {
        this.lableId = lableId;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public String getPlannedCommencementDate() {
        return plannedCommencementDate;
    }

    public void setPlannedCommencementDate(String plannedCommencementDate) {
        this.plannedCommencementDate = plannedCommencementDate;
    }

    public String getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public void setPlannedCompletionDate(String plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public String getContractFilingCode() {
        return contractFilingCode;
    }

    public void setContractFilingCode(String contractFilingCode) {
        this.contractFilingCode = contractFilingCode;
    }

    public String getProjectEncoding() {
        return projectEncoding;
    }

    public void setProjectEncoding(String projectEncoding) {
        this.projectEncoding = projectEncoding;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getTechnicalDirector() {
        return technicalDirector;
    }

    public void setTechnicalDirector(String technicalDirector) {
        this.technicalDirector = technicalDirector;
    }

    public String getSecurityOfficer() {
        return securityOfficer;
    }

    public void setSecurityOfficer(String securityOfficer) {
        this.securityOfficer = securityOfficer;
    }

    public String getQualityWorker() {
        return qualityWorker;
    }

    public void setQualityWorker(String qualityWorker) {
        this.qualityWorker = qualityWorker;
    }

    public String getConstructors() {
        return constructors;
    }

    public void setConstructors(String constructors) {
        this.constructors = constructors;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getMediumBidName() {
        return mediumBidName;
    }

    public void setMediumBidName(String mediumBidName) {
        this.mediumBidName = mediumBidName;
    }

    public String getMediumBidPrice() {
        return mediumBidPrice;
    }

    public void setMediumBidPrice(String mediumBidPrice) {
        this.mediumBidPrice = mediumBidPrice;
    }

    public String getWinningBidPeriod() {
        return winningBidPeriod;
    }

    public void setWinningBidPeriod(String winningBidPeriod) {
        this.winningBidPeriod = winningBidPeriod;
    }

    public String getBidWinnQualityStandard() {
        return bidWinnQualityStandard;
    }

    public void setBidWinnQualityStandard(String bidWinnQualityStandard) {
        this.bidWinnQualityStandard = bidWinnQualityStandard;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Lable(){}

    public Lable(String keyName, String primaryKey) {
        this.keyName = keyName;
        this.primaryKey = primaryKey;
    }

    public Lable(Integer lableId, String keyName, String primaryKey, String employer, String contractor, String projectName, String contractPrice, String plannedCommencementDate, String plannedCompletionDate, String contractFilingCode, String projectEncoding, String contractCode, String projectManager, String projectManagerCode, String projectSupervisor, String technicalDirector, String securityOfficer, String qualityWorker, String constructors, String constructionUnit, String mediumBidName, String mediumBidPrice, String winningBidPeriod, String bidWinnQualityStandard, String website, String completionStatus, String confirmationDate, String personnel, String licence) {
        this.lableId = lableId;
        this.keyName = keyName;
        this.primaryKey = primaryKey;
        this.employer = employer;
        this.contractor = contractor;
        this.projectName = projectName;
        this.contractPrice = contractPrice;
        this.plannedCommencementDate = plannedCommencementDate;
        this.plannedCompletionDate = plannedCompletionDate;
        this.contractFilingCode = contractFilingCode;
        this.projectEncoding = projectEncoding;
        this.contractCode = contractCode;
        this.projectManager = projectManager;
        this.projectManagerCode = projectManagerCode;
        this.projectSupervisor = projectSupervisor;
        this.technicalDirector = technicalDirector;
        this.securityOfficer = securityOfficer;
        this.qualityWorker = qualityWorker;
        this.constructors = constructors;
        this.constructionUnit = constructionUnit;
        this.mediumBidName = mediumBidName;
        this.mediumBidPrice = mediumBidPrice;
        this.winningBidPeriod = winningBidPeriod;
        this.bidWinnQualityStandard = bidWinnQualityStandard;
        this.website = website;
        this.completionStatus = completionStatus;
        this.confirmationDate = confirmationDate;
        this.personnel = personnel;
        this.licence = licence;
    }

    public Lable(String keyName, String primaryKey, String employer, String contractor, String projectName, String contractPrice, String plannedCommencementDate, String plannedCompletionDate, String contractFilingCode, String projectEncoding, String contractCode, String projectManager, String projectManagerCode, String projectSupervisor, String technicalDirector, String securityOfficer, String qualityWorker, String constructors, String constructionUnit, String mediumBidName, String mediumBidPrice, String winningBidPeriod, String bidWinnQualityStandard, String website, String completionStatus, String confirmationDate, String personnel, String licence) {
        this.keyName = keyName;
        this.primaryKey = primaryKey;
        this.employer = employer;
        this.contractor = contractor;
        this.projectName = projectName;
        this.contractPrice = contractPrice;
        this.plannedCommencementDate = plannedCommencementDate;
        this.plannedCompletionDate = plannedCompletionDate;
        this.contractFilingCode = contractFilingCode;
        this.projectEncoding = projectEncoding;
        this.contractCode = contractCode;
        this.projectManager = projectManager;
        this.projectManagerCode = projectManagerCode;
        this.projectSupervisor = projectSupervisor;
        this.technicalDirector = technicalDirector;
        this.securityOfficer = securityOfficer;
        this.qualityWorker = qualityWorker;
        this.constructors = constructors;
        this.constructionUnit = constructionUnit;
        this.mediumBidName = mediumBidName;
        this.mediumBidPrice = mediumBidPrice;
        this.winningBidPeriod = winningBidPeriod;
        this.bidWinnQualityStandard = bidWinnQualityStandard;
        this.website = website;
        this.completionStatus = completionStatus;
        this.confirmationDate = confirmationDate;
        this.personnel = personnel;
        this.licence = licence;
    }

    public Lable(Integer lableId) {
        this.lableId = lableId;
    }

    @Override
    public String toString() {
        return "Lable{" +
                "lableId=" + lableId +
                ", keyName='" + keyName + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", employer='" + employer + '\'' +
                ", contractor='" + contractor + '\'' +
                ", projectName='" + projectName + '\'' +
                ", contractPrice='" + contractPrice + '\'' +
                ", plannedCommencementDate='" + plannedCommencementDate + '\'' +
                ", plannedCompletionDate='" + plannedCompletionDate + '\'' +
                ", contractFilingCode='" + contractFilingCode + '\'' +
                ", projectEncoding='" + projectEncoding + '\'' +
                ", contractCode='" + contractCode + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", projectManagerCode='" + projectManagerCode + '\'' +
                ", projectSupervisor='" + projectSupervisor + '\'' +
                ", technicalDirector='" + technicalDirector + '\'' +
                ", securityOfficer='" + securityOfficer + '\'' +
                ", qualityWorker='" + qualityWorker + '\'' +
                ", constructors='" + constructors + '\'' +
                ", constructionUnit='" + constructionUnit + '\'' +
                ", mediumBidName='" + mediumBidName + '\'' +
                ", mediumBidPrice='" + mediumBidPrice + '\'' +
                ", winningBidPeriod='" + winningBidPeriod + '\'' +
                ", bidWinnQualityStandard='" + bidWinnQualityStandard + '\'' +
                ", website='" + website + '\'' +
                ", completionStatus='" + completionStatus + '\'' +
                ", confirmationDate='" + confirmationDate + '\'' +
                '}';
    }
}
