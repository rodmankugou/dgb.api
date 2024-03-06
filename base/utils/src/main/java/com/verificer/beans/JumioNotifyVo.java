package com.verificer.beans;

import java.io.Serializable;

/**
 * 参考文档： https://github.com/Jumio/implementation-guides/blob/master/netverify/callback.md
 */
public class JumioNotifyVo implements Serializable {
    private String callBackType;
    private String jumioIdScanReference;
    /**
     * . APPROVED_VERIFIED
     * • DENIED_FRAUD
     * • DENIED_UNSUPPORTED_ID_TYPE 1
     * • DENIED_UNSUPPORTED_ID_COUNTRY 1
     * • ERROR_NOT_READABLE_ID
     * • NO_ID_UPLOADED
     */
    private String verificationStatus;

    /**
     * SUCCESS if verificationStatus = APPROVED_VERIFIED, otherwise ERROR
     */
    private String idScanStatus;

    private String idScanSource;

    /**
     * • OK if verificationStatus = APPROVED_VERIFIED
     * • otherwise N/A
     */
    private String idCheckDataPositions;

    /**
     * • OK if verificationStatus = APPROVED_VERIFIED
     * • otherwise N/A
     */
    private String idCheckDocumentValidation;

    /**
     * • OK if verificationStatus = APPROVED_VERIFIED
     * • otherwise N/A
     */
    private String idCheckHologram;

    /**
     * • OK for passports and supported ID cards if verificationStatus = APPROVED_VERIFIED and MRZ check is enabled
     * • otherwise N/A
     * not returned for DEU, HKG, JPN, NLD, SGP, KOR if NV masking is enabled
     */
    private String idCheckMRZcode;

    /**
     * • OK if verificationStatus = APPROVED_VERIFIED
     * • otherwise N/A
     */
    private String idCheckMicroprint;

    /**
     *
     * • OK if verificationStatus = APPROVED_VERIFIED
     * • otherwise N/A
     */
    private String idCheckSecurityFeatures;

    /**
     *• OK if verificationStatus = APPROVED_VERIFIED
     * • otherwise N/A
     */
    private String idCheckSignature;

    /**
     * Timestamp (UTC) of the transaction creation
     * format: YYYY-MM-DDThh:mm:ss.SSSZ
     */
    private String transactionDate;

    /**
     * Timestamp (UTC) of the callback creation
     * format: YYYY-MM-DDThh:mm:ss.SSSZ
     */
    private String callbackDate;

    /**
     * Identity verification as JSON object,
     * ONLY if verificationStatus = APPROVED_VERIFIED
     *
     * see table Identity Verification below
     */
    private String identityVerification;

    /**
     *Possible types:
     * • PASSPORT
     * • DRIVING_LICENSE
     * • ID_CARD
     * • VISA
     *
     * Currently, Jumio only supports US and China visas in certain cases. Visas from other countries will be rejected as unsupported with idType = VISA
     */
    private String idType;

    /**
     *Possible subtypes if idType = ID_CARD
     * • NATIONAL_ID
     * • CONSULAR_ID
     * • ELECTORAL_ID
     * • RESIDENT_PERMIT_ID
     * • TAX_ID
     * • STUDENT_ID
     * • PASSPORT_CARD_ID
     * • MILITARY_ID
     * • PUBLIC_SAFETY_ID
     * • HEALTH_ID
     * • OTHER_ID
     * • VISA
     * • UNKNOWN
     *
     * Possible subtypes if idType = DRIVING_LICENSE
     * • REGULAR_DRIVING_LICENSE
     * • LEARNING_DRIVING_LICENSE
     *
     * Possible subtypes if idType = PASSPORT
     * • E_PASSPORT (only for mobile)
     */
    private String idSubtype;

    /**
     * Possible countries:
     * • ISO 3166-1 alpha-3 country code
     * • XKX (Kosovo)
     */
    private String idCountry;

