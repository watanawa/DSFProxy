//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 generiert 
// Siehe <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.09.01 um 10:02:54 PM CEST 
//


package com.schwipps.dsf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r TypeEquipmentDescription complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TypeEquipmentDescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GeneralInformation" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeGeneralInformation" minOccurs="0"/&gt;
 *         &lt;element name="DataProviders" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDataProviders"/&gt;
 *         &lt;element name="CommunicationLinks" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeCommunicationLinks"/&gt;
 *         &lt;element name="ArchitectureModel" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeArchitectureModel"/&gt;
 *         &lt;element name="DiagnosticData" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDiagnosticData" minOccurs="0"/&gt;
 *         &lt;element name="HealthMonitorEvents" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeHealthMonitorEvents" minOccurs="0"/&gt;
 *         &lt;element name="TracePoints" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeTracePoints" minOccurs="0"/&gt;
 *         &lt;element name="DebugSymbols" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeDebugSymbols" minOccurs="0"/&gt;
 *         &lt;element name="VirtualTerminals" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeVirtualTerminals" minOccurs="0"/&gt;
 *         &lt;element name="FileIntegrityCheck" type="{http://www.cassidian.com/uavsw/dsf/EquipmentDescription}TypeIntegrityCheck"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TypeEquipmentDescription", propOrder = {
    "generalInformation",
    "dataProviders",
    "communicationLinks",
    "architectureModel",
    "diagnosticData",
    "healthMonitorEvents",
    "tracePoints",
    "debugSymbols",
    "virtualTerminals",
    "fileIntegrityCheck"
})
public class TypeEquipmentDescription {

    @XmlElement(name = "GeneralInformation")
    protected TypeGeneralInformation generalInformation;
    @XmlElement(name = "DataProviders", required = true)
    protected TypeDataProviders dataProviders;
    @XmlElement(name = "CommunicationLinks", required = true)
    protected TypeCommunicationLinks communicationLinks;
    @XmlElement(name = "ArchitectureModel", required = true)
    protected TypeArchitectureModel architectureModel;
    @XmlElement(name = "DiagnosticData")
    protected TypeDiagnosticData diagnosticData;
    @XmlElement(name = "HealthMonitorEvents")
    protected TypeHealthMonitorEvents healthMonitorEvents;
    @XmlElement(name = "TracePoints")
    protected TypeTracePoints tracePoints;
    @XmlElement(name = "DebugSymbols")
    protected TypeDebugSymbols debugSymbols;
    @XmlElement(name = "VirtualTerminals")
    protected TypeVirtualTerminals virtualTerminals;
    @XmlElement(name = "FileIntegrityCheck", required = true)
    protected TypeIntegrityCheck fileIntegrityCheck;

    /**
     * Ruft den Wert der generalInformation-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeGeneralInformation }
     *     
     */
    public TypeGeneralInformation getGeneralInformation() {
        return generalInformation;
    }

    /**
     * Legt den Wert der generalInformation-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeGeneralInformation }
     *     
     */
    public void setGeneralInformation(TypeGeneralInformation value) {
        this.generalInformation = value;
    }

    /**
     * Ruft den Wert der dataProviders-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeDataProviders }
     *     
     */
    public TypeDataProviders getDataProviders() {
        return dataProviders;
    }

    /**
     * Legt den Wert der dataProviders-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDataProviders }
     *     
     */
    public void setDataProviders(TypeDataProviders value) {
        this.dataProviders = value;
    }

    /**
     * Ruft den Wert der communicationLinks-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeCommunicationLinks }
     *     
     */
    public TypeCommunicationLinks getCommunicationLinks() {
        return communicationLinks;
    }

    /**
     * Legt den Wert der communicationLinks-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeCommunicationLinks }
     *     
     */
    public void setCommunicationLinks(TypeCommunicationLinks value) {
        this.communicationLinks = value;
    }

    /**
     * Ruft den Wert der architectureModel-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeArchitectureModel }
     *     
     */
    public TypeArchitectureModel getArchitectureModel() {
        return architectureModel;
    }

    /**
     * Legt den Wert der architectureModel-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeArchitectureModel }
     *     
     */
    public void setArchitectureModel(TypeArchitectureModel value) {
        this.architectureModel = value;
    }

    /**
     * Ruft den Wert der diagnosticData-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeDiagnosticData }
     *     
     */
    public TypeDiagnosticData getDiagnosticData() {
        return diagnosticData;
    }

    /**
     * Legt den Wert der diagnosticData-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDiagnosticData }
     *     
     */
    public void setDiagnosticData(TypeDiagnosticData value) {
        this.diagnosticData = value;
    }

    /**
     * Ruft den Wert der healthMonitorEvents-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeHealthMonitorEvents }
     *     
     */
    public TypeHealthMonitorEvents getHealthMonitorEvents() {
        return healthMonitorEvents;
    }

    /**
     * Legt den Wert der healthMonitorEvents-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeHealthMonitorEvents }
     *     
     */
    public void setHealthMonitorEvents(TypeHealthMonitorEvents value) {
        this.healthMonitorEvents = value;
    }

    /**
     * Ruft den Wert der tracePoints-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeTracePoints }
     *     
     */
    public TypeTracePoints getTracePoints() {
        return tracePoints;
    }

    /**
     * Legt den Wert der tracePoints-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeTracePoints }
     *     
     */
    public void setTracePoints(TypeTracePoints value) {
        this.tracePoints = value;
    }

    /**
     * Ruft den Wert der debugSymbols-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeDebugSymbols }
     *     
     */
    public TypeDebugSymbols getDebugSymbols() {
        return debugSymbols;
    }

    /**
     * Legt den Wert der debugSymbols-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeDebugSymbols }
     *     
     */
    public void setDebugSymbols(TypeDebugSymbols value) {
        this.debugSymbols = value;
    }

    /**
     * Ruft den Wert der virtualTerminals-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeVirtualTerminals }
     *     
     */
    public TypeVirtualTerminals getVirtualTerminals() {
        return virtualTerminals;
    }

    /**
     * Legt den Wert der virtualTerminals-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeVirtualTerminals }
     *     
     */
    public void setVirtualTerminals(TypeVirtualTerminals value) {
        this.virtualTerminals = value;
    }

    /**
     * Ruft den Wert der fileIntegrityCheck-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TypeIntegrityCheck }
     *     
     */
    public TypeIntegrityCheck getFileIntegrityCheck() {
        return fileIntegrityCheck;
    }

    /**
     * Legt den Wert der fileIntegrityCheck-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeIntegrityCheck }
     *     
     */
    public void setFileIntegrityCheck(TypeIntegrityCheck value) {
        this.fileIntegrityCheck = value;
    }

}