    /**
     *rejectReason
     * Reject reason as JSON object if verificationStatus = DENIED_FRAUD or ERROR_NOT_READABLE_ID, see tables below
     */
    private String rejectReason;

    /**
     * 	URL to retrieve the image of the transaction (JPEG or PNG) if available 2
     */
    private String idScanImage;

    /**
     *URL to retrieve the face image of the transaction (JPEG or PNG) if available 2
     */
    private String idScanImageFace;

    /**
     *	URL to retrieve the back side image of the transaction (JPEG or PNG) if available 2
     */
    private String idScanImageBackside;

    /**
     *Identification number of the document as available on the ID if enabled, otherwise if provided
     */
    private String idNumber;

    /**
     * • First name of the customer as available on the ID if enabled, otherwise if provided
     *
     * • For following documents, N/A returned (if name contains non-Latin characters)
     * - if idCountry = CHN and idType = DRIVING_LICENSE or ID_CARD
     * - if idCountry = KOR and idType = DRIVING_LICENSE or ID_CARD
     * - if idCountry = JPN and idType = DRIVING_LICENSE
     * - if idCountry = RUS and idType = ID_CARD
     */
    private String idFirstName;

    /**
     *• Last name of the customer as available on the ID if enabled, otherwise if provided
     *
     * • For following documents, Chinese name as printed on the document returned (if enabled)
     * - if idCountry = CHN and idType = DRIVING_LICENSE or ID_CARD
     * (first name and last name)
     *
     * • Only if full name is printed in Latin characters
     * - if idCountry = KOR and idType = DRIVING_LICENSE
     * (first name and last name)
     *
     * • For following documents, N/A returned (if name contains non-Latin characters)
     * - if idCountry = CHN and idType = DRIVING_LICENSE or ID_CARD
     * - if idCountry = KOR and idType = DRIVING_LICENSE or ID_CARD
     * - if idCountry = JPN and idType = DRIVING_LICENSE
     * - if idCountry = RUS and idType = ID_CARD
     */
    private String idLastName;

    /**
     *Date of birth in the format YYYY-MM-DD as available on the ID if enabled, otherwise if provided
     *
     * If idCountry = HKG, IND date of birth can be incomplete, possible values e.g.:
     * • Year-Month-Day: 1990-12-09
     * • Year only: 1990-01-01
     * • Year-Month: 1990-12-01
     * • Year-Day: 1990-01-09
     * Additional parameter originDob will be provided
     */
    private String idDob;

    /**
     *Date of expiry in the format YYYY-MM-DD as available on the ID if enabled, otherwise if provided
     */
    private String idExpiry;

    /**
     *
     */
    private String idUsState;

    /**
     * Personal number of the document • if idType = PASSPORT and if data available on the document
     */
    private String personalNumber;

    /**
     * Address as JSON object in RAW format, see tables below 3
     */
    private String idAddress;

    /**
     * Your reference for each transaction
     */
    private String merchantIdScanReference;

    /**
     *Your reporting criteria for each transaction
     */
    private String merchantReportingCriteria;

    /**
     *ID of the customer as provided
     */
    private String customerId;

    /**
     *IP address of the client in the format xxx.xxx.xxx.xxx
     */
    private String clientIp;

    /**
     *Timestamp (UTC) of the first transaction attempt
     * format: YYYY-MM-DDThh:mm:ss.SSSZ
     */
    private String firstAttemptDate;

    /**
     *
     */
    private String optionalData1;

    /**
     *
     */
    private String optionalData2;

    /**
     *
     */
    private String dni;

    /**
     *
     */
    private String curp;

    /**
     *
     */
    private String gender;

    /**
     *
     */
    private String presetCountry;

    /**
     *
     */
    private String presetIdType;

    /**
     *
     */
    private String dlCarPermission;

    /**
     *
     */
    private String dlCategories;

    /**
     *
     */
    private String nationality;

    /**
     *
     */
    private String passportNumber;

    /**
     *
     */
    private String durationOfStay;

    /**
     *
     */
    private String numberOfEntries;

    /**
     *
     */
    private String visaCategory;

    /**
     *
     */
    private String originDob;

    /**
     *
     */
    private String issuingAuthority;

    /**
     *
     */
    private String issuingDate;

    /**
     *
     */
    private String issuingPlace;

    /**
     *
     */
    private String livenessImages;

    /**
     *
     */
    private String placeOfBirth;

    /**
     *
     */
    private String facemap;

    /**
     *
     */
    private String taxNumber;

    /**
     *
     */
    private String cpf	;

    /**
     *
     */
    private String registrationNumber;

    /**
     *
     */
    private String mothersName;

    /**
     *
     */
    private String fathersName;

    /**
     *
     */
    private String personalIdentificationNumber;

    /**
     *
     */
    private String rgNumber;

    /**
     *
     */
    private String voterIdNumber;

    /**
     *
     */
    private String issuingNumber;

    /**
     * 身份扫描结果（Screening）
     */
    private String additionalChecks;


    public String getCallBackType() {
        return callBackType;
    }

    public void setCallBackType(String callBackType) {
        this.callBackType = callBackType;
    }

    public String getJumioIdScanReference() {
        return jumioIdScanReference;
    }

    public void setJumioIdScanReference(String jumioIdScanReference) {
        this.jumioIdScanReference = jumioIdScanReference;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getIdScanStatus() {
        return idScanStatus;
    }

    public void setIdScanStatus(String idScanStatus) {
        this.idScanStatus = idScanStatus;
    }

    public String getIdScanSource() {
        return idScanSource;
    }

    public void setIdScanSource(String idScanSource) {
        this.idScanSource = idScanSource;
    }

    public String getIdCheckDataPositions() {
        return idCheckDataPositions;
    }

    public void setIdCheckDataPositions(String idCheckDataPositions) {
        this.idCheckDataPositions = idCheckDataPositions;
    }

    public String getIdCheckDocumentValidation() {
        return idCheckDocumentValidation;
    }

    public void setIdCheckDocumentValidation(String idCheckDocumentValidation) {
        this.idCheckDocumentValidation = idCheckDocumentValidation;
    }

    public String getIdCheckHologram() {
        return idCheckHologram;
    }

    public void setIdCheckHologram(String idCheckHologram) {
        this.idCheckHologram = idCheckHologram;
    }

    public String getIdCheckMRZcode() {
        return idCheckMRZcode;
    }

    public void setIdCheckMRZcode(String idCheckMRZcode) {
        this.idCheckMRZcode = idCheckMRZcode;
    }

    public String getIdCheckMicroprint() {
        return idCheckMicroprint;
    }

    public void setIdCheckMicroprint(String idCheckMicroprint) {
        this.idCheckMicroprint = idCheckMicroprint;
    }

    public String getIdCheckSignature() {
        return idCheckSignature;
    }

    public void setIdCheckSignature(String idCheckSignature) {
        this.idCheckSignature = idCheckSignature;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCallbackDate() {
        return callbackDate;
    }

    public void setCallbackDate(String callbackDate) {
        this.callbackDate = callbackDate;
    }

    public String getIdentityVerification() {
        return identityVerification;
    }

    public void setIdentityVerification(String identityVerification) {
        this.identityVerification = identityVerification;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdSubtype() {
        return idSubtype;
    }

    public void setIdSubtype(String idSubtype) {
        this.idSubtype = idSubtype;
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getIdScanImage() {
        return idScanImage;
    }

    public void setIdScanImage(String idScanImage) {
        this.idScanImage = idScanImage;
    }

    public String getIdScanImageFace() {
        return idScanImageFace;
    }

    public void setIdScanImageFace(String idScanImageFace) {
        this.idScanImageFace = idScanImageFace;
    }

    public String getIdScanImageBackside() {
        return idScanImageBackside;
    }

    public void setIdScanImageBackside(String idScanImageBackside) {
        this.idScanImageBackside = idScanImageBackside;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdFirstName() {
        return idFirstName;
    }

    public void setIdFirstName(String idFirstName) {
        this.idFirstName = idFirstName;
    }

    public String getIdLastName() {
        return idLastName;
    }

    public void setIdLastName(String idLastName) {
        this.idLastName = idLastName;
    }

    public String getIdDob() {
        return idDob;
    }

    public void setIdDob(String idDob) {
        this.idDob = idDob;
    }

    public String getIdExpiry() {
        return idExpiry;
    }

    public void setIdExpiry(String idExpiry) {
        this.idExpiry = idExpiry;
    }

    public String getIdUsState() {
        return idUsState;
    }

    public void setIdUsState(String idUsState) {
        this.idUsState = idUsState;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(String idAddress) {
        this.idAddress = idAddress;
    }

    public String getMerchantIdScanReference() {
        return merchantIdScanReference;
    }

    public void setMerchantIdScanReference(String merchantIdScanReference) {
        this.merchantIdScanReference = merchantIdScanReference;
    }

    public String getMerchantReportingCriteria() {
        return merchantReportingCriteria;
    }

    public void setMerchantReportingCriteria(String merchantReportingCriteria) {
        this.merchantReportingCriteria = merchantReportingCriteria;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getFirstAttemptDate() {
        return firstAttemptDate;
    }

    public void setFirstAttemptDate(String firstAttemptDate) {
        this.firstAttemptDate = firstAttemptDate;
    }

    public String getOptionalData1() {
        return optionalData1;
    }

    public void setOptionalData1(String optionalData1) {
        this.optionalData1 = optionalData1;
    }

    public String getOptionalData2() {
        return optionalData2;
    }

    public void setOptionalData2(String optionalData2) {
        this.optionalData2 = optionalData2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPresetCountry() {
        return presetCountry;
    }

    public void setPresetCountry(String presetCountry) {
        this.presetCountry = presetCountry;
    }

    public String getPresetIdType() {
        return presetIdType;
    }

    public void setPresetIdType(String presetIdType) {
        this.presetIdType = presetIdType;
    }

    public String getDlCarPermission() {
        return dlCarPermission;
    }

    public void setDlCarPermission(String dlCarPermission) {
        this.dlCarPermission = dlCarPermission;
    }

    public String getDlCategories() {
        return dlCategories;
    }

    public void setDlCategories(String dlCategories) {
        this.dlCategories = dlCategories;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(String durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public String getNumberOfEntries() {
        return numberOfEntries;
    }

    public void setNumberOfEntries(String numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    public String getVisaCategory() {
        return visaCategory;
    }

    public void setVisaCategory(String visaCategory) {
        this.visaCategory = visaCategory;
    }

    public String getOriginDob() {
        return originDob;
    }

    public void setOriginDob(String originDob) {
        this.originDob = originDob;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(String issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getIssuingPlace() {
        return issuingPlace;
    }

    public void setIssuingPlace(String issuingPlace) {
        this.issuingPlace = issuingPlace;
    }

    public String getLivenessImages() {
        return livenessImages;
    }

    public void setLivenessImages(String livenessImages) {
        this.livenessImages = livenessImages;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getFacemap() {
        return facemap;
    }

    public void setFacemap(String facemap) {
        this.facemap = facemap;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public String getRgNumber() {
        return rgNumber;
    }

    public void setRgNumber(String rgNumber) {
        this.rgNumber = rgNumber;
    }

    public String getVoterIdNumber() {
        return voterIdNumber;
    }

    public void setVoterIdNumber(String voterIdNumber) {
        this.voterIdNumber = voterIdNumber;
    }

    public String getIssuingNumber() {
        return issuingNumber;
    }

    public void setIssuingNumber(String issuingNumber) {
        this.issuingNumber = issuingNumber;
    }


    public String getIdCheckSecurityFeatures() {
        return idCheckSecurityFeatures;
    }

    public void setIdCheckSecurityFeatures(String idCheckSecurityFeatures) {
        this.idCheckSecurityFeatures = idCheckSecurityFeatures;
    }

    public String getAdditionalChecks() {
        return additionalChecks;
    }

    public void setAdditionalChecks(String additionalChecks) {
        this.additionalChecks = additionalChecks;
    }

    @Override
    public String toString() {
        return "JumioNotifyVo{" +
                "callBackType='" + callBackType + '\'' +
                ", jumioIdScanReference='" + jumioIdScanReference + '\'' +
                ", verificationStatus='" + verificationStatus + '\'' +
                ", idScanStatus='" + idScanStatus + '\'' +
                ", idScanSource='" + idScanSource + '\'' +
                ", idCheckDataPositions='" + idCheckDataPositions + '\'' +
                ", idCheckDocumentValidation='" + idCheckDocumentValidation + '\'' +
                ", idCheckHologram='" + idCheckHologram + '\'' +
                ", idCheckMRZcode='" + idCheckMRZcode + '\'' +
                ", idCheckMicroprint='" + idCheckMicroprint + '\'' +
                ", idCheckSecurityFeatures='" + idCheckSecurityFeatures + '\'' +
                ", idCheckSignature='" + idCheckSignature + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", callbackDate='" + callbackDate + '\'' +
                ", identityVerification='" + identityVerification + '\'' +
                ", idType='" + idType + '\'' +
                ", idSubtype='" + idSubtype + '\'' +
                ", idCountry='" + idCountry + '\'' +
                ", rejectReason='" + rejectReason + '\'' +
                ", idScanImage='" + idScanImage + '\'' +
                ", idScanImageFace='" + idScanImageFace + '\'' +
                ", idScanImageBackside='" + idScanImageBackside + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", idFirstName='" + idFirstName + '\'' +
                ", idLastName='" + idLastName + '\'' +
                ", idDob='" + idDob + '\'' +
                ", idExpiry='" + idExpiry + '\'' +
                ", idUsState='" + idUsState + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", idAddress='" + idAddress + '\'' +
                ", merchantIdScanReference='" + merchantIdScanReference + '\'' +
                ", merchantReportingCriteria='" + merchantReportingCriteria + '\'' +
                ", customerId='" + customerId + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", firstAttemptDate='" + firstAttemptDate + '\'' +
                ", optionalData1='" + optionalData1 + '\'' +
                ", optionalData2='" + optionalData2 + '\'' +
                ", dni='" + dni + '\'' +
                ", curp='" + curp + '\'' +
                ", gender='" + gender + '\'' +
                ", presetCountry='" + presetCountry + '\'' +
                ", presetIdType='" + presetIdType + '\'' +
                ", dlCarPermission='" + dlCarPermission + '\'' +
                ", dlCategories='" + dlCategories + '\'' +
                ", nationality='" + nationality + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", durationOfStay='" + durationOfStay + '\'' +
                ", numberOfEntries='" + numberOfEntries + '\'' +
                ", visaCategory='" + visaCategory + '\'' +
                ", originDob='" + originDob + '\'' +
                ", issuingAuthority='" + issuingAuthority + '\'' +
                ", issuingDate='" + issuingDate + '\'' +
                ", issuingPlace='" + issuingPlace + '\'' +
                ", livenessImages='" + livenessImages + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", facemap='" + facemap + '\'' +
                ", taxNumber='" + taxNumber + '\'' +
                ", cpf='" + cpf + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", mothersName='" + mothersName + '\'' +
                ", fathersName='" + fathersName + '\'' +
                ", personalIdentificationNumber='" + personalIdentificationNumber + '\'' +
                ", rgNumber='" + rgNumber + '\'' +
                ", voterIdNumber='" + voterIdNumber + '\'' +
                ", issuingNumber='" + issuingNumber + '\'' +
                ", additionalChecks='" + additionalChecks + '\'' +
                '}';
    }
}
